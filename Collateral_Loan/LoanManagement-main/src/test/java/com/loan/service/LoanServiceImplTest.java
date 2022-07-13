package com.loan.service;

// import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.loan.client.CollateralManagementClient;
import com.loan.entity.Customer;
import com.loan.entity.CustomerLoan;
import com.loan.entity.Loan;
import com.loan.exception.CollateralLoanAlreadyException;
import com.loan.exception.DataMissingException;
import com.loan.exception.NoCollateralLoanFoundException;
import com.loan.pojo.DataCollateralCashdeposit;
import com.loan.pojo.DataCollateralLoan;
import com.loan.pojo.DataCollateralRealestate;
import com.loan.pojo.DataCustomer;
import com.loan.repository.CustomerLoanRepo;
import com.loan.repository.CustomerRepo;
import com.loan.repository.LoanRepo;

import feign.FeignException.FeignClientException;

@SpringBootTest
public class LoanServiceImplTest {

	@InjectMocks
	LoanServiceImpl loanServiceImpl;

	@Mock
	CustomerLoanRepo customerLoanRepo;
	@Mock
	CustomerRepo customerRepo;
	@Mock
	LoanRepo loanRepo;

	@Mock
	CollateralManagementClient collateralManagementClient;

	DataCollateralLoan dataCollateralLoan;

	@Test
	void testGetLoanDetails() throws NoCollateralLoanFoundException {
		dataCollateralLoan = new DataCollateralLoan();
		dataCollateralLoan.setLoanId(1);
		dataCollateralLoan.setCollateralName("a");
		dataCollateralLoan.setCollateralValue(100.0);
		dataCollateralLoan.setPledgedDate(new Date());
		dataCollateralLoan.setTenure(10);
		dataCollateralLoan.setInterest(10);
		dataCollateralLoan.setEmi(100.0);

		DataCollateralCashdeposit cashdeposit = new DataCollateralCashdeposit();
		cashdeposit.setBankname("a");
		cashdeposit.setCurrentvalue(100);
		cashdeposit.setInterestrate(5);
		dataCollateralLoan.setCollateralCashdeposit(cashdeposit);

		DataCollateralRealestate collateralRealestate = new DataCollateralRealestate();
		collateralRealestate.setAddress("a");
		collateralRealestate.setCurrentvalue(100);
		collateralRealestate.setDepreciationrate(10);
		collateralRealestate.setRatepersqft(100);
		dataCollateralLoan.setCollateralRealestate(collateralRealestate);

		DataCustomer customer1 = new DataCustomer();
		customer1.setAddress("a");
		customer1.setName("a");
		customer1.setPhoneNo(123456);

		dataCollateralLoan.setCustomer(customer1);

		Loan loan = new Loan();
		loan.setId(1);

		Customer customer = new Customer();
		customer.setCustomerid(1);
		customer.setLoan(loan);
		CustomerLoan customerloan = new CustomerLoan();
		customerloan.setLoanProductId(1);
		loan.setCustomer(customer);
		loan.setCustomerloan(customerloan);
		
		Optional<Loan> loan1 = Optional.of(loan);
		when(loanRepo.findById(1)).thenReturn(loan1);
		when(collateralManagementClient.getCollateralByLoanId("token", 1)).thenReturn(dataCollateralLoan);
		assertEquals(dataCollateralLoan, loanServiceImpl.getLoanDetails(1, "token"));

	}

	@Test
	void testGetLoanDetailsEmpty() throws NoCollateralLoanFoundException {

		when(collateralManagementClient.getCollateralByLoanId("token", 100)).thenThrow(FeignClientException.class);
		assertThrows(NoCollateralLoanFoundException.class, () -> loanServiceImpl.getLoanDetails(100, "token"),
				"No loan with this Id");
	}

	@Test
	public void testSaveCollaterals() throws DataMissingException, CollateralLoanAlreadyException {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);

		DataCollateralRealestate collateralRealestate = new DataCollateralRealestate();
		collateralRealestate.setCurrentvalue(1000);
		collateralRealestate.setAddress("a");
		collateralRealestate.setRatepersqft(10);
		collateralRealestate.setDepreciationrate(100);

		DataCustomer customer = new DataCustomer();
		customer.setAddress("a");
		customer.setName("a");
		customer.setPhoneNo(123456);

		value.setCustomer(customer);
		value.setCollateralRealestate(collateralRealestate);

