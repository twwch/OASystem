package com.oa.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author CHTW
 * @date 2020/3/19 18:41
 */
public class ResultDept {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("pId")
    private Integer pId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("open")
    private boolean open;

    public ResultDept(Integer id, Integer pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
