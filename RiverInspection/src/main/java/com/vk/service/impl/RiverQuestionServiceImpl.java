package com.vk.service.impl;

import com.vk.mapper.RiverQuestionMapper;
import com.vk.response.ResultData;
import com.vk.service.RiverQuestionService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by weijin on 2018/6/27.
 */
@Service
public class RiverQuestionServiceImpl implements RiverQuestionService {
    @Resource
    private RiverQuestionMapper riverQuestionMapper;

    @Override
    public ResultData getAllRiverQuestion() {
        ResultData resultData = new ResultData(ResultData.FAILED,null,"查询失败");
        try{
            List<Map<String,Object>> list = riverQuestionMapper.getAllRiverQuestion();
            ResultData dataResult = new ResultData(ResultData.SUCCESS,null,null,null,list,"查询成功");
            return dataResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultData;
    }
}
