package com.loan.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "loan")
public class Loan {
	
	@Id
	@Column(name = "loan_id")
	private int id;
	@Column(name = "max_eligible")
	private int maximumLoanEligibilityAmount;
	@Column(name = "interest")
	private double interest;
	@Column(name = "tenure")
	private double tenure;
	
	@OneToOne(mappedBy = "loan", cascade = CascadeType.ALL)
	private CustomerLoan customerloan;
	
	@OneToOne(mappedBy = "loan", cascade = CascadeType.ALL)
	private Customer customer;

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

	public CustomerLoan getCustomerloan() {
		return customerloan;
	}

	public void setCustomerloan(CustomerLoan customerloan) {
		this.customerloan = customerloan;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
