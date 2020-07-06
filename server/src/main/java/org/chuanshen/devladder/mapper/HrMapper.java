package org.chuanshen.devladder.mapper;

import org.chuanshen.devladder.model.Hr;
import org.chuanshen.devladder.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HrMapper {
    Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(Long id);

    int hrReg(@Param("username") String username, @Param("password") String password);

    List<Hr> getHrsByKeywords(@Param("keywords") String keywords);

    int updateHr(Hr hr);

    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    Hr getHrById(Long hrId);

    int deleteHr(Long hrId);

    List<Hr> getAllHr(@Param("currentId") Long currentId, @Param("keywords") String keywords);
}
