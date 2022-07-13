package com.portal.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CollateralLoanJSP {

	@Min(value = 1, message = "{error.loanId.negative}")
	@NotEmpty(message = "{error.loanId.data}")
	private String loanId;

	@Min(value = 1, message = "{error.collateralValue.negative}")
	@NotEmpty(message = "{error.collateralValue.data}")
	private String collateralValue;

	@Min(value = 1, message = "{error.tenure.negative}")
	@NotEmpty(message = "{error.tenure.data}")
	private String tenure;

	@Min(value = 1, message = "{error.interest.negative}")
	@NotEmpty(message = "{error.interest.data}")
	private String interest;

	@NotEmpty(message = "{error.collateralType.data}")
	private String collateralType;

	@NotEmpty(message = "{error.detail.data}")
	private String detail;

	@Min(value = 1, message = "{error.currentvalue.negative}")
	@NotEmpty(message = "{error.currentvalue.data}")
	private String currentvalue;

	@Min(value = 1, message = "{error.detail.negative}")
	@NotEmpty(message = "{error.detail.data}")
	private String detail1;

	@Min(value = 1, message = "{error.detail.negative}")
	@NotEmpty(message = "{error.detail.data}")
	private String detail2;

	@NotEmpty(message = "{error.custname.data}")
	private String custname;

	@NotEmpty(message = "{error.custaddress.data}")
	private String custaddress;

	@Min(value = 1, message = "{error.custphoneNo.negative}")
	@NotEmpty(message = "{error.custphoneNo.data}")
	private String custphoneNo;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getCollateralValue() {
		return collateralValue;
	}

	public void setCollateralValue(String collateralValue) {
		this.collateralValue = collateralValue;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getCollateralType() {
		return collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCurrentvalue() {
		return currentvalue;
	}

	public void setCurrentvalue(String currentvalue) {
		this.currentvalue = currentvalue;
	}

	public String getDetail1() {
		return detail1;
	}

	public void setDetail1(String detail1) {
		this.detail1 = detail1;
	}

	public String getDetail2() {
		return detail2;
	}

	public void setDetail2(String detail2) {
		this.detail2 = detail2;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCustaddress() {
		return custaddress;
	}

	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}

	public String getCustphoneNo() {
		return custphoneNo;
	}

	public void setCustphoneNo(String custphoneNo) {
		this.custphoneNo = custphoneNo;
	}
}