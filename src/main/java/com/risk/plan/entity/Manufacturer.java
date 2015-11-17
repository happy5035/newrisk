package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Manufacturer implements Serializable {
    private String manufacturerid;

    private String manuname;

    private String manufactureradd;

    private String tel;

    private String email;

    private String legalname;

    private String certitype;

    private String certicode;

    private String contactname;

    private String contacttel;

    private String website;

    private String note;

    private String areaid;

    private static final long serialVersionUID = 1L;

    public String getManufacturerid() {
        return manufacturerid;
    }

    public void setManufacturerid(String manufacturerid) {
        this.manufacturerid = manufacturerid == null ? null : manufacturerid.trim();
    }

    public String getManuname() {
        return manuname;
    }

    public void setManuname(String manuname) {
        this.manuname = manuname == null ? null : manuname.trim();
    }

    public String getManufactureradd() {
        return manufactureradd;
    }

    public void setManufactureradd(String manufactureradd) {
        this.manufactureradd = manufactureradd == null ? null : manufactureradd.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname == null ? null : legalname.trim();
    }

    public String getCertitype() {
        return certitype;
    }

    public void setCertitype(String certitype) {
        this.certitype = certitype == null ? null : certitype.trim();
    }

    public String getCerticode() {
        return certicode;
    }

    public void setCerticode(String certicode) {
        this.certicode = certicode == null ? null : certicode.trim();
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname == null ? null : contactname.trim();
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel == null ? null : contacttel.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }
}