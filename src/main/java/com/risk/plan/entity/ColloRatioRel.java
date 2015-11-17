package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class ColloRatioRel implements Serializable {
    private String colloratiorelid;

    private String ownratio;

    private String loadnum;

    private String goodsid;

    private String colloratioid;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getColloratiorelid() {
        return colloratiorelid;
    }

    public void setColloratiorelid(String colloratiorelid) {
        this.colloratiorelid = colloratiorelid == null ? null : colloratiorelid.trim();
    }

    public String getOwnratio() {
        return ownratio;
    }

    public void setOwnratio(String ownratio) {
        this.ownratio = ownratio == null ? null : ownratio.trim();
    }

    public String getLoadnum() {
        return loadnum;
    }

    public void setLoadnum(String loadnum) {
        this.loadnum = loadnum == null ? null : loadnum.trim();
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getColloratioid() {
        return colloratioid;
    }

    public void setColloratioid(String colloratioid) {
        this.colloratioid = colloratioid == null ? null : colloratioid.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}