package com.vk.response;

/**
 * Created by wj on 18-4-19.
 */
public class Result {


    public static final int SUCCESS = 200;

    public static final int FAILED = 201;

    private Integer code;

    private String msg;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {

    }

    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
