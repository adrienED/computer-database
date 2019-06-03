package com.excilys.CDB.core.exception;

public class NotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5761892313790076495L;

	public NotFoundException(Long id) {
		super("Invalid id : " + id);
	}

}