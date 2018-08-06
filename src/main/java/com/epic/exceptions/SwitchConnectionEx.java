/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.exceptions;

/**
 * @author thilina_h
 */
public class SwitchConnectionEx extends ExceptionBase {

    static final private String msg = "Error connecting switch";
    static final private String ecode = "serr";
    private String errCode;
    static private final int http_code = 503;

//    public SwitchResponseEx(String message) {
//        super(message);
//    }

    public SwitchConnectionEx() {
        super(msg, null, http_code, ecode);
    }

    public SwitchConnectionEx(Throwable cause) {
        super(msg, cause, http_code, ecode);
    }

    public SwitchConnectionEx(String msg, Throwable cause) {
        super(msg, cause, http_code, ecode);
    }


    public SwitchConnectionEx(String message, String ecode) {
        super(message, null, http_code, ecode);
        errCode = ecode;
    }

    public String getErrCode() {
        return errCode;
    }

}
