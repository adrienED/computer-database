package exception;

public class NotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5761892313790076495L;

	public NotFoundException(Long id) {
		super("Invalid id : " + id);
	}

	public NotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}