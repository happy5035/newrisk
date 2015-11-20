package com.risk.plan.exception;

public class LoopExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String detail;
	
	public LoopExistException(String e) {
		// TODO Auto-generated constructor stub
		detail=e;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return detail;
	}
}
