package com.zeus.core.enums;

public enum CodeEnum {

    SUCCESS(200, "成功"),
    ERROR(500, "失败");

    private Integer code;
    private String msg;
    CodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
