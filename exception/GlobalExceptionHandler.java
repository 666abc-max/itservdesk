package com.qingda.tliaswebmanagement.exception;

import com.qingda.tliaswebmanagement.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器 一直网上抛出异常 直到controller
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//捕获所有异常
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起操作失败，请联系管理员");
    }
}
