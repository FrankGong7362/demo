package com.example.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 获取学习资料详情请求参数
 */
@Data
public class MaterialDetailRequest {
    @NotNull(message = "资料ID不能为空")
    private Integer id;
}
