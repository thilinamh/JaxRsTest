package com.epic.core;

import com.epic.exceptions.webexceptions.SomethingWentWrong;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.validation.constraints.NotNull;

/**
 * Created by thilina_h on 8/6/2018.
 */

public interface PostController extends Controller {

    public  <T extends Enum & ControllerCommands> JsonObjectBuilder processRequest(@NotNull T command, JsonValue obj);
}
