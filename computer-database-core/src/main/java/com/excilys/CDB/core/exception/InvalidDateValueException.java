package com.excilys.CDB.core.exception;

public class InvalidDateValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8848265331988199431L;
	
	public InvalidDateValueException(String dateValue) {
		super("Invalid date value: " + dateValue);
	}

}
