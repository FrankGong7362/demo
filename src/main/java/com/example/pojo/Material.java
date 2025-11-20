package com.example.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 学习资料实体
 */
@Data
public class Material {
    private Integer id;
    private String title;
    private String description;
    private String subject;
    private String type;
    private String uploader;
    private Date uploadTime;
    private Integer downloadCount;
    private Double rating;
    private Integer reviewCount;
    private String fileSize;
    private String filePath;
}
