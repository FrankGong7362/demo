package com.example.request;

import lombok.Data;

/**
 * 学习资料列表查询请求参数
 */
@Data
public class MaterialQueryRequest {
    private Integer page;
    private Integer pageSize;
    private String subject;
    private String type;
    private String keyword;
    private String sortBy;
}
