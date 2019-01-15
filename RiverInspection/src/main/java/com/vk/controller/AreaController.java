package com.vk.controller;

import com.vk.model.Area;
import com.vk.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by weijin on 2018/7/2.
 */
@RestController
@RequestMapping("area")
@Api(value ="/area", description ="区域")
public class AreaController {

    @Resource
    private AreaService areaService;

    @ApiOperation(value="查询所有区域", notes="")
    @ApiImplicitParams({})
    @RequestMapping("getArea")
    public List<Area> getArea() {
        return areaService.getArea();
    }
}
