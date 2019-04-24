package exception;

public class InvalidDateChronology extends Exception{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1632316265365891249L;

	public InvalidDateChronology() {
		super("Date de fin inferieure a la date de debut");
	}
}