		String saveCollaterals = loanServiceImpl.saveCollaterals(value, "token");
		assertEquals("You Cannot Take a loan above your Maximum Eligibilty Amount Rs 1500", saveCollaterals);
	}

	@Test
	public void testSaveCollateralsCashDeposit() throws DataMissingException, CollateralLoanAlreadyException {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(1000);
		value.setTenure(10);
		value.setInterest(10);
		value.setCollateralName("a");
		value.setPledgedDate(new Date());
		value.setEmi(1000);

		DataCollateralCashdeposit cashdeposit = new DataCollateralCashdeposit();
		cashdeposit.setCurrentvalue(1000);
		cashdeposit.setBankname("Ad");
		cashdeposit.setCollateralType("ab");
		cashdeposit.setInterestrate(5);
		cashdeposit.setLockperiod(2);

		DataCustomer customer = new DataCustomer();
		customer.setAddress("a");
		customer.setName("a");
		customer.setPhoneNo(123456);

		value.setCustomer(customer);
		value.setCollateralCashdeposit(cashdeposit);

		String saveCollaterals = loanServiceImpl.saveCollaterals(value, "token");
		assertEquals("Your Loan has been Approved",saveCollaterals);
	}

	@Test
	void testSaveCollateralsEmpty() {
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(null, "token"), "Data Missing");
	}

	@Test
	void testSaveCollateralsLoanIdNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(0);
		value.setCollateralValue(1000);
		value.setTenure(10);
		value.setInterest(10);

		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	void testSaveCollateralsValueNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setCollateralValue(0);
		value.setLoanId(1);
		value.setTenure(10);
		value.setInterest(10);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	void testSaveCollateralsTenureNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setTenure(0);
		value.setLoanId(1);
		value.setCollateralValue(1000);
		value.setInterest(10);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	void testSaveCollateralsInterestNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setInterest(0);
		value.setLoanId(1);
		value.setCollateralValue(1000);
		value.setTenure(10);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	public void testSaveCollateralsRealestateValueNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);
		DataCollateralRealestate collateralRealestate = new DataCollateralRealestate();
		collateralRealestate.setAddress("a");
		collateralRealestate.setRatepersqft(10);
		collateralRealestate.setDepreciationrate(100);
		collateralRealestate.setCurrentvalue(0);
		value.setCollateralRealestate(collateralRealestate);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	public void testSaveCollateralsRealestateDepricationNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);
		DataCollateralRealestate collateralRealestate = new DataCollateralRealestate();
		collateralRealestate.setDepreciationrate(0);
		collateralRealestate.setCurrentvalue(1000);
		collateralRealestate.setAddress("a");
		collateralRealestate.setRatepersqft(10);
		value.setCollateralRealestate(collateralRealestate);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	public void testSaveCollateralsRealestateRatesqftNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);
		DataCollateralRealestate collateralRealestate = new DataCollateralRealestate();
		collateralRealestate.setRatepersqft(0);
		collateralRealestate.setCurrentvalue(1000);
		collateralRealestate.setAddress("a");
		collateralRealestate.setDepreciationrate(100);

		value.setCollateralRealestate(collateralRealestate);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	public void testSaveCollateralsRealestateAdressNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);
		DataCollateralRealestate collateralRealestate = new DataCollateralRealestate();
		collateralRealestate.setCurrentvalue(1000);
		collateralRealestate.setRatepersqft(10);
		collateralRealestate.setDepreciationrate(100);
		collateralRealestate.setAddress(null);
		value.setCollateralRealestate(collateralRealestate);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	
	@Test
	public void testSaveCollateralsCashDepositBankNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);
		DataCollateralCashdeposit cashdeposit = new DataCollateralCashdeposit();
		cashdeposit.setBankname(null);
		cashdeposit.setCurrentvalue(100);
		cashdeposit.setInterestrate(5);
		cashdeposit.setLockperiod(1);
		value.setCollateralCashdeposit(cashdeposit);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	public void testSaveCollateralsCashDepositValueNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);
		DataCollateralCashdeposit cashdeposit = new DataCollateralCashdeposit();
		cashdeposit.setBankname("a");
		cashdeposit.setCurrentvalue(0);
		cashdeposit.setLockperiod(1);
		cashdeposit.setInterestrate(5);
		value.setCollateralCashdeposit(cashdeposit);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	public void testSaveCollateralsCashDepositInterestNull() throws DataMissingException {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);
		DataCollateralCashdeposit cashdeposit = new DataCollateralCashdeposit();
		cashdeposit.setBankname("a");
		cashdeposit.setCurrentvalue(100);
		cashdeposit.setLockperiod(1);
		cashdeposit.setInterestrate(0);

		value.setCollateralCashdeposit(cashdeposit);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}

	@Test
	public void testSaveCollateralsCashDepositLockPeriodNull() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);
		DataCollateralCashdeposit cashdeposit = new DataCollateralCashdeposit();
		cashdeposit.setBankname("a");
		cashdeposit.setCurrentvalue(100);
		cashdeposit.setLockperiod(0);
		cashdeposit.setInterestrate(5);
		value.setCollateralCashdeposit(cashdeposit);
		assertThrows(DataMissingException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "Data Missing");
	}
	
	
	@Test
	public void testSaveCollateralsLoanIdPresent() {
		DataCollateralLoan value = new DataCollateralLoan();
		value.setLoanId(1);
		value.setCollateralValue(10000);
		value.setTenure(10);
		value.setInterest(10);
		DataCollateralRealestate collateralRealestate = new DataCollateralRealestate();
		collateralRealestate.setCurrentvalue(1000);
		collateralRealestate.setRatepersqft(10);
		collateralRealestate.setDepreciationrate(100);
		collateralRealestate.setAddress(null);
		value.setCollateralRealestate(collateralRealestate);
		
		Loan value1= new Loan();
		value.setLoanId(1);
		
		Optional<Loan> value2 = Optional.of(value1);
		when(loanRepo.findById(1)).thenReturn(value2);
		assertThrows(CollateralLoanAlreadyException.class, () -> loanServiceImpl.saveCollaterals(value, "token"), "loan with this Id is Already present");
	}

}
