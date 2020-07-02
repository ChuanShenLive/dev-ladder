package org.chuanshen.devladder.controller.system.basic;

import org.chuanshen.devladder.model.Department;
import org.chuanshen.devladder.model.RespBean;
import org.chuanshen.devladder.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/6/4 17:06
 * @Description:
 **/
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep) {
        departmentService.addDep(dep);
        if (dep.getResult() == 1) {
            return RespBean.ok("添加成功", dep);
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Long id) {
        Department dep = new Department();
        dep.setId(id);
        departmentService.deleteDep(dep);
        if (dep.getResult() == -2) {
            return RespBean.error("该部门下有子部门, 删除失败!");
        } else if (dep.getResult() == -1) {
            return RespBean.error("该部门下有员工, 删除失败!");
        } else if (dep.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败!");
    }
}
