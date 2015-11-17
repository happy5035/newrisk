package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class WareHouse implements Serializable {
    private String wnodeid;

    private String warehouseno;

    private String warehousename;

    private String warehouselevel;

    private String property;

    private String dimensions;

    private String capacity;

    private String legalman;

    private String belong;

    private String administrator;

    private String phone;

    private String note;

    private String legalmanphone;

    private String linkman;

    private String linkmanphone;

    private static final long serialVersionUID = 1L;

    public String getWnodeid() {
        return wnodeid;
    }

    public void setWnodeid(String wnodeid) {
        this.wnodeid = wnodeid == null ? null : wnodeid.trim();
    }

    public String getWarehouseno() {
        return warehouseno;
    }

    public void setWarehouseno(String warehouseno) {
        this.warehouseno = warehouseno == null ? null : warehouseno.trim();
    }

    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename == null ? null : warehousename.trim();
    }

    public String getWarehouselevel() {
        return warehouselevel;
    }

    public void setWarehouselevel(String warehouselevel) {
        this.warehouselevel = warehouselevel == null ? null : warehouselevel.trim();
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions == null ? null : dimensions.trim();
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity == null ? null : capacity.trim();
    }

    public String getLegalman() {
        return legalman;
    }

    public void setLegalman(String legalman) {
        this.legalman = legalman == null ? null : legalman.trim();
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator == null ? null : administrator.trim();
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

    public String getLegalmanphone() {
        return legalmanphone;
    }

    public void setLegalmanphone(String legalmanphone) {
        this.legalmanphone = legalmanphone == null ? null : legalmanphone.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getLinkmanphone() {
        return linkmanphone;
    }

    public void setLinkmanphone(String linkmanphone) {
        this.linkmanphone = linkmanphone == null ? null : linkmanphone.trim();
    }
}