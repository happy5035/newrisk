package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Gene implements Serializable {
    private String geneid;

    private String transid;

    private String schemeid;

    private Integer tasksum;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getGeneid() {
        return geneid;
    }

    public void setGeneid(String geneid) {
        this.geneid = geneid == null ? null : geneid.trim();
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid == null ? null : transid.trim();
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid == null ? null : schemeid.trim();
    }

    public Integer getTasksum() {
        return tasksum;
    }

    public void setTasksum(Integer tasksum) {
        this.tasksum = tasksum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}