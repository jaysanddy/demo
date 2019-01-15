package com.vk.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk.response.Result;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wj on 18-7-3.
 */
public interface DldService {


    /**
     * 结案反馈
     * @param requestMap
     * @return
     */
    public Result AchieveNotice(Map<String,String> requestMap);

    /**
     * 区平台直接回退
     * @param requestMap
     * @return
     */
    public Result DirectRollBack (Map<String,String> requestMap);

    /**
     * 5.1. 受理信息
     * @param requestMap
     * @return
     */
    public Result SyncTaskAccept(Map<String,String> requestMap);

    /**
     * 立案信息
     * @param requestMap
     * @return
     */
    public Result SyncTaskCreate(Map<String,String> requestMap);

    /**
     *派遣信息
     * @param requestMap
     * @return
     */
    public Result SyncTaskDispatch(Map<String,String> requestMap);

    /**
     *不受理（作废）信息
     * @param requestMap
     * @return
     */
    public Result SyncTaskCancel(Map<String,String> requestMap);

    /**
     *案件处理信息
     * @param requestMap
     * @return
     */
    public Result SyncTaskEnd(Map<String,String> requestMap);

    /**
     * 结案
     * @param requestMap
     * @return
     */
    public Result SyncTaskSolve(Map<String,String> requestMap);
}
