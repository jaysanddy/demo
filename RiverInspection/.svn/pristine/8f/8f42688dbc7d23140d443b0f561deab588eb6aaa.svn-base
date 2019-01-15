package com.vk.mapper;

import com.vk.model.InspectionResult;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InspectionResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionResult record);

    int insertSelective(InspectionResult record);

    InspectionResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionResult record);

    int updateByPrimaryKey(InspectionResult record);

    List<InspectionResult> getResult(Map<String,String> map);
}