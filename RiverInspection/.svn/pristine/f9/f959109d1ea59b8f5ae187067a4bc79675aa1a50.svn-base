package com.vk.security;

import com.vk.mapper.RoleMapper;
import com.vk.mapper.UserMapper;
import com.vk.model.Role;
import com.vk.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfoNotFoundException;
import javax.management.relation.RoleNotFoundException;
import java.util.List;

/**
 * Created by wj on 18-4-17.
 */
@Service
public class CustomUserService implements UserDetailsService {

    protected static Logger logger= LoggerFactory.getLogger(CustomUserService.class);

    @Autowired
    private UserMapper userMaper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userMaper.findByUsername(username);
        if (user == null) {
            logger.error("user not exist,username:"+username);
            throw new UsernameNotFoundException("用户名不存在");
        }
        UserDetail userDetail = new UserDetail(user);
        userDetail.setRoles(roleMapper.selectRolesByUserId(user.getId()));
        logger.info("user login,username:"+username);
        return userDetail;
    }
}