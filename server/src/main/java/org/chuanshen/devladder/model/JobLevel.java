package org.chuanshen.devladder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class JobLevel {
    private Long id;
    private String name;
    private String titleLevel;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Timestamp createDate;
    private Boolean enabled;
}
