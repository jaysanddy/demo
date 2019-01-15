package com.vk.util;

import com.vk.model.InspectionRecord;
import com.vk.response.Result;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.util.TypeUtils;

import javax.xml.soap.Text;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weijin on 2018/7/7.
 */
public class PoiExportUtil {

    public static Result poiExport(String sheetTitle, List rowName,List<?> T,String[] cellRows,String dir,String path){
        Method metd = null;
        String fdname = null;
        HSSFCell cell ;
        HSSFCell cellTitle;

        // 创建excel
        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建sheet

        HSSFSheet sheet = wb.createSheet(sheetTitle);

        // 创建一行
        HSSFRow rowTitle = sheet.createRow(0);

        // 创建标题栏样式
        HSSFCellStyle styleTitle = wb.createCellStyle();
        styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
        HSSFFont fontTitle = wb.createFont();
        // 宋体加粗
        fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontTitle.setFontName("宋体");
        fontTitle.setFontHeight((short) 200);
        styleTitle.setFont(fontTitle);

        // 在行上创建1列
        for(int i=0;i<rowName.size();i++){
            // 列标题及样式
            if(i == 0){
                cellTitle = rowTitle.createCell(0);
            }else {
                cellTitle = rowTitle.createCell(i);
            }
            cellTitle.setCellValue(rowName.get(i).toString());
            cellTitle.setCellStyle(styleTitle);
        }
        HSSFCellStyle styleCenter = wb.createCellStyle();
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中

        try{
            int fielNum = 0;
            if(T.size()<=0){
                Result result = new Result(Result.FAILED,null,"暂无记录，无法导出");
            }
            for(Object object:T){
                Class clazz =object.getClass();
                Field[] fds = clazz.getDeclaredFields();
                HSSFRow row = sheet.createRow(fielNum + 1);
                for(int i=0;i<cellRows.length;i++){
                    for (Field field : fds) {
                        fdname = field.getName();// 得到字段名，
                        metd = clazz.getMethod("get" + change(fdname), null);
                        Object name = metd.invoke(object, null);
                        if(name != null && fdname.equals(cellRows[i])){
                            if(i == 0){
                                cell = row.createCell(0);
                            }else{
                                cell = row.createCell(i);
                            }
                            cell.setCellValue(name.toString());
                            cell.setCellStyle(styleCenter);
                        }
                    }
                }
                fielNum++;
            }
            //String filePath = "uploadFile/checkRecord/excel/"+DateUtil.fmPattern(new Date(),"yyyy-MM-dd ")+ DateUtil.getRandom(1000,9999)+".xls";
            String filePath = path+DateUtil.fmPattern(new Date(),"yyyy-MM-dd ")+ DateUtil.getRandom(1000,9999)+".xls";
            //String fileDir = "D:/wj/";
            String fileDir = dir;
            FileUtil.delete(fileDir);
            File useFile = new File(fileDir+path);
            if(!useFile.exists()){
                useFile.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(fileDir+filePath);
            wb.write(fout);
            fout.close();
            wb.close();
            System.out.println("导出完成！");
            Result result = new Result();
            result.setCode(Result.SUCCESS);
            result.setData(filePath);
            result.setMsg("导出成功!");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            Result result = new Result(Result.FAILED,null,"导出失败");
            return result;
        }
    }

    public static String change(String src) {
        if (src != null) {
            StringBuffer sb = new StringBuffer(src);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } else {
            return null;
        }
    }

    public static void main(String[] args){
        List rowName = new ArrayList();
        rowName.add("第一");
        rowName.add("第二");
        rowName.add("第三");

        List<InspectionRecord> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            InspectionRecord inspectionRecord = new InspectionRecord();
            inspectionRecord.setStatus("不错"+i);
            inspectionRecord.setCode("很好"+i);
            inspectionRecord.setId(i);
            inspectionRecord.setCreator("wo"+i);
            list.add(inspectionRecord);
        }
        Map<String,Object> map = new HashedMap();
        map.put("0","code");
        map.put("1","id");
        map.put("2","creator");
        map.put("3","status");
        String[] cellRows = {"code","id","creator","status"};
        //poiExport("很好",rowName,list,cellRows);
    }
}
