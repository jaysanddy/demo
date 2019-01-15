package com.vk.controller;

import com.vk.response.Result;
import com.vk.response.ResultData;
import com.vk.service.UserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by weijin on 2018/6/19.
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController {
    @Resource
    private UserRoleService userRoleService;

    //获得用户的权限
    @RequestMapping("getUserRole")
    public ResultData getUserRole(@RequestParam Integer userId, @RequestParam(name = "page",defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "rows",defaultValue = "10") Integer pageSize){
        return userRoleService.getUserRole(userId, pageNum, pageSize);
    }

    //创建用户的权限
    @RequestMapping("createUserRole")
    public Result createUserRole(@RequestParam Integer userId, @RequestParam Integer roleId)throws Exception{
        return userRoleService.createUserRole(userId, roleId);
    }

    //删除用户的权限
    @RequestMapping("deleteUserRole")
    public Result deleteUserRole(@RequestParam Integer userId,@RequestParam Integer roleId)throws Exception{
        return userRoleService.deleteUserRole(userId, roleId);
    }
}
