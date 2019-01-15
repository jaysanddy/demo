package com.vk.mapper;

import com.vk.model.RiverQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RiverQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiverQuestion record);

    int insertSelective(RiverQuestion record);

    RiverQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiverQuestion record);

    int updateByPrimaryKey(RiverQuestion record);

    List<RiverQuestion> getRiverQuestion();

    List<Map<String,Object>> getAllRiverQuestion();
}