package com.vk.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk.request.DemoResult;
import com.vk.response.Result;
import com.vk.response.ResultData;
import com.vk.service.InspectionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijin on 2018/6/25.
 */
@RestController
@RequestMapping("inspectionRecord")
@Api(value ="/inspectionRecord", description ="问题接口")
public class InspectionRecordController {
    @Resource
    private InspectionRecordService inspectionRecordService;

    @ApiOperation(value="创建发布问题", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "地区id", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "riverName", value = "河流名称", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "riverId", value = "河流Id", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "riverNo", value = "河流编号", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "towns", value = "所属乡镇", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "lat", value = "经度", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "lng", value = "纬度", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "address", value = "当前所在位置", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "firstAssess", value = "河道评定", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "describe", value = "河道描述", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "photoFiles", value = "现场图片", required = false, dataType = "list", paramType = "path"),
            @ApiImplicitParam(name = "score", value = "河道总分", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "rectification", value = "是否需要整改", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "detail", value = "选择河湖问题", required = false, dataType = "list", paramType = "path"),
    })
    @RequestMapping(value = "createRecord",method = RequestMethod.POST)
    public Result createRecord(@RequestParam Integer areaId,  @RequestParam String title,  @RequestParam String riverName,  @RequestParam Integer riverId,  @RequestParam String riverNo,@RequestParam   String towns, @RequestParam  String lat, @RequestParam  String lng,@RequestParam   String address, @RequestParam  Integer firstAssess,  String describe, String photoFiles, @RequestParam  Integer score, @RequestParam  Integer rectification,@RequestParam   String detail) throws Exception {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<DemoResult>>(){}.getType();
        List<DemoResult> demoResults = gson.fromJson(detail,type);

        List phonelist =gson.fromJson(photoFiles,new TypeToken<List<String>>(){}.getType());
        return inspectionRecordService.createRecord(areaId,title, riverName, riverId, riverNo, towns, lat, lng, address, firstAssess, describe, phonelist, score, rectification,demoResults);
    }

    @ApiOperation(value="获取问题接口", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "states", value = "状态", required = true, dataType = "String[]", paramType = "path"),
            @ApiImplicitParam(name = "page", value = "页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "rows", value = "码数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "creatorId", value = "用户id", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "getRecord",method = RequestMethod.POST)
    public ResultData getRecord(String[] states,String riverName,String title,String startDate,String endDate,Integer firstAssess,  @RequestParam(name = "page",defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "rows",defaultValue = "10")Integer pageSize, Integer creatorId){
        return inspectionRecordService.getRecord(states,riverName,title,startDate,endDate,firstAssess, pageNum, pageSize, creatorId);
    }

    @ApiOperation(value="获取问题接口", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recordId", value = "问题id", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "getRecordById",method = RequestMethod.POST)
    public ResultData getRecordById(Integer recordId){
        return inspectionRecordService.getRecordById(recordId);
    }

    @ApiOperation(value="获取首页统计", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "creatorId", value = "用户Id", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "getRecordCount",method = RequestMethod.POST)
    public ResultData getRecordCount(Integer creatorId){
        return inspectionRecordService.getRecordCount(creatorId);
    }

    @ApiOperation(value="普通管理员提交问题给大联动或者选择完成", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recordId", value = "这个问题的id", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "status", value = "问题的状态", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "riverName", value = "河流名称", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "riverId", value = "河流Id", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "riverNo", value = "河流编号", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "towns", value = "所属乡镇", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "lat", value = "经度", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "lng", value = "纬度", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "address", value = "当前所在位置", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "firstAssess", value = "河道评定", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "describe", value = "河道描述", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "photoFiles", value = "现场图片", required = false, dataType = "list", paramType = "path"),
            @ApiImplicitParam(name = "score", value = "河道总分", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "rectification", value = "是否需要整改", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "detail", value = "选择河湖问题", required = false, dataType = "list", paramType = "path"),
    })
    @RequestMapping(value="updateRecord",method = RequestMethod.POST)
    public Result updateRecord(@RequestParam Integer recordId,@RequestParam String address,@RequestParam String lat,@RequestParam String lng,@RequestParam String status , @RequestParam String title,  @RequestParam  Integer firstAssess,  String describe,  String photoFiles, @RequestParam  Integer score, @RequestParam  Integer rectification,@RequestParam   String detail, String code, Integer areaId)throws  Exception{
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<DemoResult>>(){}.getType();
        List<DemoResult> demoResults = gson.fromJson(detail,type);

        List phonelist =gson.fromJson(photoFiles,new TypeToken<List<String>>(){}.getType());
        return inspectionRecordService.updateRecord(recordId,address,lat,lng,status,title, firstAssess, describe, phonelist, score, rectification,demoResults,code,areaId);
    }

    @ApiOperation(value="获取问题的分布", notes="默认查当月")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "区域Id", required = false, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "endTime", value = "截止时间", required = false, dataType = "String", paramType = "path"),
    })
    @RequestMapping(value = "getRecordLocations",method = RequestMethod.GET)
    public ResultData getRecordLocations(Integer areaId,String beginTime,String endTime){
        return inspectionRecordService.getRecordLocations(areaId,beginTime,endTime);
    }

    @RequestMapping(value = "riverRecordManagerExport",method = RequestMethod.POST)
    public Result riverRecordManagerExport(String[] states,String riverName,String title,String startDate,String endDate,Integer firstAssess){
        return inspectionRecordService.riverRecordManagerExport(states,riverName,title,startDate,endDate,firstAssess);
    }
}
