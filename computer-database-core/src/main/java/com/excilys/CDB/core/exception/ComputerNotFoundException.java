package com.excilys.CDB.core.exception;

public class ComputerNotFoundException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3636546555529815670L;

	public ComputerNotFoundException(Long id) {
		super("computer id = " + id + " not found");
	}

}


