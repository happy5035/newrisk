package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class DisasterNode implements Serializable {
    private String dnodeid;

    private String disasterno;

    private String disastername;

    private String disasterlevel;

    private String principal;

    private String phone;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getDnodeid() {
        return dnodeid;
    }

    public void setDnodeid(String dnodeid) {
        this.dnodeid = dnodeid == null ? null : dnodeid.trim();
    }

    public String getDisasterno() {
        return disasterno;
    }

    public void setDisasterno(String disasterno) {
        this.disasterno = disasterno == null ? null : disasterno.trim();
    }

    public String getDisastername() {
        return disastername;
    }

    public void setDisastername(String disastername) {
        this.disastername = disastername == null ? null : disastername.trim();
    }

    public String getDisasterlevel() {
        return disasterlevel;
    }

    public void setDisasterlevel(String disasterlevel) {
        this.disasterlevel = disasterlevel == null ? null : disasterlevel.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}