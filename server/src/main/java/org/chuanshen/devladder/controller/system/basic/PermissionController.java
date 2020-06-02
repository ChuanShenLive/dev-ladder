package org.chuanshen.devladder.controller.system.basic;

import org.chuanshen.devladder.model.Menu;
import org.chuanshen.devladder.model.Role;
import org.chuanshen.devladder.service.MenuService;
import org.chuanshen.devladder.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/6/1 10:56
 * @Description:
 **/
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissionController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Long> getMidsByRid(@PathVariable Long rid) {
        return menuService.getMidsByRid(rid);
    }
}
