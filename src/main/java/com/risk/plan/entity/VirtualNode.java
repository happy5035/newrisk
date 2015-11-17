package com.risk.plan.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class VirtualNode implements Serializable {
    private String vnodeid;

    private String virtualno;

    private String virtualname;

    private String note;

    private Date time;

    private String transid;

    private String edgeid;

    private static final long serialVersionUID = 1L;

    public String getVnodeid() {
        return vnodeid;
    }

    public void setVnodeid(String vnodeid) {
        this.vnodeid = vnodeid == null ? null : vnodeid.trim();
    }

    public String getVirtualno() {
        return virtualno;
    }

    public void setVirtualno(String virtualno) {
        this.virtualno = virtualno == null ? null : virtualno.trim();
    }

    public String getVirtualname() {
        return virtualname;
    }

    public void setVirtualname(String virtualname) {
        this.virtualname = virtualname == null ? null : virtualname.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid == null ? null : transid.trim();
    }

    public String getEdgeid() {
        return edgeid;
    }

    public void setEdgeid(String edgeid) {
        this.edgeid = edgeid == null ? null : edgeid.trim();
    }
}