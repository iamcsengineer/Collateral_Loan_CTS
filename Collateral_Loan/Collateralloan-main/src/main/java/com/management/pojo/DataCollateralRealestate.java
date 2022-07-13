package com.management.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DataCollateralRealestate {
	
	private int id;
	private String collateralType;
	private String address;
	private double currentvalue;
	private double ratepersqft;
	private double depreciationrate;
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
	
	}
