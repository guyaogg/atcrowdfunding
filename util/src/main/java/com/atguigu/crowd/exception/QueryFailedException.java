package com.atguigu.crowd.exception;

/**
 * @author guyao
 * @create 2021-08-03 12:10
 */
public class QueryFailedException extends RuntimeException{
    private static final long serialVersionUID = 3L;
    public QueryFailedException() {
    }

    public QueryFailedException(String message) {
        super(message);
    }

    public QueryFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryFailedException(Throwable cause) {
        super(cause);
    }

    public QueryFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}