package com.risk.plan.entity;

import org.springframework.stereotype.Component;

@Component
public class EmergencyCustom extends Emergency {
	private EmerType emertype;
	
	
	public EmerType getEmertype() {
		return emertype;
	}


	public void setEmertype(EmerType emertype) {
		this.emertype = emertype;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
