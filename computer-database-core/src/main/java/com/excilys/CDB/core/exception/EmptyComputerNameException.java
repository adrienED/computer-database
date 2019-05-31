package exception;

public class EmptyComputerNameException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -247197129815670L;

	public EmptyComputerNameException() {
		super("Empty computer name, computer name is required");
	}

}
