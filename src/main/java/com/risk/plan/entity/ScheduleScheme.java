package com.risk.plan.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class ScheduleScheme implements Serializable {
    private String schemeid;

    private String emerid;

    private String schemeno;

    private String schemename;

    private Date starttime;

    private Date endtime;

    private Date consumetime;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid == null ? null : schemeid.trim();
    }

    public String getEmerid() {
        return emerid;
    }

    public void setEmerid(String emerid) {
        this.emerid = emerid == null ? null : emerid.trim();
    }

    public String getSchemeno() {
        return schemeno;
    }

    public void setSchemeno(String schemeno) {
        this.schemeno = schemeno == null ? null : schemeno.trim();
    }

    public String getSchemename() {
        return schemename;
    }

    public void setSchemename(String schemename) {
        this.schemename = schemename == null ? null : schemename.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getConsumetime() {
        return consumetime;
    }

    public void setConsumetime(Date consumetime) {
        this.consumetime = consumetime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}