package com.example.exception;

import com.example.response.ResponseCode;
import lombok.Data;

/**
 * 业务异常类
 */
@Data
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    public BusinessException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getDesc();
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}