package com.example.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公用返回状态码
 */
@AllArgsConstructor
@Getter
public enum ResponseCode {
    /**
     * 用户名已存在
     */
    USERNAME_EXIST(1001, "用户名已存在"),
    /**
     * 成功
     */
    SUCCESS(200, "操作成功！"),
    /**
     * 错误
     */
    ERROR(500, "操作失败！");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String desc;
}
