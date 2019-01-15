package com.vk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vk.mapper.UserRoleMapper;
import com.vk.model.UserRoleKey;
import com.vk.request.UserRoleRequest;
import com.vk.response.Result;
import com.vk.response.ResultData;
import com.vk.response.UserRoleResponse;
import com.vk.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by weijin on 2018/6/19.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public ResultData getUserRole(Integer userId,Integer pageNum,Integer pageSize) {
        ResultData resultData = new ResultData(ResultData.FAILED,null,"查询失败");
        try{
            Page page = PageHelper.startPage(pageNum,pageSize,true);
            UserRoleRequest  userRoleRequest = new UserRoleRequest();
            userRoleRequest.setUserId(userId);
            List<UserRoleResponse> userRoleResponseList= userRoleMapper.getUserRole(userRoleRequest);
            Long total = page.getTotal();
            Integer pages = page.getPages();
            ResultData dataResult = new ResultData(ResultData.SUCCESS,pageNum,total,pages,userRoleResponseList,"查询成功");

            return dataResult;
        }catch (Exception e){
            e.printStackTrace();
            return resultData;
        }
    }

    @Transactional
    @Override
    public Result createUserRole(Integer userId, Integer roleId)throws Exception {
        Result result = new Result(Result.FAILED,null,"error");
        try{
            UserRoleKey userRoleKey = new UserRoleKey();
            userRoleKey.setUserId(userId);
            userRoleKey.setRoleId(roleId);
            return userRoleMapper.insertSelective(userRoleKey)>0?new Result(Result.FAILED,null,"新增权限成功"):new Result(Result.FAILED,null,"新增权限失败") ;
        }catch (Exception e){
            throw new RuntimeException("",e.getCause());
        }
    }

    @Transactional
    @Override
    public Result deleteUserRole(Integer userId, Integer roleId)throws Exception {
        Result result = new Result(Result.FAILED,null,"error");
        try{
            UserRoleKey userRoleKey = new UserRoleKey();
            userRoleKey.setUserId(userId);
            userRoleKey.setRoleId(roleId);
            return userRoleMapper.deleteByPrimaryKey(userRoleKey)>0?new Result(Result.FAILED,null,"删除权限成功"):new Result(Result.FAILED,null,"删除权限失败") ;
        }catch (Exception e){
            throw new RuntimeException("",e.getCause());
        }
    }

    @Transactional
    @Override
    public Result updateUserRole(Integer userId, Integer roleId)throws Exception {
        Result result = new Result(Result.FAILED,null,"error");
        try{
            UserRoleKey userRoleKey = new UserRoleKey();
            userRoleKey.setUserId(userId);
            userRoleKey.setRoleId(roleId);
            return userRoleMapper.updateUserRole(userRoleKey)>0?new Result(Result.FAILED,null,"修改权限成功"):new Result(Result.FAILED,null,"修改权限失败") ;
        }catch (Exception e){
            throw new RuntimeException("",e.getCause());
        }
    }
}
