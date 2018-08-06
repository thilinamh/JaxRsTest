package com.epic.auth;

import com.epic.exceptions.webexceptions.ForbiddenException;
import com.epic.exceptions.webexceptions.InvalidDataEx;
import com.epic.util.KeyHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;


import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.NameBinding;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by thilina_h on 8/5/2018.
 */
@Provider
@AuthenticationHandlingFilter.Authenticated
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationHandlingFilter implements ContainerRequestFilter {

    @NameBinding
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Authenticated {
    }

    @Inject
    KeyHandler keyHandler;

    @Context
    private HttpHeaders headers;

    @Inject
    Event<UserAuthenticated> event;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String compactJws = headers.getHeaderString("token");
        if (compactJws == null) {
            requestContext.abortWith(new InvalidDataEx("auth token required").getResponse());
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(keyHandler.getJWTSigningKey()).parseClaimsJws(compactJws);
            final String userName = claimsJws.getBody().getSubject();
            event.fire(new UserAuthenticated(userName));
        } catch (SignatureException e) {//don't trust the JWT!
            requestContext.abortWith(new ForbiddenException("Invalid Session").getResponse());
        } catch (ExpiredJwtException e) {
            requestContext.abortWith(new ForbiddenException("Expired Session").getResponse());
        }
    }

    public void onUserAuthenticated(@Observes UserAuthenticated authenticated) {
        System.out.println(authenticated.getId() + " authenticated");
    }


}
