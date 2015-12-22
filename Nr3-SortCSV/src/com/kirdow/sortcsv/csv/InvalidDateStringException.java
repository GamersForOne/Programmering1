package com.kirdow.sortcsv.csv;

public class InvalidDateStringException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9202118863472901625L;
	private String str;
	
	public InvalidDateStringException(String msg) {
		super("Invalid Date String: " + msg);
		this.str = msg;
	}
	
}
