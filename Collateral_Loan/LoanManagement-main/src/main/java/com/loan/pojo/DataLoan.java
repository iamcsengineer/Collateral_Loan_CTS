package com.loan.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DataLoan {
	private int id;
	private int maximumLoanEligibilityAmount;
	private double interest;
	private double tenure;
	private DataCustomerLoan customerloan;
	private DataCustomer dataCustomer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaximumLoanEligibilityAmount() {
		return maximumLoanEligibilityAmount;
	}
	public void setMaximumLoanEligibilityAmount(int maximumLoanEligibilityAmount) {
		this.maximumLoanEligibilityAmount = maximumLoanEligibilityAmount;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getTenure() {
		return tenure;
	}
	public void setTenure(double tenure) {
		this.tenure = tenure;
	}
	public DataCustomerLoan getCustomerloan() {
		return customerloan;
	}
	public void setCustomerloan(DataCustomerLoan customerloan) {
		this.customerloan = customerloan;
	}
	public DataCustomer getDataCustomer() {
		return dataCustomer;
	}
	public void setDataCustomer(DataCustomer dataCustomer) {
		this.dataCustomer = dataCustomer;
	}
}
