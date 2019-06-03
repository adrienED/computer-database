package com.excilys.CDB.core.exception;

public class EmptyCompanyNameException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3636597197129815670L;

	public EmptyCompanyNameException() {
		super("Empty company name, company name is required");
	}

}
