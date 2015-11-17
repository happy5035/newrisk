package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Path implements Serializable {
    private String pathid;

    private String loadorno;

    private Integer pathserialnum;

    private String edgeid;

    private String taskid;

    private String secondnodeid;

    private String firstnodeid;

    private String length;

    private static final long serialVersionUID = 1L;

    private Edge edge;
    
    public Edge getEdge() {
		return edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	public String getPathid() {
        return pathid;
    }

    public void setPathid(String pathid) {
        this.pathid = pathid == null ? null : pathid.trim();
    }

    public String getLoadorno() {
        return loadorno;
    }

    public void setLoadorno(String loadorno) {
        this.loadorno = loadorno == null ? null : loadorno.trim();
    }

    public Integer getPathserialnum() {
        return pathserialnum;
    }

    public void setPathserialnum(Integer pathserialnum) {
        this.pathserialnum = pathserialnum;
    }

    public String getEdgeid() {
        return edgeid;
    }

    public void setEdgeid(String edgeid) {
        this.edgeid = edgeid == null ? null : edgeid.trim();
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
    }
}