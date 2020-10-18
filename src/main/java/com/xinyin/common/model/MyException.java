package com.xinyin.common.model;

import lombok.Data;

/**
 * 运行时异常
 */
@Data
public class MyException extends RuntimeException {

    private String code;
    public MyException(String code,String message){
        super(message);
        this.code = code;
    }
}
