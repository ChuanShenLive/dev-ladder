package org.chuanshen.devladder.controller.emp;

import org.chuanshen.devladder.model.RespPageBean;
import org.chuanshen.devladder.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/7/6 15:41
 * @Description:
 **/
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Long page, @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeByPage(page, size);
    }
}
