package com.cg.requestapi.request.retrofit.exception;


/**
 * 自定义服务器异常
 * @author  sam
 * @date   2019/3/22
 * @version 1.0
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
