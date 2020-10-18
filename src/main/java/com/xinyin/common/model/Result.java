package com.xinyin.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 结果类
 */
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = -904712610565038351L;
    private String code;
    private String msg;
    private Object data;

}
