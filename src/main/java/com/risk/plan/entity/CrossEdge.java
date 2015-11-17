package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class CrossEdge implements Serializable {
    private String crossedgeid;

    private String cnodeid;

    private String edgeid;

    private String startdistance;

    private static final long serialVersionUID = 1L;

    public String getCrossedgeid() {
        return crossedgeid;
    }

    public void setCrossedgeid(String crossedgeid) {
        this.crossedgeid = crossedgeid == null ? null : crossedgeid.trim();
    }

    public String getCnodeid() {
        return cnodeid;
    }

    public void setCnodeid(String cnodeid) {
        this.cnodeid = cnodeid == null ? null : cnodeid.trim();
    }

    public String getEdgeid() {
        return edgeid;
    }

    public void setEdgeid(String edgeid) {
        this.edgeid = edgeid == null ? null : edgeid.trim();
    }

    public String getStartdistance() {
        return startdistance;
    }

    public void setStartdistance(String startdistance) {
        this.startdistance = startdistance == null ? null : startdistance.trim();
    }
}