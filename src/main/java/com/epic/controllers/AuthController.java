package com.epic.controllers;

import com.epic.core.Controller;
import com.epic.core.PostController;
import com.epic.dao.UserData;
import com.epic.exceptions.webexceptions.InvalidCredentialsEx;
import com.epic.exceptions.webexceptions.InvalidDataEx;
import com.epic.exceptions.webexceptions.SomethingWentWrong;
import com.epic.util.KeyHandler;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by thilina_h on 8/5/2018.
 */
@ApplicationScoped
public class AuthController implements PostController {

    public enum Commands implements ControllerCommands<AuthController> {
        LOGIN;
    }

    @Inject
    UserData userData;

    @Inject
    KeyHandler keyHandler;

    public JsonObjectBuilder authenticate(JsonObject object) {
        final String userName = object.getString("user_name");
        final String password = object.getString("password");

        if (userData.isValidCredentials(userName, password)) {
            final String authToken = createAuthToken(userName);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("token", authToken);
            return objectBuilder;
        }
        throw new InvalidCredentialsEx();
    }

    private String createAuthToken(String id) {
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
                .claim("scope", "default")
                .signWith(SignatureAlgorithm.HS512, keyHandler.getJWTSigningKey())
                .compact();
    }

    @Override
    public <T extends Enum & ControllerCommands> JsonObjectBuilder processRequest(T command, JsonValue obj) {
        if (command == Commands.LOGIN) {
            return authenticate((JsonObject) obj);
        }
        throw new SomethingWentWrong();
    }
}
