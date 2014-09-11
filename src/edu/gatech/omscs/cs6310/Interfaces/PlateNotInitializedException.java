package edu.gatech.omscs.cs6310.Interfaces;

public class PlateNotInitializedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlateNotInitializedException(String message) {
		super(message);
	}

	public PlateNotInitializedException(String message, Throwable cause) {
		super(message, cause);
	}
}
