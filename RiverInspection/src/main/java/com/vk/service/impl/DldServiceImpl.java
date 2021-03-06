package com.vk.service.impl;

import com.vk.mapper.InspectionRecordMapper;
import com.vk.mapper.InspectionStatusMapper;
import com.vk.model.InspectionRecord;
import com.vk.model.InspectionStatus;
import com.vk.response.Result;
import com.vk.service.DldService;
import com.vk.type.InspectionRecordStatus;
import com.vk.util.DateUtil;
import com.vk.util.TextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by wj on 18-7-3.
 */
@Service
public class DldServiceImpl implements DldService{

    @Resource
    private InspectionStatusMapper inspectionStatusMapper;

    @Resource
    private InspectionRecordMapper inspectionRecordMapper;

    @Override
    public Result AchieveNotice(Map<String, String> map) {
        String num =map.get("TaskNum");
        String dealTime = map.get("AchieveTime");
        //String images = map.get("AchieveEventPictures").toString();
        String images = "";
        String content =map.get("AchieveOpinion");
        String result = map.toString();
        Integer count = saveResult(num,content,images,dealTime,result);
        Result operationResult = new Result(Result.FAILED,null,"error");
        if(count!=null&&count>0){
            operationResult.setCode(Result.SUCCESS);
            operationResult.setMsg("操作成功！");
        }
        return operationResult;
    }

    @Override
    public Result DirectRollBack(Map<String, String> map) {
        String num =map.get("TaskNum");
        String rollBackName = map.get("RollBackName");//回退操作人
        String rollBackNote =map.get("RollBackNote");//回退意见
        String dealTime = map.get("RollBackTime");//回退时间
        String content = "回退操作人:"+ rollBackName + " <br/>回退意见 :" + rollBackNote;
        String result = map.toString();
        String images = "" ;
        Integer count = saveResult(num,content,images,dealTime,result);
        Result operationResult = new Result(Result.FAILED,null,"error");
        InspectionRecord inspectionRecord = inspectionRecordMapper.getByCodeOrTaskId(null,num);
        inspectionRecord.setStatus(InspectionRecordStatus.ALTER_FINISH_CHECKING+"");
        if(count!=null&&count>0){
            operationResult.setCode(Result.SUCCESS);
            operationResult.setMsg("操作成功！");
        }
        return operationResult;
    }

    @Override
    public Result SyncTaskAccept(Map<String, String> map) {
        String num =map.get("TaskNum").toString();
        String acceptTime = map.get("AcceptTime").toString();//受理时间
        String infoTypeID = map.get("InfoTypeID").toString();//案件属性
        String infoBC = map.get("InfoBC").toString();//管理内容大类
        String infoSC = map.get("InfoSC").toString();//管理内容小类
        String infoZC = map.get("InfoZC").toString();//管理内容小类
        String infoBCName = map.get("InfoBCName").toString();//管理内容大类名称
        String infoSCName = map.get("InfoSCName").toString();//管理内容小类名称
        String infoZCName = map.get("InfoZCName").toString();//管理内容小类名称
        String address = map.get("Address").toString();//案件发生地址
        String street = map.get("Street").toString();//街道名称
        String community = map.get("Community").toString();//村居名称
        String gridCode = map.get("GridCode").toString();//网格编码
        String x = map.get("X").toString();//X坐标
        String y = map.get("Y").toString();//Y坐标
        String description = map.get("Description").toString();//问题描述
        String acceptNote = map.get("AcceptNote").toString();//受理意见
        String content =  "受理时间:"+acceptTime +"<br/>案件属性:"+infoTypeID + "<br/>管理内容大类:" +infoBC
                + "<br/>管理内容小类:"+infoSC+"<br/>管理内容小类:"+infoZC+"<br/>管理内容大类名称:"+infoBCName+"<br/>管理内容小类名称:"+infoSCName
                +"<br/>管理内容小类名称:"+infoZCName+"<br/>案件发生地址:"+address+"<br/>街道名称:"+street+"<br/>村居名称:"+community
                +"<br/>问题描述:"+description+"<br/>受理意见:"+acceptNote;
        String images = "";
        String result = map.toString();
        Integer count = saveResult(num,content,images,acceptTime,result);
        Result operationResult = new Result(Result.FAILED,null,"error");
        if(count!=null&&count>0){
            operationResult.setCode(Result.SUCCESS);
            operationResult.setMsg("操作成功！");
        }
        return operationResult;
    }

    @Override
    public Result SyncTaskCreate(Map<String, String> map) {
        String num =map.get("TaskNum").toString();//任务号
        String createTime = map.get("CreateTime").toString();//立案时间
        String allEndTime = map.get("AllEndTime").toString();//建议处置截止时间
        String createNote = map.get("CreateNote").toString();//立案意见
        String content = "立案时间:"+ createTime +"<br/>建议处置截止时间:"+allEndTime+"<br/>立案意见"+createNote;
        String result = map.toString();
        String images = "";
        Integer count = saveResult(num,content,images,createTime,result);
        Result operationResult = new Result(Result.FAILED,null,"error");
        if(count!=null&&count>0){
            operationResult.setCode(Result.SUCCESS);
            operationResult.setMsg("操作成功！");
        }
        return operationResult;
    }

