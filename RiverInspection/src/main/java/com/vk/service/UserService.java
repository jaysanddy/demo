package com.vk.service;

import com.vk.response.Result;
import com.vk.response.ResultData;

/**
 * Created by wj on 18-5-5.
 */
public interface UserService {

    public Result updatePassword(Integer userId, String oldPassword, String newPassword);

    //创建用户
    public Result createUser(String realName,String password,String remark,Integer areaId,Integer roleId,String phone);

    //后台获取本区域下的用户
    public ResultData getUserByAreaId(Integer areaId,Integer roleId,String realName,Integer pageNum,Integer pageSize);

    //获取单个用户的资料
    public ResultData getUserByUserId(Integer userId);

    //修改用户资料
    public Result updateUser(Integer userId,String realName,String password,String remark,Integer areaId,Integer status,Integer roleId,String phone);
}
