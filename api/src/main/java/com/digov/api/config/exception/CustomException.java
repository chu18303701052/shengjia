package com.digov.api.config.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected int errCode;
    /**
     * 错误信息
     */
    protected String errMsg;

    public CustomException() {
        super();
    }

    public CustomException(int errCode, String errMsg) {
        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public CustomException(String errMsg) {
        super();
        this.errCode = 200;
        this.errMsg = errMsg;
    }


    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
