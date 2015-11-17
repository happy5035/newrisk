package com.risk.plan.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Events implements Serializable {
    private String eventid;

    private String note;

    private String eventtype;

    private Date happentime;

    private static final long serialVersionUID = 1L;

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid == null ? null : eventid.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype == null ? null : eventtype.trim();
    }

    public Date getHappentime() {
        return happentime;
    }

    public void setHappentime(Date happentime) {
        this.happentime = happentime;
    }
}