package com.risk.plan.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Users implements Serializable {
    private String userid;

    private String usertype;

    private String logname;

    private String logpwd;

    private String pwdquestion;

    private String pwdanswer;

    private String username;

    private Date birday;

    private String certitype;

    private String certicobe;

    private String depart;

    private String telephone;

    private String cellphone;

    private String userqq;

    private String email;

    private Date registertime;

    private String userstate;

    private String workstate;

    private String note;

    private String firstornot;
    
    private String birdayS;
    
    private String registertimeS;
    
    public String getBirdayS() {
		return birdayS;
	}


	public String getRegistertimeS() {
		return registertimeS;
	}

	private static final long serialVersionUID = 1L;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname == null ? null : logname.trim();
    }

    public String getLogpwd() {
        return logpwd;
    }

    public void setLogpwd(String logpwd) {
        this.logpwd = logpwd == null ? null : logpwd.trim();
    }

    public String getPwdquestion() {
        return pwdquestion;
    }

    public void setPwdquestion(String pwdquestion) {
        this.pwdquestion = pwdquestion == null ? null : pwdquestion.trim();
    }

    public String getPwdanswer() {
        return pwdanswer;
    }

    public void setPwdanswer(String pwdanswer) {
        this.pwdanswer = pwdanswer == null ? null : pwdanswer.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getBirday() {
    	
        return birday;
    }

    public void setBirday(Date birday) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	String dateString=format.format(birday);
    	this.birdayS=dateString;
        this.birday = birday;
    }

    public String getCertitype() {
        return certitype;
    }

    public void setCertitype(String certitype) {
        this.certitype = certitype == null ? null : certitype.trim();
    }

    public String getCerticobe() {
        return certicobe;
    }

    public void setCerticobe(String certicobe) {
        this.certicobe = certicobe == null ? null : certicobe.trim();
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart == null ? null : depart.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getUserqq() {
        return userqq;
    }

    public void setUserqq(String userqq) {
        this.userqq = userqq == null ? null : userqq.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	String dateString=format.format(registertime);
    	this.registertimeS=dateString;
        this.registertime = registertime;
    }

    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate == null ? null : userstate.trim();
    }

    public String getWorkstate() {
        return workstate;
    }

    public void setWorkstate(String workstate) {
        this.workstate = workstate == null ? null : workstate.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getFirstornot() {
        return firstornot;
    }

    public void setFirstornot(String firstornot) {
        this.firstornot = firstornot == null ? null : firstornot.trim();
    }

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", usertype=" + usertype
				+ ", logname=" + logname + ", logpwd=" + logpwd
				+ ", pwdquestion=" + pwdquestion + ", pwdanswer=" + pwdanswer
				+ ", username=" + username + ", birday=" + birday
				+ ", certitype=" + certitype + ", certicobe=" + certicobe
				+ ", depart=" + depart + ", telephone=" + telephone
				+ ", cellphone=" + cellphone + ", userqq=" + userqq
				+ ", email=" + email + ", registertime=" + registertime
				+ ", userstate=" + userstate + ", workstate=" + workstate
				+ ", note=" + note + ", firstornot=" + firstornot + "]";
	}
    
}