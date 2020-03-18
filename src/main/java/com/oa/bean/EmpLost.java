package com.oa.bean;

import java.util.Date;

public class EmpLost {
    private Integer id;

    private String eAccount;

    private String eId;

    private String name;

    private String phoneNum;

    private Date lostTime;

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
        this.eAccount = eAccount == null ? null : eAccount.trim();
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId == null ? null : eId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public Date getLostTime() {
        return lostTime;
    }

    public void setLostTime(Date lostTime) {
        this.lostTime = lostTime;
    }
}