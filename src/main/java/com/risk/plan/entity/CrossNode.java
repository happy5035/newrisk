package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class CrossNode implements Serializable {
    private String cnodeid;

    private String crossno;

    private String crossname;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getCnodeid() {
        return cnodeid;
    }

    public void setCnodeid(String cnodeid) {
        this.cnodeid = cnodeid == null ? null : cnodeid.trim();
    }

    public String getCrossno() {
        return crossno;
    }

    public void setCrossno(String crossno) {
        this.crossno = crossno == null ? null : crossno.trim();
    }

    public String getCrossname() {
        return crossname;
    }

    public void setCrossname(String crossname) {
        this.crossname = crossname == null ? null : crossname.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}