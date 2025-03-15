package org.example.webmanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.webmanagement.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("出错了",e);
        return Result.error("出错了，请联系管理员");
    }
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错了",e);
        String message=e.getMessage();
        int i=message.indexOf("Duplicate entry");
        String errMsg=message.substring(i);
        String[] arr=errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }
}
