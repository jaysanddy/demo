package com.vk.mapper;

import com.vk.model.Area;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    List<Area> getArea();
//    Company getUserCompany(@Param(value = "userId") Integer userId，@Param(value = "userId") Integer userId);
}