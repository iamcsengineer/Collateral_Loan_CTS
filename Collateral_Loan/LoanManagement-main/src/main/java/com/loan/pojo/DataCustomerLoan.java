package com.loan.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DataCustomerLoan {

	private int loanProductId;
	private double loanPrincipal;
	private double tenure;
	private double interest;
	private double emi;

	public int getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(int loanProductId) {
		this.loanProductId = loanProductId;
	}

	public double getLoanPrincipal() {
		return loanPrincipal;
	}

	public void setLoanPrincipal(double loanPrincipal) {
		this.loanPrincipal = loanPrincipal;
	}

	public double getTenure() {
		return tenure;
	}

	public void setTenure(double tenure) {
		this.tenure = tenure;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}
}
