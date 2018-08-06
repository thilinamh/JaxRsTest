package com.epic.exceptions.webexceptions;

import javax.ws.rs.core.Response;

/**
 * Created by tm on 7/24/17.
 * This class cannot take any response body since Http 304 cannot have a body
 */
public class DataNotAvailable extends REST_Ex {
    private static String ecode = "N/A";
    private static String data = "Data Not Available";
    private static Response.Status http_code = Response.Status.fromStatusCode(204);

    //    public DataNotAvailable(boolean visible, JsonObject data) {
//        super(ecode, data, http_code);
//    }
//
//    public DataNotAvailable(boolean visible, String data) {
//        super(ecode, visible, data, http_code);
//    }
//
//    public DataNotAvailable(String data) {
//        super(ecode, true, data, http_code);
//    }
    public DataNotAvailable() {
        super(http_code);
    }
}
