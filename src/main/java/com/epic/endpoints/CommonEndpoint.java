package com.epic.endpoints;

import com.epic.auth.AuthenticationHandlingFilter;
import com.epic.controllers.UserDataController;
import com.epic.core.RequestMediator;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by thilina_h on 8/5/2018.
 */
@Path("common")
public class CommonEndpoint {

    @Inject
    RequestMediator requestMediator;

    @GET
    @AuthenticationHandlingFilter.Authenticated
    public Response getData(){
        return requestMediator.handleRequest(UserDataController.Commands.GET_AGE);
    }
}
