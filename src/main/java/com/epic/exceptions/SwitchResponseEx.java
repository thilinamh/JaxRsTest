/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.exceptions;

/**
 *
 * @author thilina_h
 */
public class SwitchResponseEx extends Exception {

    static final private String msg = "Error status from switch";
    private String errCode;

//    public SwitchResponseEx(String message) {
//        super(message);
//    }

    public SwitchResponseEx(String message, String ecode) {
        super(message);
        errCode = ecode;
    }

    public String getErrCode() {
        return errCode;
    }

//    public SwitchResponseEx() {
//        this(msg);
//    }
}
