package org.chuanshen.devladder.service;

import org.chuanshen.devladder.mapper.MenuRoleMapper;
import org.chuanshen.devladder.mapper.RoleMapper;
import org.chuanshen.devladder.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.addRole(role);
    }

    @Transactional
    public Integer deleteRoleById(Long rid) {
        menuRoleMapper.deleteMenuByRid(rid);
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
