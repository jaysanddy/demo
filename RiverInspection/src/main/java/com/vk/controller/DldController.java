package com.vk.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk.response.Result;
import com.vk.service.DldService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzb on 18-7-3.
 * 大联动 反馈调用类
 */
@RestController
@RequestMapping("dld")
public class DldController {

    @Resource
    private DldService dldService;

    /**
     * 结案反馈
     * @param requestMap
     * @return
     */
    @RequestMapping("AchieveNotice")
    public Result AchieveNotice(@RequestParam(name = "requestMap") String requestMap){
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String,String> paramMap = new Gson().fromJson(requestMap,type);
        return dldService.AchieveNotice(paramMap);
    }


    /**
     * 区平台直接回退
     * @param requestMap
     * @return
     */
    @RequestMapping("DirectRollBack")
    public Result DirectRollBack (@RequestParam(name = "requestMap") String requestMap){
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String,String> paramMap = new Gson().fromJson(requestMap,type);
        return dldService.DirectRollBack(paramMap);
    }


    /**
     * 5.1. 受理信息
     * @param requestMap
     * @return
     */
    @RequestMapping("SyncTaskAccept")
    public Result SyncTaskAccept(@RequestParam(name = "requestMap") String requestMap){
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String,String> paramMap = new Gson().fromJson(requestMap,type);
        return null;
    }

    /**
     * 立案信息
     * @param requestMap
     * @return
     */
    @RequestMapping("SyncTaskCreate")
    public Result SyncTaskCreate(@RequestParam(name = "requestMap") String requestMap){
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String,String> paramMap = new Gson().fromJson(requestMap,type);
        return dldService.SyncTaskCreate(paramMap);
    }

    /**
     *派遣信息
     * @param requestMap
     * @return
     */
    @RequestMapping("SyncTaskDispatch")
    public Result SyncTaskDispatch(@RequestParam(name = "requestMap") String requestMap){
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String,String> paramMap = new Gson().fromJson(requestMap,type);
        return dldService.SyncTaskDispatch(paramMap);
    }

    /**
     *不受理（作废）信息
     * @param requestMap
     * @return
     */
    @RequestMapping("SyncTaskCancel")
    public Result SyncTaskCancel(@RequestParam(name = "requestMap") String requestMap){
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String,String> paramMap = new Gson().fromJson(requestMap,type);
        return dldService.SyncTaskCancel(paramMap);
    }

    /**
     *案件处理信息
     * @param requestMap
     * @return
     */
    @RequestMapping("SyncTaskEnd")
    public Result SyncTaskEnd(@RequestParam(name = "requestMap") String requestMap){
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String,String> paramMap = new Gson().fromJson(requestMap,type);
        return dldService.SyncTaskEnd(paramMap);
    }

    /**
     * 结案
     * @param requestMap
     * @return
     */
    @RequestMapping("SyncTaskSolve")
    public Result SyncTaskSolve(@RequestParam(name = "requestMap") String requestMap){
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String,String> paramMap = new Gson().fromJson(requestMap,type);
        return dldService.SyncTaskSolve(paramMap);
    }
}
