package com.management.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "collateral_loan")
//Entity for Loan with One to One mapping with Real Estate Data and Cash Deposit Data
public class CollateralLoan {

	@Id
	@Column(name = "loan_id")
	private int loanId;
	@Column(name = "collateral_name")
	private String collateralName;
	@Column(name = "collateral_value")
	private double collateralValue;
	@Column(name = "pledged_date")
	private Date pledgedDate;

	@OneToOne(mappedBy = "collateralLoan", cascade = CascadeType.ALL)
	private CollateralRealestate collateralRealestate;

	@OneToOne(mappedBy = "collateralLoan", cascade = CascadeType.ALL)
	private CollateralCashdeposit collateralCashdeposit;

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public String getCollateralName() {
		return collateralName;
	}

	public void setCollateralName(String collateralName) {
		this.collateralName = collateralName;
	}

	public double getCollateralValue() {
		return collateralValue;
	}

	public void setCollateralValue(double collateralValue) {
		this.collateralValue = collateralValue;
	}

	public Date getPledgedDate() {
		return pledgedDate;
	}

	public void setPledgedDate(Date pledgedDate) {
		this.pledgedDate = pledgedDate;
	}

	public CollateralRealestate getCollateralRealestate() {
		return collateralRealestate;
	}

	public void setCollateralRealestate(CollateralRealestate collateralRealestate) {
		this.collateralRealestate = collateralRealestate;
	}

	public CollateralCashdeposit getCollateralCashdeposit() {
		return collateralCashdeposit;
	}

	public void setCollateralCashdeposit(CollateralCashdeposit collateralCashdeposit) {
		this.collateralCashdeposit = collateralCashdeposit;
	}

}
