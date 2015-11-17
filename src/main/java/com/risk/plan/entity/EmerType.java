package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class EmerType implements Serializable {
    private String emertypeid;

    private String emertypename;

    private String level;

    private String describe;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getEmertypeid() {
        return emertypeid;
    }

    public void setEmertypeid(String emertypeid) {
        this.emertypeid = emertypeid == null ? null : emertypeid.trim();
    }

    public String getEmertypename() {
        return emertypename;
    }

    public void setEmertypename(String emertypename) {
        this.emertypename = emertypename == null ? null : emertypename.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}