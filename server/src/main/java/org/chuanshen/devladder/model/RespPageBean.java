package org.chuanshen.devladder.model;

import lombok.Data;

import java.util.List;

/**
 * @Auther: Chuan Shen
 * @Date: 2020/7/6 15:50
 * @Description:
 **/
@Data
public class RespPageBean {
    private Long total;
    private List<?> data;

    public RespPageBean() {
    }

    public RespPageBean(Long total, List<?> data) {
        this.total = total;
        this.data = data;
    }
}
