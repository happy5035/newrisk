package com.risk.plan.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class GoodsRelation implements Serializable {
    private String goodsreid;

    private Date time;

    private String saveornot;

    private String demandnum;

    private String priority;

    private String goodsid;

    private String note;

    private String nodeid;
    
    private Goods goods;
    
    private Nodes nodes;
    
    
    public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Nodes getNodes() {
		return nodes;
	}

	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}

	private static final long serialVersionUID = 1L;

    public String getGoodsreid() {
        return goodsreid;
    }

    public void setGoodsreid(String goodsreid) {
        this.goodsreid = goodsreid == null ? null : goodsreid.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSaveornot() {
        return saveornot;
    }

    public void setSaveornot(String saveornot) {
        this.saveornot = saveornot == null ? null : saveornot.trim();
    }

    public String getDemandnum() {
        return demandnum;
    }

    public void setDemandnum(String demandnum) {
        this.demandnum = demandnum == null ? null : demandnum.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
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

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid == null ? null : nodeid.trim();
    }
}