    @Override
    public Result SyncTaskDispatch(Map<String, String> map) {
        String num =map.get("TaskNum").toString();//任务号
        String dispatchTime =map.get("DispatchTime").toString();//派遣时间
        String deptCode =map.get("DeptCode").toString();//派遣部门编号
        String deptName =map.get("DeptName").toString();//派遣部门名称
        String execDeptCode =map.get("ExecDeptCode").toString();//主责部门编号
        String execDeptName =map.get("ExecDeptName").toString();//主责部门名称
        String cArriveTime =map.get("CArriveTime").toString();//到场时间要求（分钟数）
        String cSolvingTime =map.get("CSolvingTime").toString();//处置时间要求（分钟数）
        String drriveTime =map.get("ArriveTime").toString();//到场截止时间（格式：yyyy-MM-dd HH24:mm:ss）
        String eolvingTime =map.get("SolvingTime").toString();//处置截止时间（格式：yyyy-MM-dd HH24:mm:ss）
        String solvingNote =map.get("SolvingNote").toString();//派遣意见
        String content = "派遣时间:" + dispatchTime +"<br/>派遣部门名称:"+deptName
                +"<br/>主责部门名称:"+execDeptName+"<br/>到场时间要求（分钟数):"+cArriveTime
                +"<br/>处置时间要求（分钟数）:"+cSolvingTime+"<br/>到场截止时间:"+drriveTime+"<br/>处置截止时间:"+eolvingTime+"<br/>派遣意见:"+solvingNote;
        String result = map.toString();
        String images = "";
        Integer count = saveResult(num,content,images,dispatchTime,result);
        Result operationResult = new Result(Result.FAILED,null,"error");
        if(count!=null&&count>0){
            operationResult.setCode(Result.SUCCESS);
            operationResult.setMsg("操作成功！");
        }
        return operationResult;
    }

    @Override
    public Result SyncTaskCancel(Map<String, String> map) {
        String num =map.get("TaskNum").toString();//任务号
        String cancelNote = map.get("CancelNote").toString();//作废意见
        String cancelTime = map.get("CancelTime").toString();//作废时间
        String content = "作废意见:"+cancelNote +"<br/>作废时间:"+cancelTime;
        String result = map.toString();
        String images = "";
        Integer count = saveResult(num,content,images,cancelTime,result);
        InspectionRecord inspectionRecord = inspectionRecordMapper.getByCodeOrTaskId(null,num);
        inspectionRecord.setStatus(InspectionRecordStatus.ALTER_FINISH_CHECKING+"");
        inspectionRecordMapper.updateByPrimaryKeySelective(inspectionRecord);
        Result operationResult = new Result(Result.FAILED,null,"error");
        if(count!=null&&count>0){
            operationResult.setCode(Result.SUCCESS);
            operationResult.setMsg("操作成功！");
        }
        return operationResult;
    }

    @Override
    public Result SyncTaskEnd(Map<String, String> map) {
        String num =map.get("TaskNum").toString();//任务号
        String endTime = map.get("EndTime").toString();//结案时间
        String lastSolvingTime =map.get("LastSolvingTime").toString();//最终处理时间
        String solvingNote = map.get("SolvingNote").toString();//最终主责单位处理完成意见
        String endNote =map.get("EndNote").toString();//结案意见
        String banliResult = map.get("BanliResult").toString();//结案评判
//        String imageFile =map.get("ImageFile").toString();//核查图片信息
//        String waveFile = map.get("WaveFile").toString();//核查声音或视频信息
        String content = "最终处理时间:"+lastSolvingTime+"<br/>最终主责单位处理完成意见:"+solvingNote+"<br/>结案意见:"
                +endNote+"<br/>结案评判:"+banliResult;
        String result = map.toString();
        String images ="";
        Integer count = saveResult(num,content,images,lastSolvingTime,result);
        Result operationResult = new Result(Result.FAILED,null,"error");
        InspectionRecord inspectionRecord = inspectionRecordMapper.getByCodeOrTaskId(null,num);
        inspectionRecord.setStatus(InspectionRecordStatus.ALTER_FINISH_CHECKING+"");
        inspectionRecordMapper.updateByPrimaryKeySelective(inspectionRecord);
        if(count!=null&&count>0){
            operationResult.setCode(Result.SUCCESS);
            operationResult.setMsg("操作成功！");
        }
        return operationResult;
    }

    @Override
    public Result SyncTaskSolve(Map<String, String> map) {
        String num =map.get("TaskNum").toString();//任务号
        String solveTime = map.get("SolveTime").toString();//处理时间
        String execDeptCode = map.get("ExecDeptCode").toString();//处理部门编号
        String execDeptName = map.get("ExecDeptName").toString();//处理部门名称
        String solvingNote = map.get("SolvingNote").toString();//处理意见
        String result = map.toString();
        String content = "处理部门编号:"+execDeptCode+"<br/>处理部门名称:"+ execDeptName + "<br/>2处理意见:" + solvingNote ;
        String images = "";
        Integer count = saveResult(num,content,images,solveTime,result);
        Result operationResult = new Result(Result.FAILED,null,"error");
        if(count!=null&&count>0){
            operationResult.setCode(Result.SUCCESS);
            operationResult.setMsg("操作成功！");
        }
        return operationResult;
    }

    private Integer saveResult(String num,String content,String images,String dealTime,String result){
        if(TextUtil.isEmpty(num)){
            return null;
        }
        InspectionRecord inspectionRecord = inspectionRecordMapper.getByCodeOrTaskId(null,num);
        if(inspectionRecord!=null){
            Integer recordId = inspectionRecord.getId();
            InspectionStatus inspectionStatus = new InspectionStatus();
            inspectionStatus.setRecordId(recordId);
            inspectionStatus.setContent(content);
            inspectionStatus.setImages(images);
            inspectionStatus.setDealTime(DateUtil.StrToDate(dealTime));
            inspectionStatus.setCreateTime(new Date());
            inspectionStatus.setResult(result);
            Integer count = inspectionStatusMapper.insertSelective(inspectionStatus);
            return count;
        }
        return null;
    }
}
