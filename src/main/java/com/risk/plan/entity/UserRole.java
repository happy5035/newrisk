package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class UserRole implements Serializable {
    private String crid;

    private String roleid;

    private String userid;

    private static final long serialVersionUID = 1L;

    public String getCrid() {
        return crid;
    }

    public void setCrid(String crid) {
        this.crid = crid == null ? null : crid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}