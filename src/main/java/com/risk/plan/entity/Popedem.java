package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Popedem implements Serializable {
    private String popedemid;

    private String popedomname;

    private String parentid;

    private String url;

    private String photo;

    private Integer sequence;

    private Integer state;

    private Integer grade;

    private static final long serialVersionUID = 1L;

    public String getPopedemid() {
        return popedemid;
    }

    public void setPopedemid(String popedemid) {
        this.popedemid = popedemid == null ? null : popedemid.trim();
    }

    public String getPopedomname() {
        return popedomname;
    }

    public void setPopedomname(String popedomname) {
        this.popedomname = popedomname == null ? null : popedomname.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}