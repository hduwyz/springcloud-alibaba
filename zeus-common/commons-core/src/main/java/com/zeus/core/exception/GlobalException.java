package com.zeus.core.exception;

import com.zeus.core.enums.ErrorCode;

public class GlobalException extends RuntimeException{

    //自定义异常码
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public GlobalException(ErrorCode error) {
        // 父类的构造函数；调用底层的Throwable的构造函数，将参数message赋值到detailMessage (Throwable的属性)
        super(error.getDesc());
        //赋值code码
        this.code = error.getCode();
    }

    public GlobalException(String message, Integer code) {
        // 父类的构造函数；调用底层的Throwable的构造函数，将参数message赋值到detailMessage (Throwable的属性)
        super(message);
        //赋值code码
        this.code = code;
    }

    public GlobalException(String message) {
        // 父类的构造函数；调用底层的Throwable的构造函数，将参数message赋值到detailMessage (Throwable的属性)
        super(message);
    }
}
