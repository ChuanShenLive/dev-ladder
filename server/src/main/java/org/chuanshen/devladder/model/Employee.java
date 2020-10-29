package org.chuanshen.devladder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private Long id;
    private String name;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date birthday;
    private String idCard;
    private String wedlock;
//    @JsonIgnore
//    private Long nationId;
//    @JsonIgnore
//    private String nationName;
    private Nation nation;
    private String nativePlace;
//    @JsonIgnore
//    private Long politicId;
//    @JsonIgnore
//    private String politicName;
    private PoliticsStatus politicsStatus;
    private String email;
    private String phone;
    private String address;
//    @JsonIgnore
//    private Long departmentId;
//    @JsonIgnore
//    private String departmentName;
    private Department department;
//    @JsonIgnore
//    private Long jobLevelId;
//    @JsonIgnore
//    private String jobLevelName;
    private JobLevel jobLevel;
//    @JsonIgnore
//    private Long posId;
//    private String posName;
    private Position position;
    private String engageForm;
    private String tiptopDegree;
    private String specialty;
    private String school;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date beginDate;
    private String workState;
    private String workID;
    private Double contractTerm;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date conversionTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date notWorkDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date beginContract;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date endContract;
    private Integer workAge;
    private Salary salary;
}