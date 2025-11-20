package com.example.service;

import com.example.pojo.CategoryOption;
import com.example.pojo.Material;
import com.example.pojo.StatisticsInfo;
import com.example.request.MaterialQueryRequest;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface MaterialsService {

    /**
     * 获取学习资料列表
     */
    PageInfo<Material> getMaterialsList(MaterialQueryRequest request);

    /**
     * 下载学习资料
     */
    void downloadMaterial(Integer id, HttpServletResponse response);

    /**
     * 获取学习资料详情
     */
    Material getMaterialDetail(Integer id);

    /**
     * 获取统计信息
     */
    StatisticsInfo getStatistics();

    /**
     * 获取学科分类列表
     */
    List<CategoryOption> getSubjectCategories();

    /**
     * 获取资料类型列表
     */
    List<CategoryOption> getMaterialTypes();
}

