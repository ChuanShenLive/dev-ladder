package org.chuanshen.devladder.service;

import org.chuanshen.devladder.mapper.RoleMapper;
import org.chuanshen.devladder.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/6/1 11:07
 * @Description:
 **/
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }
}
