package com.epic.core;

import com.epic.exceptions.webexceptions.InvalidDataEx;
import com.epic.exceptions.webexceptions.SomethingWentWrong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Created by thilina_h on 8/6/2018.
 */
@RequestScoped
public class RequestMediator {
    protected Response.Status httpCode = Response.Status.OK;
    protected String return_status = "ok";
    Logger debugLogger = LoggerFactory.getLogger(RequestMediator.class);

    public <T extends Enum & Controller.ControllerCommands> Response handleRequest(JsonValue jsonValue, Controller.ControllerCommands command) {
        try {

            JsonObjectBuilder jsonObjectBuilder;
            if (jsonValue == null || jsonValue == JsonValue.NULL) {
                jsonObjectBuilder = ((GetController) command.getController()).processRequest((T) command);//GET
            } else if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT || jsonValue.getValueType() == JsonValue.ValueType.ARRAY) {
                jsonObjectBuilder = ((PostController) command.getController()).processRequest((T) command, jsonValue); //POST
            } else {
                throw new InvalidDataEx("Unsupported data type");
            }
            jsonObjectBuilder.add("status", return_status);
            return Response.status(httpCode).entity(jsonObjectBuilder.build().toString()).build();
        }
//        catch (WebApplicationException ex) {
//            int status = ex.getResponse().getStatus();
//
//            throw ex;
//        }
        catch (ConstraintViolationException ex) {
            String err = handleViolation(ex);
            throw new SomethingWentWrong("Error occurred due to invalid arguments. \n" + err, false);
        }
    }

    protected String handleViolation(ConstraintViolationException ex) {
        StringBuilder errorBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : constraintViolations) {
            errorBuilder.append("* ").append(violation.getMessage()).append("\n");
        }
        String err = errorBuilder.toString();
        debugLogger.warn("Bean validation error.{} \n", err, ex);
        return err;
    }

    public <T extends Enum & Controller.ControllerCommands> Response handleRequest(Controller.ControllerCommands command) {
        return handleRequest(JsonValue.NULL, command);
    }

}
