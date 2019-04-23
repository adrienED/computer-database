package exception;

public class ComputerNotFoundException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3636597197129815670L;

	public ComputerNotFoundException(Long id) {
		super("computer id = " + id + " not found");
		// TODO Auto-generated constructor stub
	}

}


