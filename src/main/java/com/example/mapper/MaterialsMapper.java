package com.example.mapper;

import com.example.pojo.CategoryOption;
import com.example.pojo.Material;
import com.example.request.MaterialQueryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialsMapper {

    List<Material> selectByConditions(MaterialQueryRequest request);

    Material selectById(Integer id);

    void updateDownloadCount(Integer id);

    Integer countTotalResources();

    Integer countSubjects();

    Integer countTodayDownloads();

    Double getAverageRating();

    List<CategoryOption> selectSubjectCategories();

    List<CategoryOption> selectMaterialTypes();
}
