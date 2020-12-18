package com.lilu.base.http.exception;

/**
 * Description:
 * 处理服务器异常
 * @author lilu on 2020/12/18
 * No one knows this better than me
 */
public class ServerException extends RuntimeException {
    private int errCode;
    private String message;

    public ServerException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.message = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
