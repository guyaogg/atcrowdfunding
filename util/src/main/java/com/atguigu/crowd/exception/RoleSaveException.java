package com.atguigu.crowd.exception;

/**
 * @author guyao
 * @create 2021-08-04 15:54
 */
public class RoleSaveException extends RuntimeException{
    private static final long serialVersionUID = 5L;
    public RoleSaveException() {
    }

    public RoleSaveException(String message) {
        super(message);
    }

    public RoleSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleSaveException(Throwable cause) {
        super(cause);
    }

    public RoleSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}