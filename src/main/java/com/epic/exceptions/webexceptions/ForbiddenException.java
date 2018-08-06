package com.epic.exceptions.webexceptions;

import javax.ws.rs.core.Response;

/**
 * Created by thilina_h on 2/13/2018.
 */
public class ForbiddenException extends REST_Ex {

    final static Response.Status http_code =Response.Status.FORBIDDEN;
    final static String ecode="forbidden";
    public ForbiddenException(String ecode, String data) {
        super(ecode, data, http_code);
    }
    public ForbiddenException(String data) {
        super(ecode, data, http_code);
    }
    public ForbiddenException() {
        super(ecode, "Restricted operation. Please re login", http_code);
    }
}
