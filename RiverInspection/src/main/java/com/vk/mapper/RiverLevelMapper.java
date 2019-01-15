package com.vk.mapper;

import com.vk.model.RiverLevel;

public interface RiverLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiverLevel record);

    int insertSelective(RiverLevel record);

    RiverLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiverLevel record);

    int updateByPrimaryKey(RiverLevel record);

}