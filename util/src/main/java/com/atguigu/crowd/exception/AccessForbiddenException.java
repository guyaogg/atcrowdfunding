package com.atguigu.crowd.exception;

/**
 * 未登录错误
 * @author guyao
 * @create 2021-08-02 17:29
 */
public class AccessForbiddenException extends RuntimeException{
    private static final long serialVersionUID = 2L;
    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
