package it.riccardotomassoni.hrmanager;

public class ApiException extends RuntimeException {
	
	public ApiException(String message) {
		super(message);
	}
}
