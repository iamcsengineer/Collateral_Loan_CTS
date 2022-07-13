package com.portal.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DataCollateralCashdeposit {

	private String collateralType;

	private String bankname;

	private double currentvalue;

	private double interestrate;

	private double lockperiod;

	public String getCollateralType() {
		return collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public double getCurrentvalue() {
		return currentvalue;
	}

	public void setCurrentvalue(double currentvalue) {
		this.currentvalue = currentvalue;
	}

	public double getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(double interestrate) {
		this.interestrate = interestrate;
	}

	public double getLockperiod() {
		return lockperiod;
	}

	public void setLockperiod(double lockperiod) {
		this.lockperiod = lockperiod;
	}

}
