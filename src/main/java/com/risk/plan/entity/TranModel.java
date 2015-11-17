package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class TranModel implements Serializable {
    private String tranmodelid;

    private String note;

    private String transtype;

    private String loadweight;

    private static final long serialVersionUID = 1L;

    public String getTranmodelid() {
        return tranmodelid;
    }

    public void setTranmodelid(String tranmodelid) {
        this.tranmodelid = tranmodelid == null ? null : tranmodelid.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype == null ? null : transtype.trim();
    }

    public String getLoadweight() {
        return loadweight;
    }

    public void setLoadweight(String loadweight) {
        this.loadweight = loadweight == null ? null : loadweight.trim();
    }
}