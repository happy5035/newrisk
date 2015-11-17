package com.risk.plan.common;

import org.springframework.stereotype.Component;

@Component
public class EdgePoints {
	private String axisx;
	
	private String axisy;
	
	private String edgeType;
	
	private String fnodename;
	
	private String snodename;

	public String getAxisx() {
		return axisx;
	}

	public void setAxisx(String axisx) {
		this.axisx = axisx;
	}

	public String getAxisy() {
		return axisy;
	}

	public void setAxisy(String axisy) {
		this.axisy = axisy;
	}

	public String getEdgeType() {
		return edgeType;
	}

	public void setEdgeType(String edgeType) {
		this.edgeType = edgeType;
	}

	public String getFnodename() {
		return fnodename;
	}

	public void setFnodename(String fnodename) {
		this.fnodename = fnodename;
	}

	public String getSnodename() {
		return snodename;
	}

	public void setSnodename(String snodename) {
		this.snodename = snodename;
	}
	
	
}
