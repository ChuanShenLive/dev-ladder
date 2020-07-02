package org.chuanshen.devladder.service;

import org.chuanshen.devladder.mapper.DepartmentMapper;
import org.chuanshen.devladder.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/6/4 17:09
 * @Description:
 **/
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.getDepByPid(-1L);
    }

    public void addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
    }

    public void deleteDep(Department dep) {
        departmentMapper.deleteDep(dep);
    }
}
