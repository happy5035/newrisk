package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Goods implements Serializable {
    private String goodsid;

    private String goodsname;

    private String goodsno;

    private String mainuse;

    private String measureunit;

    private String guaranteeperiod;

    private String specification;

    private String size;

    private String goodstypeid;

    private String note;
    
    private GoodsType goodstype;
    
    

    public GoodsType getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(GoodsType goodstype) {
		this.goodstype = goodstype;
	}

	private static final long serialVersionUID = 1L;

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public String getGoodsno() {
        return goodsno;
    }

    public void setGoodsno(String goodsno) {
        this.goodsno = goodsno == null ? null : goodsno.trim();
    }

    public String getMainuse() {
        return mainuse;
    }

    public void setMainuse(String mainuse) {
        this.mainuse = mainuse == null ? null : mainuse.trim();
    }

    public String getMeasureunit() {
        return measureunit;
    }

    public void setMeasureunit(String measureunit) {
        this.measureunit = measureunit == null ? null : measureunit.trim();
    }

    public String getGuaranteeperiod() {
        return guaranteeperiod;
    }

    public void setGuaranteeperiod(String guaranteeperiod) {
        this.guaranteeperiod = guaranteeperiod == null ? null : guaranteeperiod.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(String goodstypeid) {
        this.goodstypeid = goodstypeid == null ? null : goodstypeid.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}