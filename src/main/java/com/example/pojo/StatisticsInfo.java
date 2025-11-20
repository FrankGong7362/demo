package com.example.pojo;

import lombok.Data;

/**
 * 统计信息实体
 */
@Data
public class StatisticsInfo {
    private Integer totalResources;
    private Integer subjectCount;
    private Integer todayDownloads;
    private Double avgRating;
}
