package org.chuanshen.devladder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Department {
    private Long id;
    private String name;
    private Long parentId;
    @JsonIgnore
    private String depPath;
    private boolean enabled;
    private boolean isParent;

    //存储过程执行结果
    @JsonIgnore
    private Integer result;
    private List<Department> children = new ArrayList<>();
}
