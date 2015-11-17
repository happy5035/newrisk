package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class RolePopedem implements Serializable {
    private String rpid;

    private String roleid;

    private String popedemid;

    private static final long serialVersionUID = 1L;

    public String getRpid() {
        return rpid;
    }

    public void setRpid(String rpid) {
        this.rpid = rpid == null ? null : rpid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getPopedemid() {
        return popedemid;
    }

    public void setPopedemid(String popedemid) {
        this.popedemid = popedemid == null ? null : popedemid.trim();
    }
}