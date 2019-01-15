package com.vk.mapper;

import com.vk.model.InspectionStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionStatus record);

    int insertSelective(InspectionStatus record);

    InspectionStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionStatus record);

    int updateByPrimaryKeyWithBLOBs(InspectionStatus record);

    int updateByPrimaryKey(InspectionStatus record);
}