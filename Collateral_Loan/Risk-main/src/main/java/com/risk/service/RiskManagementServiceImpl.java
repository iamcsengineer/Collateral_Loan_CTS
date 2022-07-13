package com.risk.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.client.CollateralManagementClient;
import com.risk.entity.CollateralMarketValue;
import com.risk.entity.CollateralRisk;
import com.risk.exception.NoCollateralLoanFoundException;
import com.risk.pojo.DataCollateralCashdeposit;
import com.risk.pojo.DataCollateralLoan;
import com.risk.pojo.DataCollateralRisk;
import com.risk.repository.CollateralMarketValueRepository;
import com.risk.repository.CollateralRiskRepository;

import feign.FeignException.FeignClientException;

@Service
public class RiskManagementServiceImpl implements RiskManagementService {

//	private static final Logger LOGGER = LoggerFactory.getLogger(RiskManagementServiceImpl.class);

	@Autowired
	CollateralRiskRepository collateralRiskRepository;

	@Autowired
	CollateralManagementClient collateralManagementClient;

	@Autowired
	CollateralMarketValueRepository collateralMarketValueRepository;

	@Transactional
	public DataCollateralRisk getCollateralRisk(String token, int loanid) throws NoCollateralLoanFoundException {
//		LOGGER.info("Starting Get Collateral Risk Service ");
		try {

			DataCollateralLoan collateralByLoanId = collateralManagementClient.getCollateralByLoanId(token, loanid);

			DataCollateralRisk dataCollateralRisk = new DataCollateralRisk();

			dataCollateralRisk.setLoanid(loanid);
			dataCollateralRisk.setSanctionedLoan(collateralByLoanId.getCollateralValue());
			dataCollateralRisk.setDateAssessed(collateralByLoanId.getPledgedDate());

			try {
				DataCollateralCashdeposit collateralCashdeposit = collateralByLoanId.getCollateralCashdeposit();
				double interestrate2 = collateralByLoanId.getCollateralCashdeposit().getInterestrate();

				if (collateralMarketValueRepository.findByBankname(collateralCashdeposit.getBankname()).isPresent()) {
					CollateralMarketValue collateralMarketValueByBank = collateralMarketValueRepository
							.findByBankname(collateralByLoanId.getCollateralCashdeposit().getBankname()).get();
					double interestrate = collateralMarketValueByBank.getInterestrate();
					double rate = (interestrate2 / interestrate) * 100;
					dataCollateralRisk.setRiskpercent(rate);
				} else {
					dataCollateralRisk.setRiskpercent(100);
				}
				double currentvalue = collateralByLoanId.getCollateralCashdeposit().getCurrentvalue();
				long time = (new Date().getTime() - collateralByLoanId.getPledgedDate().getTime()) / (1000 * 3600 * 24);
				long year = time / 365;
				double marketvalue = (currentvalue * (Math.pow(((100 + interestrate2) / 100), year)));

				dataCollateralRisk.setMarketCurrentValue(marketvalue);

			} catch (NullPointerException e) {

				Optional<CollateralMarketValue> collateralMarketValueByAdress2 = collateralMarketValueRepository
						.findByAddress(collateralByLoanId.getCollateralRealestate().getAddress());
				if (collateralMarketValueByAdress2.isPresent()) {
					CollateralMarketValue collateralMarketValueByAdress = collateralMarketValueByAdress2.get();
					double ratepersqft = collateralMarketValueByAdress.getRatepersqft();
					double ratepersqft2 = collateralByLoanId.getCollateralRealestate().getRatepersqft();
					double rate = (ratepersqft2 / ratepersqft) * 100;
					dataCollateralRisk.setRiskpercent(rate);
				} else {
					dataCollateralRisk.setRiskpercent(100);
				}
				double depreciationrate = collateralByLoanId.getCollateralRealestate().getDepreciationrate();
				double currentvalue = collateralByLoanId.getCollateralRealestate().getCurrentvalue();
				long time = (new Date().getTime() - collateralByLoanId.getPledgedDate().getTime()) / (1000 * 3600 * 24);
				long year = time / 365;
				double marketvalue = (currentvalue * (Math.pow(((100 - depreciationrate) / 100), year)));
				dataCollateralRisk.setMarketCurrentValue(marketvalue);
			}
			CollateralRisk collateralRisk = new CollateralRisk();

			collateralRisk.setDateAssessed(dataCollateralRisk.getDateAssessed());
			collateralRisk.setLoanid(dataCollateralRisk.getLoanid());
			collateralRisk.setSanctionedLoan(dataCollateralRisk.getSanctionedLoan());
			collateralRisk.setRiskpercent(dataCollateralRisk.getRiskpercent());
			collateralRisk.setMarketCurrentValue(dataCollateralRisk.getMarketCurrentValue());
			collateralRiskRepository.save(collateralRisk);
//			LOGGER.info(collateralRisk.toString());
//			LOGGER.info("Ending Get Collateral Risk Service");
			return dataCollateralRisk;
		} catch (

		FeignClientException e) {
//			LOGGER.info(e.getLocalizedMessage());
//			LOGGER.info("Ending Get Collateral Risk Service");
			throw new NoCollateralLoanFoundException("No loan with this Id");
		}

	}

	public String readfile() throws IOException {

//		LOGGER.info("Starting Read File Service");
		File file = new File("src/main/resources/text.txt").getAbsoluteFile();
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			String s[] = sc.nextLine().split(",");
			try {
				CollateralMarketValue collateralMarketValue = new CollateralMarketValue();
				collateralMarketValue.setId(Integer.parseInt(s[0]));
				collateralMarketValue.setInterestrate(Double.parseDouble(s[1]));
				collateralMarketValue.setBankname(s[2]);
				collateralMarketValue.setAddress(s[3]);
				collateralMarketValue.setRatepersqft(Double.parseDouble(s[4]));
//				LOGGER.info(collateralMarketValue.toString());
				collateralMarketValueRepository.save(collateralMarketValue);
			} catch (Exception e) {
			}
		}
		sc.close();
//		LOGGER.info("Updated");
//		LOGGER.info("Ending Read File Service");
		return "Updated";
	}
}
