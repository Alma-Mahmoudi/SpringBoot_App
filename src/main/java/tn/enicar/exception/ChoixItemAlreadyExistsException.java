package tn.enicar.exception;

public class ChoixItemAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ChoixItemAlreadyExistsException(String message) {
	        super(message);
	    }
}
