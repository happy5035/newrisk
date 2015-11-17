package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class RealCollo implements Serializable {
    private String realcolloid;

    private String realload;

    private String note;

    private String goodsid;

    private String realcollocationid;

    private static final long serialVersionUID = 1L;

    public String getRealcolloid() {
        return realcolloid;
    }

    public void setRealcolloid(String realcolloid) {
        this.realcolloid = realcolloid == null ? null : realcolloid.trim();
    }

    public String getRealload() {
        return realload;
    }

    public void setRealload(String realload) {
        this.realload = realload == null ? null : realload.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getRealcollocationid() {
        return realcollocationid;
    }

    public void setRealcollocationid(String realcollocationid) {
        this.realcollocationid = realcollocationid == null ? null : realcollocationid.trim();
    }
}