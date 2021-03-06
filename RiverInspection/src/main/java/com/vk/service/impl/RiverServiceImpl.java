package com.vk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vk.mapper.JointRiverLevelMapper;
import com.vk.mapper.RiverMapper;
import com.vk.model.JointRiverLevelKey;
import com.vk.model.River;
import com.vk.response.Result;
import com.vk.response.ResultData;
import com.vk.service.RiverService;
import com.vk.util.PoiImportRiver;
import com.vk.util.TextUtil;
import net.sf.jsqlparser.statement.select.Join;
import org.apache.commons.collections.map.HashedMap;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.soap.Text;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weijin on 2018/6/21.
 */
@Service
public class RiverServiceImpl implements RiverService {
    @Resource
    private RiverMapper riverMapper;
    @Resource
    private JointRiverLevelMapper jointRiverLevelMapper;

    @Transactional
    @Override
    public Result createRiver(String riverName, Double lengths, String beginArea, String endArea, String basinArea, String remark, String areas, Integer areaId, String[] riverLevelIds,String riverNo)throws  Exception {
        Result result = new Result(ResultData.FAILED,null,"插入失败");
        try{
            River river = new River();
            if(TextUtil.isNotEmpty(riverName)){
                river.setRiverName(riverName);
            }
            if(lengths != null && lengths.toString() != ""){
                river.setLengths(lengths);
            }
            if(TextUtil.isNotEmpty(beginArea)){
                river.setBeginArea(beginArea);
            }
            if(TextUtil.isNotEmpty(endArea)){
                river.setEndArea(endArea);
            }
            if(TextUtil.isNotEmpty(basinArea)){
                river.setBasinArea(basinArea);
            }
            if(TextUtil.isNotEmpty(remark)){
                river.setRemark(remark);
            }
            if(TextUtil.isNotEmpty(areas)){
                river.setAreas(areas);
            }
            if(TextUtil.integerNotEmpty(areaId)){
                river.setAreaId(areaId);
            }
            if(TextUtil.isNotEmpty(riverNo)){
                river.setRiverNo(riverNo);
            }
            river.setCreateTime(new Date());
            Integer ok = riverMapper.insertSelective(river);
            if(ok > 0){
                if(riverLevelIds.length>0){
                    for(String id:riverLevelIds){
                        JointRiverLevelKey jointRiverLevelKey = new JointRiverLevelKey();
                        jointRiverLevelKey.setRiverId(river.getId());
                        jointRiverLevelKey.setLevelId(Integer.valueOf(id));

                        jointRiverLevelMapper.insertSelective(jointRiverLevelKey);
                    }
                }
               result.setMsg("添加成功");
               result.setCode(Result.SUCCESS);
            }else{
                result.setMsg("添加失败");
            }
        }catch (Exception e){
            throw new RuntimeException("",e.getCause());
        }
        return result;
    }

    @Override
    public ResultData getRivers(Integer riverId,String riverName, Integer areaId, Integer riverLevelId,Integer pageNum,Integer pageSize) {
        ResultData resultData = new ResultData(ResultData.FAILED,null,"查询失败");
        try{
            Page page = PageHelper.startPage(pageNum,pageSize,true);
            River river = new River();
            river.setId(riverId);
            river.setRiverName(riverName);
            river.setAreaId(areaId);
            river.setRiverLevelId(riverLevelId);
            List<River> list = riverMapper.getByteRivers(river);
            Long total = page.getTotal();
            Integer pages = page.getPages();
            ResultData dataResult = new ResultData(ResultData.SUCCESS,pageNum,total,pages,list,"查询成功");
            return dataResult;
        }catch (Exception e){
            e.printStackTrace();
            return resultData;
        }
    }

