package com.vk.service.impl;

import com.vk.mapper.RoleMapper;
import com.vk.model.Role;
import com.vk.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wj on 18-5-5.
 */

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRolesByUserId(Integer userId) {
        return roleMapper.selectRolesByUserId(userId);
    }
}
