package com.takeout.handler;

import com.takeout.constant.MessageConstant;
import com.takeout.exception.BaseException;
import com.takeout.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Result<String> handleBaseException(BaseException exception) {
        log.error("业务异常：{}", exception.getMessage());
        return Result.error(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception exception) {
        log.error("系统异常：", exception);
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}
