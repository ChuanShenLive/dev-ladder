package org.chuanshen.devladder.controller.system;

import org.chuanshen.devladder.model.Hr;
import org.chuanshen.devladder.model.RespBean;
import org.chuanshen.devladder.model.Role;
import org.chuanshen.devladder.service.RoleService;
import org.chuanshen.devladder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/7/2 8:53
 * @Description:
 **/
@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords) {
        return userService.getAllHrs(keywords);
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (userService.updateUser(hr) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateUserRole(Long hrid, Long[] rids) {
        if (userService.updateUserRole(hrid, rids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Long id) {
        if (userService.deleteHrById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
