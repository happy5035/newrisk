package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class UERelation implements Serializable {
    private String uerelationid;

    private String userid;

    private String emerid;

    private String state;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getUerelationid() {
        return uerelationid;
    }

    public void setUerelationid(String uerelationid) {
        this.uerelationid = uerelationid == null ? null : uerelationid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getEmerid() {
        return emerid;
    }

    public void setEmerid(String emerid) {
        this.emerid = emerid == null ? null : emerid.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}