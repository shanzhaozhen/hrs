package com.hbjs.hrsapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import com.hbjs.hrscommon.enums.ResultType;
import com.hbjs.hrscommon.vo.ResultBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultBody<?> handleIllegalArgumentException(Exception e) {
        log.warn("未知错误：{0}", e);
        return new ResultBody<>().setCode(ResultType.FAILURE).setMessage("未知异常错误").setData(e.getMessage());
    }

    /**
     * 断言事件监听
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultBody<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("未知错误：{0}", e);
        return new ResultBody<>().setCode(ResultType.FAILURE).setMessage(e.getMessage());
    }

    /**
     * 监听表单验证错误信息
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBody<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("监听表单验证错误：{0}", e);
        return new ResultBody<>().setCode(ResultType.FAILURE).setMessage(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 监听SQL执行错误信息
     * @param e
     * @return
     */
    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultBody<?> handleMyBatisSystemException(MyBatisSystemException e) {
        log.warn("SQL执行错误：{0}", e);
        return new ResultBody<>().setCode(ResultType.FAILURE).setMessage("执行失败").setData(e.getMessage());
    }

}
