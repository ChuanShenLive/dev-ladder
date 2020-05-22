package org.chuanshen.devladder.controller.config;

import org.chuanshen.devladder.model.RespBean;
import org.chuanshen.devladder.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/4/28 14:56
 * @Description:
 **/
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    public RespBean getMenusByHrId() {
        return RespBean.ok(null, menuService.getMenusByHrId());
    }
}
