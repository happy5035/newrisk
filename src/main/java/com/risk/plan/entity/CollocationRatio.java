package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class CollocationRatio implements Serializable {
    private String colloratioid;

    private String collorationame;

    private String kindnum;

    private static final long serialVersionUID = 1L;

    public String getColloratioid() {
        return colloratioid;
    }

    public void setColloratioid(String colloratioid) {
        this.colloratioid = colloratioid == null ? null : colloratioid.trim();
    }

    public String getCollorationame() {
        return collorationame;
    }

    public void setCollorationame(String collorationame) {
        this.collorationame = collorationame == null ? null : collorationame.trim();
    }

    public String getKindnum() {
        return kindnum;
    }

    public void setKindnum(String kindnum) {
        this.kindnum = kindnum == null ? null : kindnum.trim();
    }
}