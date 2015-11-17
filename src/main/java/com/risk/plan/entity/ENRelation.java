package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class ENRelation implements Serializable {
    private String enrelationid;

    private String nodeid;

    private String emerid;

    private static final long serialVersionUID = 1L;

    private Emergency emergency;
	private Nodes nodes;
	private EmerType emertype;
    public EmerType getEmertype() {
		return emertype;
	}

	public void setEmertype(EmerType emertype) {
		this.emertype = emertype;
	}

	public Emergency getEmergency() {
		return emergency;
	}

	public void setEmergency(Emergency emergency) {
		this.emergency = emergency;
	}

	public Nodes getNodes() {
		return nodes;
	}

	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}

	public String getEnrelationid() {
        return enrelationid;
    }

    public void setEnrelationid(String enrelationid) {
        this.enrelationid = enrelationid == null ? null : enrelationid.trim();
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid == null ? null : nodeid.trim();
    }

    public String getEmerid() {
        return emerid;
    }

    public void setEmerid(String emerid) {
        this.emerid = emerid == null ? null : emerid.trim();
    }
}