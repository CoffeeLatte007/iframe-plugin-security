package org.iframe.plugin.security.exception;

/**
 * 认证异常，非法访问时抛出的异常
 * Created by lizhaoz on 2016/1/7.
 */

public class AuthcException extends Exception {
    public AuthcException() {
    }

    public AuthcException(String message) {
        super(message);
    }

    public AuthcException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthcException(Throwable cause) {
        super(cause);
    }


}
