package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Sub implements Serializable {
    private String subid;

    private String subname;

    private String subno;

    private String emerid;

    private String people;

    private String money;

    private String goods;

   

    private static final long serialVersionUID = 1L;

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid == null ? null : subid.trim();
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname == null ? null : subname.trim();
    }

    public String getSubno() {
        return subno;
    }

    public void setSubno(String subno) {
        this.subno = subno == null ? null : subno.trim();
    }

    public String getEmerId() {
        return emerid;
    }

    public void setEmerId(String emername) {
        this.emerid = emerid == null ? null : emerid.trim();
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods == null ? null : goods.trim();
    }

}