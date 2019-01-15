package com.vk.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk.request.DemoResult;
import com.vk.response.Result;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weijin on 2018/7/4.
 */
public class BigLinkageUtil {

    public static Result sendBigLink(String urlRequest,String address, String describe, String realName, String phone, String lat, String lng, String time, String code, String imagesUrl,
                                   String codeNum, String areaRemark){
        Result resultData = new Result(Result.FAILED,null,"出现了未知的异常");

        try {
            String result = HttpUtil.getPostData(urlRequest, "address="+"address"+"&describe="+describe+"&realName="+realName
            +"&phone="+phone+"&lat="+lat+"&lng="+lng+"&time="+time+"&code="+code+"&imagesUrl="+imagesUrl+"&codeNum="+codeNum+"&areaRemark="+areaRemark
            );
            Gson gson = new Gson();
            Type type = new TypeToken<Result>(){}.getType();
            Result resultBigLink = gson.fromJson(result,type);
            if(resultBigLink != null){
                resultData.setMsg(resultBigLink.getMsg());
                resultData.setCode(Result.SUCCESS);
                if(resultBigLink.getData()!=null){
                    Map<String,String> resultMap = gson.fromJson((String)resultBigLink.getData(),new TypeToken<Map<String,String>>(){}.getType());
                    resultData.setData(resultMap);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultData;
    }
}
