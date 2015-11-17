package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Area implements Serializable {
    private String areaid;

    private String parareaid;

    private String areaname;

    private String arealevel;

    private String postalcode;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getParareaid() {
        return parareaid;
    }

    public void setParareaid(String parareaid) {
        this.parareaid = parareaid == null ? null : parareaid.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getArealevel() {
        return arealevel;
    }

    public void setArealevel(String arealevel) {
        this.arealevel = arealevel == null ? null : arealevel.trim();
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode == null ? null : postalcode.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

	@Override
	public String toString() {
		return "Area [areaid=" + areaid + ", parareaid=" + parareaid
				+ ", areaname=" + areaname + ", arealevel=" + arealevel
				+ ", postalcode=" + postalcode + ", note=" + note + "]";
	}
}