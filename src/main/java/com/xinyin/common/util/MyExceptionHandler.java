package com.xinyin.common.util;

import com.xinyin.common.model.MyException;
import com.xinyin.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if(e instanceof MyException) {
            MyException myException = (MyException)e;
            return ResultUtils.error(myException.getCode(),myException.getMessage());
        }else{
            log.error("系统异常",e);
            return ResultUtils.error(ResultEnum.SERVER_ERROR.getCode(), ResultEnum.SERVER_ERROR.getDesc());
        }
    }

}
