package com.vk.Exception;

/**
 * Created by weijin on 2018/7/10.
 */
public class CommonException extends Exception {
    // 原始异常
    private Throwable target;

    // 开发提供异常提示内容
    private String errormsg = "";

    public Throwable getTargetException() {
        return target;
    }

    public Throwable getCause() {
        return target;
    }

    protected CommonException() {
        super((Throwable) null);
    }

    public CommonException(Throwable target, String s) {
        super(s, null);
        this.target = target;
        this.errormsg = s;
    }

    public CommonException(Throwable target) {
        super((Throwable) null);
        this.target = target;
    }

    public String getErrormsg() {
        return errormsg;
    }
}
