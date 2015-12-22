package com.kirdow.sortcsv.csv;

public class InvalidDateException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5646025248966436317L;
	private String dateType;
	private int value;
	
	public InvalidDateException(String dateType, int value) {
		super("Date type '" + dateType + "' with value '" + value + "' has the wrong format");
		this.dateType = dateType;
		this.value = value;
	}
	
	public final String getDateType() {
		return this.dateType;
	}
	
	public final int getValue() {
		return this.value;
	}
	
}
