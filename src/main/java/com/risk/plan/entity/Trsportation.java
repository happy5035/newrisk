package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import ch.qos.logback.core.pattern.parser.Node;
@Component
public class Trsportation implements Serializable {
    private String transid;

    private String tranmodelid;

    private String nodeid;

    private String busnumber;

    private String transno;

    private String state;

    private String note;

    private TranModel tranmodel;
    private Nodes nodes;
    
    private static final long serialVersionUID = 1L;

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid == null ? null : transid.trim();
    }

    public String getTranmodelid() {
        return tranmodelid;
    }

    public void setTranmodelid(String tranmodelid) {
        this.tranmodelid = tranmodelid == null ? null : tranmodelid.trim();
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid == null ? null : nodeid.trim();
    }

    public String getBusnumber() {
        return busnumber;
    }

    public void setBusnumber(String busnumber) {
        this.busnumber = busnumber == null ? null : busnumber.trim();
    }

    public String getTransno() {
        return transno;
    }

    public void setTransno(String transno) {
        this.transno = transno == null ? null : transno.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public TranModel getTranmodel() {
		return tranmodel;
	}

	public void setTranmodel(TranModel tranmodel) {
		this.tranmodel = tranmodel;
	}
    
	  public Nodes getNodes() {
			return nodes;
		}

		public void setNodes(Nodes nodes) {
			this.nodes = nodes;
		}
	
}