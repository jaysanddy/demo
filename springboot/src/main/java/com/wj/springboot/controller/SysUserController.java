package com.wj.springboot.controller;


import com.wj.springboot.entity.SysUser;
import com.wj.springboot.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 123
 * @since 2018-11-20
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/getUser")
    public List<SysUser> getUser(Integer pageIndex,Integer pageSize){
        List<SysUser> users = sysUserService.getUser(pageIndex,pageSize);
        return users;
    }
}

