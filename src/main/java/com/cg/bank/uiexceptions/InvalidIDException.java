package com.cg.bank.uiexceptions;

public class InvalidIDException extends Exception {
	public InvalidIDException() {
		System.err.println("Invalid Credentials");
	}

}
