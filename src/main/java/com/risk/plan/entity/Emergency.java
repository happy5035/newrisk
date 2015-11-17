package com.risk.plan.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Emergency implements Serializable {
    private String emerid;

    private String emername;

    private String emerno;

    private Date happentime;

    private String emerdescribe;

    private String subnum;

    private String note;
    
      
    private String happentimeS;

    private static final long serialVersionUID = 1L;
    
    
    public String getHappentimeS() {
		return happentimeS;
	}

	

	public String getEmerid() {
        return emerid;
    }

    public void setEmerid(String emerid) {
        this.emerid = emerid == null ? null : emerid.trim();
    }

    public String getEmername() {
        return emername;
    }

    public void setEmername(String emername) {
        this.emername = emername == null ? null : emername.trim();
    }

    public String getEmerno() {
        return emerno;
    }

    public void setEmerno(String emerno) {
        this.emerno = emerno == null ? null : emerno.trim();
    }

    
    
    
    public String getSubnum() {
        return subnum;
    }

    public void setSubnum(String subnum) {
        this.subnum = subnum == null ? null :subnum.trim();
    }

    
    
    
    public Date getHappentime() {
        return happentime;
    }

    public void setHappentime(Date happentime) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	String dateString=format.format(happentime);
        this.happentimeS=dateString;
        this.happentime = happentime;
    }

    public String getEmerdescribe() {
        return emerdescribe;
    }

    public void setEmerdescribe(String emerdescribe) {
        this.emerdescribe = emerdescribe == null ? null : emerdescribe.trim();
    }

   

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

   
}