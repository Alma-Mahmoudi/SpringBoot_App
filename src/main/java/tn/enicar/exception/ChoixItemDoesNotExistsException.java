package tn.enicar.exception;


public class ChoixItemDoesNotExistsException  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ChoixItemDoesNotExistsException(String message) {
	        super(message);
	    }
}

