package com.vk.service;

import com.vk.response.Result;
import com.vk.response.ResultData;

/**
 * Created by weijin on 2018/6/21.
 */
public interface RiverService {

    //创建河流
    Result createRiver(String riverName,Double lengths,String beginArea,String endArea,String basinArea,String remark,String areas,Integer areaId,String[] riverLevelIds,String riverNo)throws  Exception;

    //获取河流
    ResultData getRivers(Integer riverId,String riverName,Integer areaId,Integer riverLevelId,Integer pageNum,Integer pageSize);

    //获取河流
    ResultData getRiverById(Integer riverId,String riverName,Integer areaId,Integer riverLevelId);

    //获取河流
    ResultData getRiversByApp(String riverName,Integer areaId,Integer riverLevelId);

    //修改河流
    Result updateRiver(Integer riverId,String riverName,Double lengths,String beginArea,String endArea,String basinArea,String remark,String areas,Integer areaId,String[] riverLevelIds,String riverNo);

    //导入河湖
    Result importRivers();
}
