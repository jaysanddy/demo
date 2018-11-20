package com.wj.springboot.service;

import com.wj.springboot.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 123
 * @since 2018-11-20
 */
public interface SysUserService extends IService<SysUser> {
    public List<SysUser> getUser(Integer pageIndex,Integer pageSize);
}
