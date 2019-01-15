package com.vk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vk.mapper.UserMapper;
import com.vk.mapper.UserRoleMapper;
import com.vk.model.User;
import com.vk.model.UserRoleKey;
import com.vk.response.Result;
import com.vk.response.ResultData;
import com.vk.service.UserRoleService;
import com.vk.service.UserService;
import com.vk.util.MD5;
import com.vk.util.TextUtil;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by wj on 18-5-5.
 */

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public Result updatePassword(Integer userId, String oldPassword, String newPassword) {
        Result result = new Result(Result.FAILED,null,"error");
        User user = userMapper.selectByPrimaryKey(userId);
        String oldPwd = user.getPassword();
        if(oldPwd.equals(MD5.getMD5(oldPassword))){
            String newMD5Pwd = MD5.getMD5(newPassword);
            user.setPassword(newMD5Pwd);
            userMapper.updateByPrimaryKeySelective(user);
            result.setMsg("密码修改成功！");
            result.setCode(Result.SUCCESS);
        }else {
            result.setMsg("密码修改失败,与原密码不一致！");
        }
        return result;
    }

    @Transactional
    @Override
    public Result createUser( String realName, String password, String remark, Integer areaId,Integer roleId,String phone) {
        Result result = new Result(Result.FAILED,null,"error");
        try{

            User user = new User();
            user.setName(phone);
            user.setRealname(realName);
            user.setPassword(MD5.getMD5(password));
            user.setRemark(remark);
            user.setAreaId(areaId);
            user.setCreateTime(new Date());
            user.setStatus(0);
            user.setPhone(phone);

            userMapper.insertSelective(user);

            userRoleService.createUserRole(user.getId(),roleId);

            result.setCode(Result.SUCCESS);
            result.setMsg("添加用户成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("添加用户失败");
        }
        return result;
    }

    @Override
    public ResultData getUserByAreaId(Integer areaId,Integer roleId,String realName,Integer pageNum,Integer pageSize) {
        ResultData resultData = new ResultData(ResultData.FAILED,null,"查询失败");
        try{
            User user = new User();
            if(TextUtil.integerNotEmpty(areaId)){
                user.setAreaId(areaId);
            }
            if(TextUtil.integerNotEmpty(roleId)){
                user.setRoleId(roleId);
            }
            if(TextUtil.isNotEmpty(realName)){
                user.setRealname(realName);
            }
            Page page = PageHelper.startPage(pageNum,pageSize,true);
            List<User> users = userMapper.getByteUsers(user);
            Long total = page.getTotal();
            Integer pages = page.getPages();
            ResultData dataResult = new ResultData(ResultData.SUCCESS,pageNum,total,pages,users,"查询成功");
            return dataResult;
        }catch (Exception e){
            e.printStackTrace();
            return resultData;
        }
    }

    @Override
    public ResultData getUserByUserId(Integer userId) {
        ResultData resultData = new ResultData(ResultData.FAILED,null,"查询失败");
        try{
            User user = new User();
            user.setId(userId);
            List<User> users = userMapper.getByteUsers(user);
            ResultData dataResult = new ResultData(ResultData.SUCCESS,null,null,null,users.get(0),"查询成功");
            return dataResult;
        }catch (Exception e){
            e.printStackTrace();
            return resultData;
        }
    }

    @Transactional
    @Override
    public Result updateUser(Integer userId, String realName, String password, String remark, Integer areaId, Integer status,Integer roleId,String phone) {
        Result result = new Result(Result.FAILED,null,"error");
        try{

            User user = new User();
            if(TextUtil.isNotEmpty(realName)){
                user.setRealname(realName);
            }
            if(TextUtil.isNotEmpty(password)){
                user.setPassword(MD5.getMD5(password));
            }
            if(TextUtil.isNotEmpty(remark)){
                user.setRemark(remark);
            }
            if(TextUtil.integerNotEmpty(areaId)){
                user.setAreaId(areaId);
            }
            if(TextUtil.integerNotEmpty(status)){
                user.setStatus(status);
            }
            if(TextUtil.isNotEmpty(phone)){
                user.setPhone(phone);
                user.setName(phone);
            }
            user.setId(userId);
            Integer ok =userMapper.updateByPrimaryKeySelective(user);
            UserRoleKey userRoleKey = new UserRoleKey();
            userRoleKey.setUserId(userId);
            userRoleKey.setRoleId(roleId);
            Integer roleOK =userRoleMapper.updateUserRole(userRoleKey);
            if(ok >0 && roleOK >0){
                return new Result(Result.SUCCESS,null,"修改成功");
            }else {
                return new Result(Result.FAILED,null,"修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
    }
}
