package exception;

public class InvalidDateChronology extends Exception{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1632316265365891249L;

	public InvalidDateChronology() {
		super("Date de fin inf�rieure � la date de d�but");
	}
}
