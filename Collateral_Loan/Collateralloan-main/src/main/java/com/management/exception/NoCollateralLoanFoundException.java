package com.management.exception;
//Loan with the given loan Id not found exception
public class NoCollateralLoanFoundException extends Exception {

	public NoCollateralLoanFoundException(String string)
	{
		super(string);
	}
}
	