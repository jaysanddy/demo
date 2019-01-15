package com.vk.service;

import com.vk.model.InspectionResult;
import com.vk.response.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by weijin on 2018/6/28.
 */
public interface InspectionReusltService {
    //复查提交
    Result insertResult(Integer recordId, String context, List photoFiles, Integer status,String recordStatus)throws Exception;

    //复查确认
    Result saveResult(Integer resultId, String context, List photoFiles, Integer status,Integer recordId,String recordStatus)throws Exception ;

    List<InspectionResult> getResult(Map<String,String> map);
}
