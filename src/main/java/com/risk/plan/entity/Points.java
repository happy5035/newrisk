package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Points implements Serializable {
    private String pointsid;

    private String edgeid;

    private String lat;

    private String lng;

    private Integer serialnum;

    private static final long serialVersionUID = 1L;
    
    private Edge edge;
    
    

    public Edge getEdge() {
		return edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid == null ? null : pointsid.trim();
    }

    public String getEdgeid() {
        return edgeid;
    }

    public void setEdgeid(String edgeid) {
        this.edgeid = edgeid == null ? null : edgeid.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public Integer getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(Integer serialnum) {
        this.serialnum = serialnum;
    }
}