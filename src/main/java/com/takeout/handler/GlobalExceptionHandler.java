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

    @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
    public Result<String> handleSQLIntegrityConstraintViolationException(java.sql.SQLIntegrityConstraintViolationException exception) {
        // 报错信息示例: Duplicate entry 'admin' for key 'employee.idx_username'
        String message = exception.getMessage();
        log.error("数据库约束异常：{}", message);
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String username = split[2]; // 获取冲突的用户名
            return Result.error("用户名 " + username + " 已存在");
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception exception) {
        log.error("系统异常：", exception);
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}
