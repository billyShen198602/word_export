package com.xinyin.common.util;


import com.xinyin.common.model.Result;

public class ResultUtils {
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result success(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result success(Object obj) {
        Result result = new Result();
        result.setCode("200");
        result.setMsg("成功");
        result.setData(obj);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode("200");
        result.setMsg("成功");
        result.setData(null);
        return result;
    }

}
