package org.chuanshen.devladder.service;

import org.chuanshen.devladder.mapper.EmpMapper;
import org.chuanshen.devladder.model.Employee;
import org.chuanshen.devladder.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/7/6 16:31
 * @Description:
 **/
@Service
public class EmployeeService {
    @Autowired
    EmpMapper empMapper;

    public RespPageBean getEmployeeByPage(Long page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }

        List<Employee> data = empMapper.getEmployeeByPage(page, size);
        Long total = empMapper.getTotal();
        return new RespPageBean(total, data);
    }
}
