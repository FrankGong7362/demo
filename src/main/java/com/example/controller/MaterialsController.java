package com.example.controller;

import com.example.pojo.CategoryOption;
import com.example.pojo.Material;
import com.example.pojo.StatisticsInfo;
import com.example.request.MaterialDetailRequest;
import com.example.request.MaterialDownloadRequest;
import com.example.request.MaterialQueryRequest;
import com.example.response.R;
import com.example.service.MaterialsService;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialsController {

    @Autowired
    private MaterialsService materialsService;

    @Autowired
    private UserService userService;

    /**
     * 获取学习资料列表
     */
    @RequestMapping("/list")
    @CrossOrigin
    public R<PageInfo<Material>> getMaterialsList(@RequestBody MaterialQueryRequest request) {
        // 设置默认值
        if (request.getPage() == null) {
            request.setPage(1);
        }
        if (request.getPageSize() == null) {
            request.setPageSize(12);
        }

        PageInfo<Material> result = materialsService.getMaterialsList(request);
        for(Material material: result.getList()){
            material.setUploader(userService.queryById(Integer.valueOf(material.getUploader())).getName());
        }
        return R.data(result);
    }

    /**
     * 下载学习资料
     */
    @RequestMapping("/download")
    @CrossOrigin
    public void downloadMaterial(@RequestBody MaterialDownloadRequest request, HttpServletResponse response) {
        materialsService.downloadMaterial(request.getId(), response);
    }

    /**
     * 获取学习资料详情
     */
    @RequestMapping("/detail")
    @CrossOrigin
    public R<Material> getMaterialDetail(@RequestBody MaterialDetailRequest request) {
        Material material = materialsService.getMaterialDetail(request.getId());
        return R.data(material);
    }

    /**
     * 获取统计信息
     */
    @RequestMapping("/statistics")
    @CrossOrigin
    public R<StatisticsInfo> getStatistics() {
        StatisticsInfo statistics = materialsService.getStatistics();
        return R.data(statistics);
    }

    /**
     * 获取学科分类列表
     */
    @RequestMapping("/subjectCategories")
    @CrossOrigin
    public R<List<CategoryOption>> getSubjectCategories() {
        List<CategoryOption> categories = materialsService.getSubjectCategories();
        return R.data(categories);
    }

    /**
     * 获取资料类型列表
     */
    @RequestMapping("/materialTypes")
    @CrossOrigin
    public R<List<CategoryOption>> getMaterialTypes() {
        List<CategoryOption> types = materialsService.getMaterialTypes();
        return R.data(types);
    }
}
