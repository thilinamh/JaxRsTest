package com.epic.exceptions.webexceptions;

import javax.ws.rs.core.Response;

/**
 * Created by thilina_h on 12/4/2017.
 */
public class CommunicationException extends REST_Ex {
    private static final String ecode = "comm_err_1";
    private static final Response.Status http_code = Response.Status.SERVICE_UNAVAILABLE;

    public CommunicationException(boolean visibility, String data) {
        super(ecode, visibility, data, http_code);
    }

    public CommunicationException(boolean visibility, String data, String ecode) {
        super(ecode, visibility, data, http_code);
    }

    public CommunicationException(String data, String ecode) {
        super(ecode, true, data, http_code);
    }

}
