package com.oa.bean;

public class LeaveSales {
    private Integer id;

    private String eId;

    private String name;

    private String leaveTime;

    private String start;

    private String end;

    private String leaveReason;

    private String failMessage;

    private Integer audtiState;

    private Integer salesState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime == null ? null : leaveTime.trim();
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start == null ? null : start.trim();
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end == null ? null : end.trim();
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason == null ? null : leaveReason.trim();
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage == null ? null : failMessage.trim();
    }

    public Integer getAudtiState() {
        return audtiState;
    }

    public void setAudtiState(Integer audtiState) {
        this.audtiState = audtiState;
    }

    public Integer getSalesState() {
        return salesState;
    }

    public void setSalesState(Integer salesState) {
        this.salesState = salesState;
    }
}