package com.example.exception;

import com.example.response.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 业务异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public R businessExceptionHandler(BusinessException e) {
        return R.fail(e.getCode(), e.getMessage());
    }

    /**
     * 其他异常
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public R exceptionHandler(Exception e) {
        return R.fail(e.getMessage());
    }


}