package com.risk.plan.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class SCollocationPlan implements Serializable {
    private String scollocationplanid;

    private String tranmodelid;

    private String colloratioid;

    private Date loadtime;

    private Date unloadtime;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getScollocationplanid() {
        return scollocationplanid;
    }

    public void setScollocationplanid(String scollocationplanid) {
        this.scollocationplanid = scollocationplanid == null ? null : scollocationplanid.trim();
    }

    public String getTranmodelid() {
        return tranmodelid;
    }

    public void setTranmodelid(String tranmodelid) {
        this.tranmodelid = tranmodelid == null ? null : tranmodelid.trim();
    }

    public String getColloratioid() {
        return colloratioid;
    }

    public void setColloratioid(String colloratioid) {
        this.colloratioid = colloratioid == null ? null : colloratioid.trim();
    }

    public Date getLoadtime() {
        return loadtime;
    }

    public void setLoadtime(Date loadtime) {
        this.loadtime = loadtime;
    }

    public Date getUnloadtime() {
        return unloadtime;
    }

    public void setUnloadtime(Date unloadtime) {
        this.unloadtime = unloadtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}