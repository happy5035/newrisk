package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class RealCollocation implements Serializable {
    private String realcollocationid;

    private String realcollocationname;

    private String realkindnum;

    private static final long serialVersionUID = 1L;

    public String getRealcollocationid() {
        return realcollocationid;
    }

    public void setRealcollocationid(String realcollocationid) {
        this.realcollocationid = realcollocationid == null ? null : realcollocationid.trim();
    }

    public String getRealcollocationname() {
        return realcollocationname;
    }

    public void setRealcollocationname(String realcollocationname) {
        this.realcollocationname = realcollocationname == null ? null : realcollocationname.trim();
    }

    public String getRealkindnum() {
        return realkindnum;
    }

    public void setRealkindnum(String realkindnum) {
        this.realkindnum = realkindnum == null ? null : realkindnum.trim();
    }
}