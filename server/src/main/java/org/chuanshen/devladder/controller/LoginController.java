package org.chuanshen.devladder.controller;

import org.chuanshen.devladder.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/4/27 10:24
 * @Description:
 **/
@RestController
public class LoginController {
    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("尚未登录, 请先登录!");
    }
}
