package com.risk.plan.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Task implements Serializable {
    private String taskid;

    private String geneid;

    private Integer taskserialnum;

    private String firstnodeid;

    private String secondnodeid;

    private Date starttime;

    private Date endtime;

    private String standardornot;

    private String realcollocationid;

    private String scollocationplanid;

    private Integer pathsum;

    private static final long serialVersionUID = 1L;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public String getGeneid() {
        return geneid;
    }

    public void setGeneid(String geneid) {
        this.geneid = geneid == null ? null : geneid.trim();
    }

    public Integer getTaskserialnum() {
        return taskserialnum;
    }

    public void setTaskserialnum(Integer taskserialnum) {
        this.taskserialnum = taskserialnum;
    }

    public String getFirstnodeid() {
        return firstnodeid;
    }

    public void setFirstnodeid(String firstnodeid) {
        this.firstnodeid = firstnodeid == null ? null : firstnodeid.trim();
    }

    public String getSecondnodeid() {
        return secondnodeid;
    }

    public void setSecondnodeid(String secondnodeid) {
        this.secondnodeid = secondnodeid == null ? null : secondnodeid.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getStandardornot() {
        return standardornot;
    }

    public void setStandardornot(String standardornot) {
        this.standardornot = standardornot == null ? null : standardornot.trim();
    }

    public String getRealcollocationid() {
        return realcollocationid;
    }

    public void setRealcollocationid(String realcollocationid) {
        this.realcollocationid = realcollocationid == null ? null : realcollocationid.trim();
    }

    public String getScollocationplanid() {
        return scollocationplanid;
    }

    public void setScollocationplanid(String scollocationplanid) {
        this.scollocationplanid = scollocationplanid == null ? null : scollocationplanid.trim();
    }

    public Integer getPathsum() {
        return pathsum;
    }

    public void setPathsum(Integer pathsum) {
        this.pathsum = pathsum;
    }
}