package com.example.service.impl;

import com.example.mapper.MaterialsMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.CategoryOption;
import com.example.pojo.Material;
import com.example.pojo.StatisticsInfo;
import com.example.request.MaterialQueryRequest;
import com.example.service.MaterialsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialsServiceImpl implements MaterialsService {

    @Autowired
    private MaterialsMapper materialsMapper;

    @Override
    public PageInfo<Material> getMaterialsList(MaterialQueryRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        List<Material> materials = materialsMapper.selectByConditions(request);
        return new PageInfo<>(materials);
    }

    @Override
    public void downloadMaterial(Integer id, HttpServletResponse response) {
        Material material = materialsMapper.selectById(id);
        if (material != null) {
            // 更新下载次数
            materialsMapper.updateDownloadCount(id);

            // 设置响应头，返回文件流
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + material.getTitle() + ".pdf");

            // 实现文件流输出逻辑
            // ...
        }
    }

    @Override
    public Material getMaterialDetail(Integer id) {
        return materialsMapper.selectById(id);
    }

    @Override
    public StatisticsInfo getStatistics() {
        StatisticsInfo stats = new StatisticsInfo();
        stats.setTotalResources(materialsMapper.countTotalResources());
        stats.setSubjectCount(materialsMapper.countSubjects());
        stats.setTodayDownloads(materialsMapper.countTodayDownloads());
        stats.setAvgRating(materialsMapper.getAverageRating());
        return stats;
    }

    @Override
    public List<CategoryOption> getSubjectCategories() {
        return materialsMapper.selectSubjectCategories();
    }

    @Override
    public List<CategoryOption> getMaterialTypes() {
        return materialsMapper.selectMaterialTypes();
    }
}
