package com.oa.utils;

/**
 * @author CHTW
 * @date 2020/3/20 23:06
 */
public class ResultEmpLost {
    private Integer id;

    private String eAccount;

    private String eId;

    private String name;

    private String phoneNum;

    private String lostOutTime;


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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLostOutTime() {
        return lostOutTime;
    }

    public void setLostOutTime(String lostOutTime) {
        this.lostOutTime = lostOutTime;
    }
}
