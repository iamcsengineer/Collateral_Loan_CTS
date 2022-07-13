package com.risk.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.risk.client.CollateralManagementClient;
import com.risk.entity.CollateralMarketValue;
import com.risk.entity.CollateralRisk;
import com.risk.exception.NoCollateralLoanFoundException;
import com.risk.pojo.DataCollateralCashdeposit;
import com.risk.pojo.DataCollateralLoan;
import com.risk.pojo.DataCollateralRealestate;
import com.risk.repository.CollateralMarketValueRepository;
import com.risk.repository.CollateralRiskRepository;

import feign.FeignException.FeignClientException;

@SpringBootTest
public class RiskManagementServiceImplTest {

	@InjectMocks
	RiskManagementServiceImpl service;

	@Mock
	CollateralManagementClient collateralManagementClient;

	DataCollateralLoan dataCollateralLoan;
	@Mock
	CollateralMarketValueRepository collateralMarketValueRepository;
	@Mock
	CollateralRisk collateralRisk;
	@Mock
	CollateralRiskRepository collateralRiskRepository;

	@Test
	void testGetCollateralRiskBankPresent() throws NoCollateralLoanFoundException {

		dataCollateralLoan = new DataCollateralLoan();

		dataCollateralLoan.setCollateralName("a");
		dataCollateralLoan.setCollateralValue(100.0);
		dataCollateralLoan.setLoanId(1);
		dataCollateralLoan.setPledgedDate(new Date());

		DataCollateralCashdeposit cashdeposit = new DataCollateralCashdeposit();
		cashdeposit.setBankname("a");
		cashdeposit.setCurrentvalue(100);
		cashdeposit.setId(1);
		cashdeposit.setInterestrate(5);
		dataCollateralLoan.setCollateralCashdeposit(cashdeposit);

		CollateralMarketValue collateralMarketValue = new CollateralMarketValue();
		collateralMarketValue.setBankname("a");
		collateralMarketValue.setInterestrate(10);
		
		Optional<CollateralMarketValue> value = Optional.of(collateralMarketValue);

		collateralRisk = new CollateralRisk();
		collateralRisk.setDateAssessed(new Date());
		collateralRisk.setLoanid(1);
		collateralRisk.setSanctionedLoan(100);
		collateralRisk.setRiskpercent(10);
		collateralRisk.setMarketCurrentValue(105);

		when(collateralRiskRepository.save(collateralRisk)).thenReturn(collateralRisk);
		
		when(collateralMarketValueRepository.findByBankname("a")).thenReturn(value);
		when(collateralManagementClient.getCollateralByLoanId("token", 1)).thenReturn(dataCollateralLoan);

		service.getCollateralRisk("token", 1);
	}

	@Test
	 void testGetCollateralRiskLoanNotPresent() throws NoCollateralLoanFoundException {
		
	when(collateralManagementClient.getCollateralByLoanId("token",100)).thenThrow(FeignClientException.class);
	assertThrows(NoCollateralLoanFoundException.class, () -> service.getCollateralRisk("token",100), "No loan with this Id");
	}
	
	
	
	@Test
	void testGetCollateralRiskBankAbsent() throws NoCollateralLoanFoundException {

		dataCollateralLoan = new DataCollateralLoan();

		dataCollateralLoan.setCollateralName("a");
		dataCollateralLoan.setCollateralValue(100.0);
		dataCollateralLoan.setLoanId(1);
		dataCollateralLoan.setPledgedDate(new Date());

		DataCollateralCashdeposit cashdeposit = new DataCollateralCashdeposit();
		cashdeposit.setBankname("a");
		cashdeposit.setCurrentvalue(100);
		cashdeposit.setId(1);
		cashdeposit.setInterestrate(5);
		dataCollateralLoan.setCollateralCashdeposit(cashdeposit);
		
		collateralRisk = new CollateralRisk();
		collateralRisk.setDateAssessed(new Date());
		collateralRisk.setLoanid(1);
		collateralRisk.setSanctionedLoan(100);
		collateralRisk.setRiskpercent(100);
		collateralRisk.setMarketCurrentValue(105);

		when(collateralRiskRepository.save(collateralRisk)).thenReturn(collateralRisk);
		when(collateralManagementClient.getCollateralByLoanId("token", 1)).thenReturn(dataCollateralLoan);

		service.getCollateralRisk("token", 1);

	}

	@Test
	void testGetCollateralRiskAdressPresent() throws NoCollateralLoanFoundException {
		dataCollateralLoan = new DataCollateralLoan();

		dataCollateralLoan.setCollateralName("a");
		dataCollateralLoan.setCollateralValue(100.0);
		dataCollateralLoan.setLoanId(1);
		dataCollateralLoan.setPledgedDate(new Date());

		DataCollateralRealestate collateralRealestate = new DataCollateralRealestate();
		collateralRealestate.setAddress("a");
		collateralRealestate.setCurrentvalue(100);
		collateralRealestate.setDepreciationrate(10);
		collateralRealestate.setId(1);
		collateralRealestate.setRatepersqft(100);
		dataCollateralLoan.setCollateralRealestate(collateralRealestate);

		CollateralMarketValue collateralMarketValue = new CollateralMarketValue();
		collateralMarketValue.setAddress("a");
		collateralMarketValue.setRatepersqft(10);

		Optional<CollateralMarketValue> value = Optional.of(collateralMarketValue);

		collateralRisk = new CollateralRisk();
		collateralRisk.setDateAssessed(new Date());
		collateralRisk.setLoanid(1);
		collateralRisk.setSanctionedLoan(100);
		collateralRisk.setRiskpercent(10);
		collateralRisk.setMarketCurrentValue(105);

		when(collateralRiskRepository.save(collateralRisk)).thenReturn(collateralRisk);
		
		when(collateralMarketValueRepository.findByAddress("a")).thenReturn(value);
		when(collateralManagementClient.getCollateralByLoanId("token", 1)).thenReturn(dataCollateralLoan);

		service.getCollateralRisk("token", 1);
	}

	@Test
	void testGetCollateralRiskAdressAbsent() throws NoCollateralLoanFoundException {
		dataCollateralLoan = new DataCollateralLoan();

		dataCollateralLoan.setCollateralName("a");
		dataCollateralLoan.setCollateralValue(100.0);
		dataCollateralLoan.setLoanId(1);
		dataCollateralLoan.setPledgedDate(new Date());

		DataCollateralRealestate collateralRealestate = new DataCollateralRealestate();
		collateralRealestate.setAddress("a");
		collateralRealestate.setCurrentvalue(100);
		collateralRealestate.setDepreciationrate(10);
		collateralRealestate.setId(1);
		collateralRealestate.setRatepersqft(100);
		dataCollateralLoan.setCollateralRealestate(collateralRealestate);
		
		collateralRisk = new CollateralRisk();
		collateralRisk.setDateAssessed(new Date());
		collateralRisk.setLoanid(1);
		collateralRisk.setSanctionedLoan(100);
		collateralRisk.setRiskpercent(100);
		collateralRisk.setMarketCurrentValue(105);

		when(collateralRiskRepository.save(collateralRisk)).thenReturn(collateralRisk);
		when(collateralManagementClient.getCollateralByLoanId("token", 1)).thenReturn(dataCollateralLoan);

		service.getCollateralRisk("token", 1);
	}
	
	
	@Test
	void testreadFile() throws IOException {
		
		String readfile = service.readfile();
		assertEquals(readfile, "Updated");
	}
}
