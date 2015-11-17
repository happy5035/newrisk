package com.risk.plan.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class TransLive implements Serializable {
    private String transliveid;

    private String edgeability;

    private String state;

    private Date starttime;

    private Date endtime;

    private String edgeid;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getTransliveid() {
        return transliveid;
    }

    public void setTransliveid(String transliveid) {
        this.transliveid = transliveid == null ? null : transliveid.trim();
    }

    public String getEdgeability() {
        return edgeability;
    }

    public void setEdgeability(String edgeability) {
        this.edgeability = edgeability == null ? null : edgeability.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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

    public String getEdgeid() {
        return edgeid;
    }

    public void setEdgeid(String edgeid) {
        this.edgeid = edgeid == null ? null : edgeid.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}