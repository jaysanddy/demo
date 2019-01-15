package com.vk.mapper;

import com.vk.model.UserRoleKey;
import com.vk.request.UserRoleRequest;
import com.vk.response.UserRoleResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    List<UserRoleResponse> getUserRole(UserRoleRequest userRoleRequest);

    Integer updateUserRole(UserRoleKey record);
}