package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Edge implements Serializable {
    private String edgeid;

    private String secondnodeid;

    private String firstnodeid;

    private String edgeno;

    private String edgename;

    private String edgetype;

    private String edgetypet;

    private String length;

    private String note;

    private static final long serialVersionUID = 1L;
    
    private Edge edge;
    
    

    public Edge getEdge() {
		return edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	public String getEdgeid() {
        return edgeid;
    }

    public void setEdgeid(String edgeid) {
        this.edgeid = edgeid == null ? null : edgeid.trim();
    }

    public String getSecondnodeid() {
        return secondnodeid;
    }

    public void setSecondnodeid(String secondnodeid) {
        this.secondnodeid = secondnodeid == null ? null : secondnodeid.trim();
    }

    public String getFirstnodeid() {
        return firstnodeid;
    }

    public void setFirstnodeid(String firstnodeid) {
        this.firstnodeid = firstnodeid == null ? null : firstnodeid.trim();
    }

    public String getEdgeno() {
        return edgeno;
    }

    public void setEdgeno(String edgeno) {
        this.edgeno = edgeno == null ? null : edgeno.trim();
    }

    public String getEdgename() {
        return edgename;
    }

    public void setEdgename(String edgename) {
        this.edgename = edgename == null ? null : edgename.trim();
    }

    public String getEdgetype() {
        return edgetype;
    }

    public void setEdgetype(String edgetype) {
        this.edgetype = edgetype == null ? null : edgetype.trim();
    }

    public String getEdgetypet() {
        return edgetypet;
    }

    public void setEdgetypet(String edgetypet) {
        this.edgetypet = edgetypet == null ? null : edgetypet.trim();
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}