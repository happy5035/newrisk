package com.risk.plan.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class ProducRelation implements Serializable {
    private String producrelationid;

    private Date time;

    private String producnum;

    private String price;

    private String manufacturerid;

    private String goodsid;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getProducrelationid() {
        return producrelationid;
    }

    public void setProducrelationid(String producrelationid) {
        this.producrelationid = producrelationid == null ? null : producrelationid.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getProducnum() {
        return producnum;
    }

    public void setProducnum(String producnum) {
        this.producnum = producnum == null ? null : producnum.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getManufacturerid() {
        return manufacturerid;
    }

    public void setManufacturerid(String manufacturerid) {
        this.manufacturerid = manufacturerid == null ? null : manufacturerid.trim();
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}