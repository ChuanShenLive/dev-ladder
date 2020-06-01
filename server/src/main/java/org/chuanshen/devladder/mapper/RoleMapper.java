package org.chuanshen.devladder.mapper;

import org.chuanshen.devladder.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {

    Role selectByPrimaryKey(Long id);

    List<Role> getAllRoles();

    List<Role> roles();

    int addNewRole(@Param("role") String role, @Param("roleZh") String roleZh);

    int deleteByPrimaryKey(Long id);

    int deleteRoleById(Long rid);
}