    @Override
    public ResultData getRiverById(Integer riverId, String riverName, Integer areaId, Integer riverLevelId) {
        ResultData resultData = new ResultData(ResultData.FAILED,null,"查询失败");
        try{
            River river = new River();
            river.setId(riverId);
            river.setRiverName(riverName);
            river.setAreaId(areaId);
            List<River> list = riverMapper.getByteRivers(river);
            ResultData dataResult = new ResultData(ResultData.SUCCESS,null,null,null,list,"查询成功");
            return dataResult;
        }catch (Exception e){
            e.printStackTrace();
            return resultData;
        }
    }

    @Override
    public ResultData getRiversByApp(String riverName, Integer areaId, Integer riverLevelId) {
        ResultData resultData = new ResultData(ResultData.FAILED,null,"查询失败");
        try {
            River river = new River();
            river.setRiverName(riverName);
            river.setAreaId(areaId);
            List<River> list = riverMapper.getByteRivers(river);
            ResultData dataResult = new ResultData(ResultData.SUCCESS,null,null,null,list,"查询成功");
            return dataResult;
        }catch (Exception e){
            e.printStackTrace();
            return resultData;
        }
    }

    @Override
    public Result updateRiver(Integer riverId, String riverName, Double lengths, String beginArea, String endArea, String basinArea, String remark, String areas, Integer areaId, String[] riverLevelIds, String riverNo) {
        Result result = new Result(ResultData.FAILED,null,"插入失败");
        try{
            River river = new River();
            if(TextUtil.isNotEmpty(riverName)){
                river.setRiverName(riverName);
            }
            if(lengths != null && lengths.toString() != ""){
                river.setLengths(lengths);
            }
            if(TextUtil.isNotEmpty(beginArea)){
                river.setBeginArea(beginArea);
            }
            if(TextUtil.isNotEmpty(endArea)){
                river.setEndArea(endArea);
            }
            if(TextUtil.isNotEmpty(basinArea)){
                river.setBasinArea(basinArea);
            }
            if(TextUtil.isNotEmpty(remark)){
                river.setRemark(remark);
            }
            if(TextUtil.isNotEmpty(areas)){
                river.setAreas(areas);
            }
            if(TextUtil.integerNotEmpty(areaId)){
                river.setAreaId(areaId);
            }
            if(TextUtil.isNotEmpty(riverNo)){
                river.setRiverNo(riverNo);
            }
            river.setId(riverId);
            Integer ok = riverMapper.updateByPrimaryKeySelective(river);
            if(ok>0){
                jointRiverLevelMapper.deleteRiverLevel(riverId);
                if(riverLevelIds.length>0){
                    for(String id:riverLevelIds){
                        JointRiverLevelKey jointRiverLevelKey = new JointRiverLevelKey();
                        jointRiverLevelKey.setRiverId(river.getId());
                        jointRiverLevelKey.setLevelId(Integer.valueOf(id));

                        jointRiverLevelMapper.insertSelective(jointRiverLevelKey);
                    }
                }
                result.setMsg("修改成功");
                result.setCode(Result.SUCCESS);
            }else{
                result.setMsg("修改失败");
            }
        }catch (Exception e){
            throw new RuntimeException("",e.getCause());
        }
        return result;
    }

    @Transactional
    @Override
    public Result importRivers() {
        Result result = PoiImportRiver.importOrderByExcel("","/home/wj/Pictures/uploadFile/wj.xlsx");
        Map<String,Object> resultMap = (Map<String,Object>) result.getData();
        List<River> rivers = (List<River>)resultMap.get("rivers");
        List<JointRiverLevelKey> jointRiverLevelKeys = (List<JointRiverLevelKey>) resultMap.get("riverLevels");
        for(int i=0;i<rivers.size();i++){
            River river = rivers.get(i);
            riverMapper.insertSelective(river);
        }
        for(int i=0;i<jointRiverLevelKeys.size();i++){
            JointRiverLevelKey jointRiverLevelKey = jointRiverLevelKeys.get(i);
            jointRiverLevelMapper.insertSelective(jointRiverLevelKey);
        }
        return new Result(Result.SUCCESS,null,"success");
    }
}
