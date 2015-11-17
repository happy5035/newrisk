package com.risk.plan.entity;



public class ENRelationCustom extends ENRelation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Emergency emergency;
	private Nodes nodes;
	
	public Emergency getEmergency() {
		return emergency;
	}
	public void setEmergency(Emergency emergency) {
		this.emergency = emergency;
	}
	public Nodes getNodes() {
		return nodes;
	}
	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}
	
	
}
