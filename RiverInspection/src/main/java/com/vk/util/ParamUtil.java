package com.vk.util;

/**
 * Created by wj on 18-4-28.
 */
public class ParamUtil {

    public static final int pageNum = 1;

    public static final int pageSize = 10;

    public static void initPageParam(Integer pageNum,Integer pageSize){
        if(pageNum==null){
            pageNum = ParamUtil.pageNum;
        }
        if(pageSize==null){
            pageSize = ParamUtil.pageSize;
        }
    }
}
