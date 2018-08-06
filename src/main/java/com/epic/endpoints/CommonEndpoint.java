package com.epic.endpoints;

import com.epic.auth.AuthenticationHandlingFilter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by thilina_h on 8/5/2018.
 */
@Path("common")
public class CommonEndpoint {

    @GET
    @AuthenticationHandlingFilter.Authenticated
    public Response getData(){
        return Response.accepted().build();
    }
}
