package com.vk.mapper;

import com.vk.model.InspectionQuestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InspectionQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionQuestion record);

    int insertSelective(InspectionQuestion record);

    InspectionQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionQuestion record);

    int updateByPrimaryKey(InspectionQuestion record);

    List<InspectionQuestion> getQuestions(InspectionQuestion record);

    int delQuestions(InspectionQuestion record);

    public List<Map<String,Object>> getQuestionStats(@Param(value = "beginTime")String beginTime,@Param(value = "endTime")String endTime,@Param(value = "areaId")Integer areaId);



}