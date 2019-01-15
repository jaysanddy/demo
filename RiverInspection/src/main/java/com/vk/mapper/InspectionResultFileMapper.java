package com.vk.mapper;

import com.vk.model.InspectionResultFile;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionResultFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionResultFile record);

    int insertSelective(InspectionResultFile record);

    InspectionResultFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionResultFile record);

    int updateByPrimaryKey(InspectionResultFile record);

    int delResultFile(InspectionResultFile record);

}