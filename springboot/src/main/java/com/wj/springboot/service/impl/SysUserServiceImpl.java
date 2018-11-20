package com.wj.springboot.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wj.springboot.entity.SysUser;
import com.wj.springboot.dao.SysUserMapper;
import com.wj.springboot.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 123
 * @since 2018-11-20
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public List<SysUser> getUser(Integer pageIndex,Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(pageIndex,pageSize);
        List<SysUser> users = baseMapper.getUser();
        Pagination pagination = PageHelper.getPagination();
        int pages =pagination.getPages();
        int total =pagination.getTotal();
        int size = pagination.getSize();
        int current = pagination.getCurrent();
        return  users;

    }
}
