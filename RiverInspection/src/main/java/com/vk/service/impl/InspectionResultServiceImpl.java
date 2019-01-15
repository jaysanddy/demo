package com.vk.service.impl;

import com.vk.mapper.InspectionRecordMapper;
import com.vk.mapper.InspectionResultFileMapper;
import com.vk.mapper.InspectionResultMapper;
import com.vk.model.InspectionRecord;
import com.vk.model.InspectionResultFile;
import com.vk.model.InspectionResult;
import com.vk.response.Result;
import com.vk.response.ResultData;
import com.vk.service.InspectionReusltService;
import com.vk.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weijin on 2018/6/28.
 */
@Service
public class InspectionResultServiceImpl implements InspectionReusltService {
    @Resource
    private InspectionResultMapper inspectionReusltMapper;
    @Resource
    private InspectionResultFileMapper inspectionResultFileMapper;
    @Value("${project.uploadFilePath}")
    private String projectUrl;
    @Resource
    private InspectionRecordMapper inspectionRecordMapper;

    @Transactional
    @Override
    public Result insertResult(Integer recordId, String context, List photoFiles, Integer status,String recordStatus)throws Exception {
        Result result = new Result(ResultData.FAILED,null,"插入失败");
        try {
            InspectionRecord inspectionRecord = new InspectionRecord();
            inspectionRecord.setId(recordId);
            inspectionRecord.setStatus(recordStatus);
            inspectionRecordMapper.updateByPrimaryKeySelective(inspectionRecord);

            InspectionResult inspectionReuslt =new InspectionResult();
            inspectionReuslt.setRecordId(recordId);
            inspectionReuslt.setContext(context);
            inspectionReuslt.setStatus(status);
            inspectionReuslt.setCreateTime(new Date());
            inspectionReusltMapper.insert(inspectionReuslt);

            if(photoFiles.size()>0){
                for(int i=0;i<photoFiles.size();i++){
                    String filename = FileUtil.base64ToFile(projectUrl,photoFiles.get(i).toString());
                    InspectionResultFile file = new InspectionResultFile();
                    file.setType(0);
                    file.setCreateTime(new Date());
                    file.setResultId(inspectionReuslt.getId());
                    file.setFileName(filename);
                    inspectionResultFileMapper.insertSelective(file);
                }
            }
            result.setCode(Result.SUCCESS);
            result.setMsg("插入成功");
        }catch (Exception e){
            throw new RuntimeException("",e.getCause());
        }
        return result;
    }

    @Transactional
    @Override
    public Result saveResult(Integer resultId, String context, List photoFiles, Integer status,Integer recordId,String recordStatus)throws Exception {
        Result result = new Result(ResultData.FAILED,null,"插入失败");
        try{
            InspectionRecord inspectionRecord = new InspectionRecord();
            inspectionRecord.setId(recordId);
            inspectionRecord.setStatus(recordStatus);
            inspectionRecordMapper.updateByPrimaryKeySelective(inspectionRecord);

            InspectionResult inspectionReuslt =new InspectionResult();
            inspectionReuslt.setId(resultId);
            inspectionReuslt.setContext(context);
            inspectionReuslt.setStatus(status);
            inspectionReusltMapper.updateByPrimaryKeySelective(inspectionReuslt);

            InspectionResultFile reusltFilefile = new InspectionResultFile();
            reusltFilefile.setId(resultId);
            inspectionResultFileMapper.delResultFile(reusltFilefile);
            if(photoFiles.size()>0){
                for(int i=0;i<photoFiles.size();i++){
                    InspectionResultFile file = new InspectionResultFile();
                    String filename = FileUtil.base64ToFile(projectUrl,photoFiles.get(i).toString());
                    file.setType(0);
                    file.setCreateTime(new Date());
                    file.setResultId(inspectionReuslt.getId());
                    file.setFileName(filename);
                    inspectionResultFileMapper.insertSelective(file);
                }
            }
            result.setCode(Result.SUCCESS);
            result.setMsg("插入成功");
        }catch (Exception e){
            throw new RuntimeException("",e.getCause());
        }
        return result;
    }

    @Override
    public List<InspectionResult> getResult(Map<String, String> map) {
        return inspectionReusltMapper.getResult(map);
    }
}
