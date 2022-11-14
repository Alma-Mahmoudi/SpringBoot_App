package tn.enicar.exception;

public class SujetNotFoundException extends RuntimeException {
	  
	private static final long serialVersionUID = 1L;

	public SujetNotFoundException (String message) {
      super(message);
  }
}

