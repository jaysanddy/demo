package com.vk.controller;

import com.vk.response.ResultData;
import com.vk.service.RiverQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by weijin on 2018/6/27.
 */
@RestController
@RequestMapping("riverQuestion")
@Api(value ="/riverQuestion", description ="河流问题集合接口")
public class RiverQuestionController {
    @Resource
    private RiverQuestionService riverQuestionService;

    @ApiOperation(value="获取河流问题", notes="")
    @RequestMapping(value = "getAllRiverQuestion",method = RequestMethod.POST)
    public ResultData getAllRiverQuestion(){
        return riverQuestionService.getAllRiverQuestion();
    }
}
