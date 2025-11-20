package com.example.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
/**
 * 下载学习资料请求参数
 */
@Data
public class MaterialDownloadRequest {
    @NotNull(message = "资料ID不能为空")
    private Integer id;
}