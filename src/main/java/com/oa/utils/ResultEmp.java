package com.oa.utils;

import lombok.Data;

import java.util.Date;

/**
 * @author CHTW
 * @date 2020/3/20 13:25
 */

public class ResultEmp {
    private Integer id;

    private String eAccount;

    private String eId;

    private String name;

    private Integer age;

    private String sex;

    private String ePassword;

    private String idCardNo;

    private String phoneNum;

    private String email;

    private String bankAccount;

    private String dept;

    private String position;

    private Double lowSalary;

    private String contractNum;

    private String inWithTime;

    private Integer gradeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String geteAccount() {
        return eAccount;
    }

    public void seteAccount(String eAccount) {
        this.eAccount = eAccount;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getePassword() {
        return ePassword;
    }

    public void setePassword(String ePassword) {
        this.ePassword = ePassword;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getLowSalary() {
        return lowSalary;
    }

    public void setLowSalary(Double lowSalary) {
        this.lowSalary = lowSalary;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getInWithTime() {
        return inWithTime;
    }

    public void setInWithTime(String inWithTime) {
        this.inWithTime = inWithTime;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "ResultEmp{" +
                "id=" + id +
                ", eAccount='" + eAccount + '\'' +
                ", eId='" + eId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", ePassword='" + ePassword + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", dept='" + dept + '\'' +
                ", position='" + position + '\'' +
                ", lowSalary=" + lowSalary +
                ", contractNum='" + contractNum + '\'' +
                ", inWithTime='" + inWithTime + '\'' +
                ", gradeId=" + gradeId +
                '}';
    }
}
