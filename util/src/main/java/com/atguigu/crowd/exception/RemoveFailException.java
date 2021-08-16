package com.atguigu.crowd.exception;

/**
 * @author guyao
 * @create 2021-08-03 12:19
 */
public class RemoveFailException extends RuntimeException{
    private static final long serialVersionUID = 4L;
    public RemoveFailException() {
    }

    public RemoveFailException(String message) {
        super(message);
    }

    public RemoveFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoveFailException(Throwable cause) {
        super(cause);
    }

    public RemoveFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}