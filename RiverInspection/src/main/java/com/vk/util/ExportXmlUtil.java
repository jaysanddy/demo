package com.vk.util;

import com.vk.Exception.CommonException;
import com.vk.model.InspectionRecord;
import com.vk.request.AreaStatsRequest;
import com.vk.request.QuestionStatsRequest;
import com.vk.response.Result;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weijin on 2018/7/7.
 */
public class ExportXmlUtil {

    public static Result InspectionRecordExport(List<InspectionRecord> list, String dir, String path){
        List rowName = new ArrayList();
        rowName.add("河道名称");
        rowName.add("河道编号");
        rowName.add("标题");
        rowName.add("所属乡镇");
        rowName.add("巡河地点");
        rowName.add("初步评定");
        rowName.add("河道描述");
        rowName.add("总分");
        rowName.add("问题创建时间");
        rowName.add("问题发现人");
        for(InspectionRecord record:list){
            if(record.getFirstAssess() == 0 ){
                record.setFirstAssessCn("优秀");
            }else if(record.getFirstAssess() == 1){
                record.setFirstAssessCn("一般");
            }else{
                record.setFirstAssessCn("较差");
            }

            if(TextUtil.integerNotEmpty(record.getScore())){
                record.setScoreCn(record.getScore()+"分");
            }

            Date createTime =record.getCreateTime();
            record.setCreateTimeCn(DateUtil.fmPattern(createTime,"yyyy-MM-dd"));
        }
        String[] cellRows = {"riverName","riverNo","title","towns","address","firstAssessCn","context","scoreCn","createTimeCn","creator"};
        return PoiExportUtil.poiExport("河流问题",rowName,list,cellRows,dir,path);
    }

    public static Result areaStatsExport(List<Map<String,Object>> list, String dir, String path){
        try{
            List rowName = new ArrayList();
            rowName.add("区县");
            rowName.add("巡查数(条)");
            rowName.add("需整改数(条)");
            rowName.add("巡查整改率(%)");
            rowName.add("已整改数(条)");
            rowName.add("已整改率(%)");
            rowName.add("扣分");
            String[] cellRows = {"areaName","total","al","alp","fal","falp","score"};
            BeanUtils<AreaStatsRequest> beanUtils = new BeanUtils<AreaStatsRequest>();
            List<AreaStatsRequest>  areaStatsRequests=beanUtils.ListMap2JavaBean(list, AreaStatsRequest.class);
            return PoiExportUtil.poiExport("整改统计",rowName,areaStatsRequests,cellRows,dir,path);
        }catch (CommonException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Result questionStatsExport(List<Map<String,Object>> list, String dir, String path){
        try{
            List rowName = new ArrayList();
            rowName.add("区县");
            rowName.add("有生活垃圾等漂浮物");
            rowName.add("有大量绿萍、水草、蓝藻、水葫芦等水生植物");
            rowName.add("有废弃船只");
            rowName.add("正常");
            rowName.add("驳岸破损或坍塌");
            rowName.add("自然滩坡溃口");
            rowName.add("边坡垃圾");
            rowName.add("河岸杂树");
            rowName.add("堤防绿化养护不到位");
            rowName.add("河岸垦种");
            rowName.add("未设置界桩");
            rowName.add("正常");
            rowName.add("占河填埋");
            rowName.add("河道淤塞");
            rowName.add("占河违搭");
            rowName.add("占河围栏养殖（包括养鱼、养鸭、种菱等）");
            rowName.add("有行水障碍物");
            rowName.add("有挡水圩堰和坝埂");
            rowName.add("正常");
            rowName.add("河水发臭变色");
            rowName.add("污水排入");
            rowName.add("正常");
            String[] cellRows = {"areaName","qid5","qid6","qid7","qid8","qid9","qid10",
                    "qid11","qid12","qid13","qid14","qid15","qid16","qid17","qid18",
                    "qid19","qid20","qid21","qid22","qid23","qid24","qid25","qid26"};
            BeanUtils<QuestionStatsRequest> beanUtils = new BeanUtils<QuestionStatsRequest>();
            List<QuestionStatsRequest>  areaStatsRequests=beanUtils.ListMap2JavaBean(list, QuestionStatsRequest.class);
            return PoiExportUtil.poiExport("问题统计",rowName,areaStatsRequests,cellRows,dir,path);
        }catch (CommonException e){
            e.printStackTrace();
        }
        return null;
    }
}
