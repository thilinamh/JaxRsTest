package com.epic.endpoints;

import com.epic.controllers.AuthController;
import com.epic.core.RequestMediator;
import com.epic.util.JsonHandler;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by thilina_h on 8/5/2018.
 */
@Path("user")
public class UserEndpoint {
    @Inject
    AuthController authController;

    @Inject
    RequestMediator requestMediator;

    @POST
    @Path("auth")
    public Response authenticate(String data) {
        final JsonObject jsonObject = JsonHandler.toJsonObject(data);
        return requestMediator.handleRequest(jsonObject, AuthController.Commands.LOGIN);
    }
}
