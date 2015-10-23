package com.kirdow.luhnalgoritm;

public class NotNumberInputException extends Exception {
	
	public NotNumberInputException(String msg) {
		super(msg);
	}
	
	public NotNumberInputException() {
		super("Didn't enter a number");
	}
	
}
