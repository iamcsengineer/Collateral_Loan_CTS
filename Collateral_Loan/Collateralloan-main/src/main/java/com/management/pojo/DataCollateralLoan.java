package com.management.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DataCollateralLoan {

	private int loanId;
	private String collateralName;
	private double collateralValue;
	private Date pledgedDate;

	private DataCollateralRealestate collateralRealestate;
	private DataCollateralCashdeposit collateralCashdeposit;
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
	public DataCollateralRealestate getCollateralRealestate() {
		return collateralRealestate;
	}
	public void setCollateralRealestate(DataCollateralRealestate collateralRealestate) {
		this.collateralRealestate = collateralRealestate;
	}
	public DataCollateralCashdeposit getCollateralCashdeposit() {
		return collateralCashdeposit;
	}
	public void setCollateralCashdeposit(DataCollateralCashdeposit collateralCashdeposit) {
		this.collateralCashdeposit = collateralCashdeposit;
	}

}
