package org.chuanshen.devladder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private Long id;
    private String name;
    private String gender;
    private Date birthday;
    private String idCard;
    private String wedlock;
    @JsonIgnore
    private Long nationId;
    @JsonIgnore
    private String nationName;
    private String nativePlace;
    @JsonIgnore
    private String politicName;
    @JsonIgnore
    private Long politicId;
    private String email;
    private String phone;
    private String address;
    @JsonIgnore
    private Long departmentId;
    @JsonIgnore
    private String departmentName;
    @JsonIgnore
    private Long jobLevelId;
    @JsonIgnore
    private String jobLevelName;
    @JsonIgnore
    private Long posId;
    private String posName;
    private String engageForm;
    private String tiptopDegree;
    private String specialty;
    private String school;
    private Date beginDate;
    private String workState;
    private String workID;
    private Double contractTerm;
    private Date conversionTime;
    private Date notWorkDate;
    private Date beginContract;
    private Date endContract;
    private Integer workAge;
    private Department department;
    private Nation nation;
    private JobLevel jobLevel;
    private Position position;
    private PoliticsStatus politicsStatus;
    private Salary salary;
}