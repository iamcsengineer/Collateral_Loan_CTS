package com.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//Entity for Collateral Real Estate Type
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "collateral_realestate")
public class CollateralRealestate {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "collateral_type")
	private String collateralType;
	@Column(name = "address")
	private String address;
	@Column(name = "current_value")
	private double currentvalue;
	@Column(name = "rate_per_sq_ft")
	private double ratepersqft;
	@Column(name = "depreciation_rate")
	private double depreciationrate;
	
	@OneToOne
	@JoinColumn(name="loan_id") 
	private CollateralLoan collateralLoan;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollateralType() {
		return collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getCurrentvalue() {
		return currentvalue;
	}

	public void setCurrentvalue(double currentvalue) {
		this.currentvalue = currentvalue;
	}

	public double getRatepersqft() {
		return ratepersqft;
	}

	public void setRatepersqft(double ratepersqft) {
		this.ratepersqft = ratepersqft;
	}

	public double getDepreciationrate() {
		return depreciationrate;
	}

	public void setDepreciationrate(double depreciationrate) {
		this.depreciationrate = depreciationrate;
	}

	public CollateralLoan getCollateralLoan() {
		return collateralLoan;
	}

	public void setCollateralLoan(CollateralLoan collateralLoan) {
		this.collateralLoan = collateralLoan;
	}
	
	}
