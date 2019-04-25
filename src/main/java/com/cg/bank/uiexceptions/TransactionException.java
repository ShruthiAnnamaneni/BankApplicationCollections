package com.cg.bank.uiexceptions;

public class TransactionException extends Exception {
	public TransactionException() {
		System.err.println("Fund Transfer failed");
	}

}
