package com.wj.springboot.dao;

import com.wj.springboot.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 123
 * @since 2018-11-20
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    public List<SysUser> getUser();
}
