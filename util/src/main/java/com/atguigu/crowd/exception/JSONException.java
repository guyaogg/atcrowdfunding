package com.atguigu.crowd.exception;

/**
 * @author guyao
 * @create 2021-08-04 15:54
 */
public class JSONException extends RuntimeException{
    private static final long serialVersionUID = 6L;
    public JSONException() {
    }

    public JSONException(String message) {
        super(message);
    }

    public JSONException(String message, Throwable cause) {
        super(message, cause);
    }

    public JSONException(Throwable cause) {
        super(cause);
    }

    public JSONException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}