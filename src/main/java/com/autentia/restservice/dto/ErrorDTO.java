package com.autentia.restservice.dto;

public class ErrorDTO {
    private boolean err;
    private String msg;

    public ErrorDTO(boolean err, String msg) {
        this.err = err;
        this.msg = msg;
    }

    public boolean getErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
