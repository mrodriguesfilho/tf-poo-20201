package banco;

public class InvalidCredentialsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("Provided credentials are not valid");
	}

}
