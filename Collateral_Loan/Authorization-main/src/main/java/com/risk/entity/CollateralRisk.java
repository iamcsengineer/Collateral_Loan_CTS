package com.risk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "collateral_risk")
public class CollateralRisk {

	@Id
	@Column(name = "loan_id")
	private int loanid;

	@Column(name = "risk_percent")
	private double riskpercent;

	@Column(name = "Date_Assessed")
	private Date dateAssessed;

	@Column(name = "Sanctioned_Loan")
	private double sanctionedLoan;

	@Column(name = "market_current_value")
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
