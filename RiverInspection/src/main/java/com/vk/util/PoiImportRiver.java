package com.vk.util;

import com.vk.model.JointRiverLevelKey;
import com.vk.model.River;
import com.vk.response.Result;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by wj on 18-7-26.
 */
public class PoiImportRiver {

    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance();


    public static Result importOrderByExcel(String seriesNum, String uploadPath){
        Result resultObj  = new Result(Result.FAILED,null,"error");
        try {
            String path =uploadPath;  //文件存放绝对路径
            Workbook wb = WorkbookFactory.create(new FileInputStream(path));
            XSSFWorkbook xssfworkbook = (XSSFWorkbook) wb;
            XSSFSheet sheet = xssfworkbook.getSheetAt(0);
            List<River> rivers = new ArrayList<>();
            List<JointRiverLevelKey> riverLevels = new ArrayList<>();
            Iterator<Row> rit = sheet.iterator();

            while (rit.hasNext()) {
                Row row = rit.next();
                if(row.getRowNum()==0){
                    continue;
                }
                String riverId = getString(row.getCell(0));
                String riverLevel = getString(row.getCell(2));
                String riverName = getString(row.getCell(3));
                String riverLength = getString(row.getCell(4));
                String beginArea = getString(row.getCell(5));
                String endArea = getString(row.getCell(6));
                String riverArea = getString(row.getCell(7));
                String areas = getString(row.getCell(8));

                System.out.println(riverId);
                if(TextUtil.isEmpty(riverId)){
                    break;
                }
                River river = new River();
                river.setId(Integer.parseInt(riverId));
                river.setAreaId(1);
                river.setLengths(getFormatValue(riverLength,2));
                river.setBeginArea(beginArea);
                river.setEndArea(endArea);
                river.setBasinArea(riverArea);
                river.setAreas(areas);
                river.setRiverName(riverName);
                river.setCreateTime(new Date());
                rivers.add(river);
                riverLevels.addAll(getRiverLevel(Integer.parseInt(riverId),riverLevel));
            }
            Map<String,Object> resultMap = new HashedMap();
            resultMap.put("rivers",rivers);
            resultMap.put("riverLevels",riverLevels);
            resultObj.setData(resultMap);
            resultObj.setCode(Result.SUCCESS);
            resultObj.setMsg("读取成功！");
        }catch (Exception e){
            throw new RuntimeException("读取失败!",e);
        }
        return resultObj;
    }

    public static Double getFormatValue(String value,Integer length){
        if(TextUtil.isEmpty(value)){
            return null;
        }
        DecimalFormat df = null;
        switch (length){
            case 1:
                df = new DecimalFormat("#.0");
                break;
            case 2:
                df = new DecimalFormat("#.00");
                break;
            case 3:
                df = new DecimalFormat("#.000");
                break;
            case 4:
                df = new DecimalFormat("#.0000");
                break;
            default:
                df = new DecimalFormat("#.00");
        }
        String dfResult = df.format(Double.parseDouble(value));
        Double result = Double.parseDouble(dfResult);
        return result;
    }
    public static List<JointRiverLevelKey> getRiverLevel(Integer riverId,String riverLevel){
        List<JointRiverLevelKey> jointRiverLevelKeys = new ArrayList<>();
        String l1 = "市级";
        String l2 = "镇级";
        String l3 = "村级";
        String ll1 = "流域";
        String ll2 = "区域";
        String ll3 = "重要";
        String ll4 = "其他";
        JointRiverLevelKey jointRiverLevelKey = new JointRiverLevelKey();
        if(riverLevel.contains(l1)){
            jointRiverLevelKey.setLevelId(1);
            jointRiverLevelKey.setRiverId(riverId);
            jointRiverLevelKeys.add(jointRiverLevelKey);
            if(riverLevel.contains(ll1)){
                JointRiverLevelKey key2 = new JointRiverLevelKey();
                key2.setLevelId(4);
                key2.setRiverId(riverId);
                jointRiverLevelKeys.add(key2);
            }
            if(riverLevel.contains(ll2)){
                JointRiverLevelKey key2 = new JointRiverLevelKey();
                key2.setLevelId(5);
                key2.setRiverId(riverId);
                jointRiverLevelKeys.add(key2);
            }
            if(riverLevel.contains(ll3)){
                JointRiverLevelKey key2 = new JointRiverLevelKey();
                key2.setLevelId(6);
                key2.setRiverId(riverId);
                jointRiverLevelKeys.add(key2);
            }if(riverLevel.contains(ll4)){
                JointRiverLevelKey key2 = new JointRiverLevelKey();
                key2.setLevelId(7);
                key2.setRiverId(riverId);
                jointRiverLevelKeys.add(key2);
            }
        }
        if(riverLevel.contains(l2)){
            jointRiverLevelKey.setLevelId(2);
            jointRiverLevelKey.setRiverId(riverId);
            jointRiverLevelKeys.add(jointRiverLevelKey);
        }
        if(riverLevel.contains(l3)){
            jointRiverLevelKey.setLevelId(3);
            jointRiverLevelKey.setRiverId(riverId);
            jointRiverLevelKeys.add(jointRiverLevelKey);
        }
        return jointRiverLevelKeys;
    }

    public static String getString(Cell cell) {
        String str = null;
        if(cell == null){
            return str;
        }

        int t = cell.getCellType();
        if (Cell.CELL_TYPE_STRING == t) {
            str = cell.getStringCellValue();
        } else if (Cell.CELL_TYPE_NUMERIC == t) {
            str = numberFormat.format(cell.getNumericCellValue());
            str = str.replaceAll(",", "");
        } else if (Cell.CELL_TYPE_BOOLEAN == t) {
            str = String.valueOf(cell.getBooleanCellValue());
        } else if (Cell.CELL_TYPE_FORMULA == t) {
            str = String.valueOf(cell.getCellFormula());
        } else if (Cell.CELL_TYPE_ERROR == t) {
            str = String.valueOf(cell.getErrorCellValue());
        }

        if (str != null){
            str = str.trim();
        }


        return str;
    }
    public static void main(String[] args) {

        importOrderByExcel("","/home/wj/Pictures/uploadFile/wj.xlsx");
    }
}
