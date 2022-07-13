package com.risk.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DataCollateralRisk {

	private int loanid;
	private double riskpercent;
	private Date dateAssessed;
	private double sanctionedLoan;
	private double marketCurrentValue;

	public int getLoanid() {
		return loanid;
	}

	public void setLoanid(int loanid) {
		this.loanid = loanid;
	}

	public double getRiskpercent() {
		return riskpercent;
	}

	public void setRiskpercent(double riskpercent) {
		this.riskpercent = riskpercent;
	}

	public Date getDateAssessed() {
		return dateAssessed;
	}

	public void setDateAssessed(Date dateAssessed) {
		this.dateAssessed = dateAssessed;
	}

	public double getSanctionedLoan() {
		return sanctionedLoan;
	}

	public void setSanctionedLoan(double sanctionedLoan) {
		this.sanctionedLoan = sanctionedLoan;
	}

	public double getMarketCurrentValue() {
		return marketCurrentValue;
	}

	public void setMarketCurrentValue(double marketCurrentValue) {
		this.marketCurrentValue = marketCurrentValue;
	}
}
