/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.util;

import javax.json.*;
import javax.json.stream.JsonParsingException;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.Map;


/**
 * @author thilina_h
 */
public class JsonHandler {


    public static void addToJsonObjectWithNullReplace(String key, Object value, Object replace_on_null, JsonObjectBuilder objBuilder) {
        if (value != null) {
            if (value instanceof String) {
                objBuilder.add(key, ((String) value).trim());
            } else if (value instanceof JsonValue) {
                objBuilder.add(key, (JsonValue) value);
            } else if (value instanceof Number) {
                float f = ((Number) value).floatValue();
                int i = ((Number) value).intValue();
                if (f > i) {
                    objBuilder.add(key, f);
                } else {
                    objBuilder.add(key, i);
                }
            } else {
                objBuilder.add(key, value.toString());
            }
        } else if (replace_on_null instanceof String) {
            objBuilder.add(key, (String) replace_on_null);
        } else if (replace_on_null instanceof Number) {
            objBuilder.add(key, ((Number) replace_on_null).intValue());
        } else {
            objBuilder.addNull(key);
        }
    }

    private JsonHandler() {
    }

    ;

    public static JsonObject toJsonObject(String str) {

        try (JsonReader jsonReader = Json.createReader(new StringReader(str))) {
            return jsonReader.readObject();

        } catch (JsonParsingException e) {
            e.printStackTrace();
            throw new javax.ws.rs.client.ResponseProcessingException(Response.serverError().build(), "cannot parse input");
        } catch (JsonException e) {
            e.printStackTrace();
            throw new javax.ws.rs.NotSupportedException();
        }
    }

    public static JsonArray toJsonArray(String str) {
        try {
            JsonReader jsonReader = Json.createReader(new StringReader(str));
            return jsonReader.readArray();

        } catch (JsonParsingException e) {
            e.printStackTrace();
            throw new javax.ws.rs.client.ResponseProcessingException(Response.serverError().build(), "cannot parse input");
        } catch (JsonException e) {
            e.printStackTrace();
            throw new javax.ws.rs.NotSupportedException();
        }
    }

    public static void addToObjectBuilder(JsonObjectBuilder objectBuilder, JsonObject jsonBody) {
        for (Map.Entry<String, JsonValue> entry : jsonBody.entrySet()) {
            objectBuilder.add(entry.getKey(), entry.getValue());
        }
    }
}
