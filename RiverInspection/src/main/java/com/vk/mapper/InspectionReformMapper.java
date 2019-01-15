package com.vk.mapper;

import com.vk.model.InspectionReform;

public interface InspectionReformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionReform record);

    int insertSelective(InspectionReform record);

    InspectionReform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionReform record);

    int updateByPrimaryKey(InspectionReform record);
}