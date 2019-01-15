package com.vk.service;

import com.vk.request.DemoResult;
import com.vk.request.InspectionAnswerQuestions;
import com.vk.response.Result;
import com.vk.response.ResultData;

import java.util.List;

/**
 * Created by weijin on 2018/6/25.
 */
public interface InspectionRecordService {
    //创建问题
    Result createRecord (Integer areaId,String title,String riverName,Integer riverId,String riverNo,String towns,String lat,String lng,String address,Integer firstAssess,String describe,List photoFiles,Integer score,Integer rectification,List<DemoResult> detail)throws Exception;

    //获取问题列表
    ResultData getRecord(String[] states,String riverName,String title,String startDate,String endDate,Integer firstAssess,Integer pageNum,Integer pageSize,Integer creatorId);

    //获取某个问题的详细情况
    ResultData getRecordById(Integer recordId);

    //获得首页的统计
    ResultData getRecordCount(Integer creatorId);

    Result updateRecord(Integer recordId,String address,String lat,String lng,String status, String title, Integer firstAssess, String context, List photoFiles, Integer score, Integer rectification,List<DemoResult> detail,String code,Integer areaId)throws Exception;

    //获取问题的位置分布
    public ResultData getRecordLocations(Integer areaId,String beginTime,String endTime);

    public Result riverRecordManagerExport(String[] states,String riverName,String title,String startDate,String endDate,Integer firstAssess);
}
