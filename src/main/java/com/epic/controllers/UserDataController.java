package com.epic.controllers;

import com.epic.core.Controller;
import com.epic.core.GetController;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * Created by thilina_h on 8/6/2018.
 */
public class UserDataController implements GetController {

    public enum Commands implements ControllerCommands<AuthController> {
        GET_AGE;
    }

    @Override
    public <T extends Enum & ControllerCommands> JsonObjectBuilder processRequest(T command) {
        return Json.createObjectBuilder();
    }
}
