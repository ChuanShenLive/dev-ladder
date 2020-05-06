package org.chuanshen.devladder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {

    private Long id;
    @JsonIgnore
    private String url;
    private String path;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Object component;
    private String name;
    private String iconCls;
    @JsonIgnore
    private Long parentId;
    private Meta meta;
    private Boolean enabled;
    @JsonIgnore
    private List<Role> roles;
    private List<Menu> children;
}
