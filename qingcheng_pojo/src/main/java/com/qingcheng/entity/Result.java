package com.qingcheng.entity;

//返回信息
public class Result {
    //    业务返回码
    private Integer code;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.code = 0;
        this.message = "执行成功";
    }

    //    消息
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

