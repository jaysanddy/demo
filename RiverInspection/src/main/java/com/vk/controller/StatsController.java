package com.vk.controller;

import com.vk.response.Result;
import com.vk.response.ResultData;
import com.vk.service.StatsService;
import com.vk.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by wj on 18-7-4.
 * 统计
 */
@RestController
@RequestMapping("stats")
@Api(value ="/stats", description ="统计接口")
public class StatsController {

    @Resource
    private StatsService statsService;

    @ApiOperation(value="统计巡检时各种问题发生的数量", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "endTime", value = "截止时间", required = false, dataType = "String", paramType = "path"),
    })
    @RequestMapping(value = "getQuestionStats",method = RequestMethod.POST)
    public ResultData getQuestionStats(String beginTime,String endTime){
        return statsService.getQuestionStats(beginTime,endTime);
    }

    @ApiOperation(value="统计区域内问题的整改情况", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "endTime", value = "截止时间", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "riverLevelId", value = "河湖等级", required = false, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "getAreaStats",method = RequestMethod.POST)
    public ResultData getAreaStats(String beginTime,String endTime,Integer riverLevelId){
        return statsService.getAreaStats(beginTime,endTime,riverLevelId);
    }

    @ApiOperation(value="获取本月的第一天和今天的时间", notes="")
    @RequestMapping(value = "getDays",method = RequestMethod.POST)
    public ResultData getDays(){
        Map<String,Object> map = new HashedMap();
        map.put("getFirstDay",DateUtil.StringFormatString(DateUtil.getFirstDay()));
        map.put("getNowDay",DateUtil.fmPattern(new Date(),"yyyy-MM-dd"));
        ResultData resultData = new ResultData(ResultData.SUCCESS,null,null,null,map,"获取成功");
        return resultData;
    }

    @RequestMapping(value = "getAreaStatsExport",method = RequestMethod.POST)
    public Result getAreaStatsExport(String beginTime, String endTime, Integer riverLevelId){
        return statsService.getAreaStatsExport(beginTime,endTime,riverLevelId);
    }

    @RequestMapping(value = "getQuestionStatsExport",method = RequestMethod.POST)
    public Result getQuestionStatsExport(String beginTime,String endTime){
        return statsService.getQuestionStatsExport(beginTime,endTime);
    }

}
