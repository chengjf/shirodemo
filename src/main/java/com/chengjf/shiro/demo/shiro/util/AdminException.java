package com.chengjf.shiro.demo.shiro.util;

/**
 * Created by jeff on 2017/9/17.
 */
public class AdminException extends RuntimeException {

    private String msg;
    private int code = 500;

    public AdminException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AdminException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public AdminException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public AdminException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
