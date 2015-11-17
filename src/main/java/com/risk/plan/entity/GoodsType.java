package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class GoodsType implements Serializable {
    private String goodstypeid;

    private String goodstypeno;

    private String goodstypename;

    private String grade;

    private String goodstypedescribe;

    private String note;

    private static final long serialVersionUID = 1L;

    public String getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(String goodstypeid) {
        this.goodstypeid = goodstypeid == null ? null : goodstypeid.trim();
    }

    public String getGoodstypeno() {
        return goodstypeno;
    }

    public void setGoodstypeno(String goodstypeno) {
        this.goodstypeno = goodstypeno == null ? null : goodstypeno.trim();
    }

    public String getGoodstypename() {
        return goodstypename;
    }

    public void setGoodstypename(String goodstypename) {
        this.goodstypename = goodstypename == null ? null : goodstypename.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getGoodstypedescribe() {
        return goodstypedescribe;
    }

    public void setGoodstypedescribe(String goodstypedescribe) {
        this.goodstypedescribe = goodstypedescribe == null ? null : goodstypedescribe.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}