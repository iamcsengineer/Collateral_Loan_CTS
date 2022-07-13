package com.management.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.entity.CollateralCashdeposit;
import com.management.entity.CollateralLoan;
import com.management.entity.CollateralRealestate;
import com.management.exception.NoCollateralLoanFoundException;
import com.management.pojo.DataCollateralCashdeposit;
import com.management.pojo.DataCollateralLoan;
import com.management.pojo.DataCollateralRealestate;
import com.management.repository.CollateralCashDepositRepository;
import com.management.repository.CollateralLoanRepository;
import com.management.repository.CollateralRealestateRepository;

@Service
public class ManagementServiceImpl implements ManagementService {

	@Autowired
	CollateralCashDepositRepository collateralCashDepositRepository;
	@Autowired
	CollateralLoanRepository collateralLoanRepository;
	@Autowired
	CollateralRealestateRepository collateralRealestateRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagementServiceImpl.class);

	//Get the collateral details according to the loan id
	@Transactional
	public DataCollateralLoan getCollateralLoan(int loanid) throws NoCollateralLoanFoundException {

		LOGGER.info("Starting Get Collateral Loan Details Service");

		Optional<CollateralLoan> findById = collateralLoanRepository.findById(loanid);
		if (!findById.isPresent()) {
			throw new NoCollateralLoanFoundException("No loan with this Id");
		} else {
			CollateralLoan collateralLoan = findById.get();
			CollateralCashdeposit collateralCashdeposit = collateralLoan.getCollateralCashdeposit();

			DataCollateralLoan dataCollateralLoan = new DataCollateralLoan();

			dataCollateralLoan.setLoanId(collateralLoan.getLoanId());
			dataCollateralLoan.setCollateralName(collateralLoan.getCollateralName());
			dataCollateralLoan.setCollateralValue(collateralLoan.getCollateralValue());
			dataCollateralLoan.setPledgedDate(collateralLoan.getPledgedDate());

			if ((collateralCashdeposit == null)) {
				DataCollateralRealestate dataCollateralRealestate = new DataCollateralRealestate();

				dataCollateralRealestate.setAddress(collateralLoan.getCollateralRealestate().getAddress());
				dataCollateralRealestate
						.setCollateralType(collateralLoan.getCollateralRealestate().getCollateralType());
				dataCollateralRealestate.setCurrentvalue(collateralLoan.getCollateralRealestate().getCurrentvalue());
				dataCollateralRealestate
						.setDepreciationrate(collateralLoan.getCollateralRealestate().getDepreciationrate());
				dataCollateralRealestate.setId(collateralLoan.getCollateralRealestate().getId());
				dataCollateralRealestate.setRatepersqft(collateralLoan.getCollateralRealestate().getRatepersqft());

				dataCollateralLoan.setCollateralRealestate(dataCollateralRealestate);
			} else {
				DataCollateralCashdeposit dataCollateralCashdeposit = new DataCollateralCashdeposit();

				dataCollateralCashdeposit.setBankname(collateralLoan.getCollateralCashdeposit().getBankname());
				dataCollateralCashdeposit
						.setCollateralType(collateralLoan.getCollateralCashdeposit().getCollateralType());
				dataCollateralCashdeposit.setCurrentvalue(collateralLoan.getCollateralCashdeposit().getCurrentvalue());
				dataCollateralCashdeposit.setId(collateralLoan.getCollateralCashdeposit().getId());
				dataCollateralCashdeposit.setInterestrate(collateralLoan.getCollateralCashdeposit().getInterestrate());
				dataCollateralCashdeposit.setLockperiod(collateralLoan.getCollateralCashdeposit().getLockperiod());
				dataCollateralLoan.setCollateralCashdeposit(dataCollateralCashdeposit);
			}
//			LOGGER.info(dataCollateralLoan.toString());
//			LOGGER.info("Ending Get Collateral Loan Details Service");
			return dataCollateralLoan;
		}

	}
	
	//Save the collateral details accoring to the loan id
	@Transactional
	public String saveCollateralLoan(CollateralLoan collateralLoan) {
//		LOGGER.info("Starting Save Collateral Loan Details Service");
		CollateralLoan collateralLoan1 = new CollateralLoan();

		collateralLoan1.setLoanId(collateralLoan.getLoanId());
		collateralLoan1.setCollateralValue(collateralLoan.getCollateralValue());
		collateralLoan1.setPledgedDate(new Date());

		if (!(collateralLoan.getCollateralRealestate() == null)) {
			CollateralRealestate collateralRealestate = new CollateralRealestate();

			collateralLoan1.setCollateralName("COLLATERAL REAL ESTATE");

			collateralRealestate.setAddress(collateralLoan.getCollateralRealestate().getAddress());
			collateralRealestate.setCollateralType(collateralLoan.getCollateralRealestate().getCollateralType());
			collateralRealestate.setCurrentvalue(collateralLoan.getCollateralRealestate().getCurrentvalue());
			collateralRealestate.setDepreciationrate(collateralLoan.getCollateralRealestate().getDepreciationrate());
			collateralRealestate.setId(collateralLoan.getCollateralRealestate().getId());
			collateralRealestate.setRatepersqft(collateralLoan.getCollateralRealestate().getRatepersqft());

			collateralRealestate.setCollateralLoan(collateralLoan1);
			collateralLoan1.setCollateralRealestate(collateralRealestate);
		}
		if (!(collateralLoan.getCollateralCashdeposit() == null)) {
			CollateralCashdeposit collateralCashdeposit = new CollateralCashdeposit();

			collateralLoan1.setCollateralName("COLLATERAL CASH DEPOSIT");

			collateralCashdeposit.setBankname(collateralLoan.getCollateralCashdeposit().getBankname());
			collateralCashdeposit.setCollateralType(collateralLoan.getCollateralCashdeposit().getCollateralType());
			collateralCashdeposit.setCurrentvalue(collateralLoan.getCollateralCashdeposit().getCurrentvalue());
			collateralCashdeposit.setId(collateralLoan.getCollateralCashdeposit().getId());
			collateralCashdeposit.setInterestrate(collateralLoan.getCollateralCashdeposit().getInterestrate());
			collateralCashdeposit.setLockperiod(collateralLoan.getCollateralCashdeposit().getLockperiod());
			collateralCashdeposit.setCollateralLoan(collateralLoan1);
			collateralLoan1.setCollateralCashdeposit(collateralCashdeposit);
		}
		collateralLoanRepository.save(collateralLoan1);
//		LOGGER.info(collateralLoan1.toString());
//		LOGGER.info("Collateral Loan Inserted");
//		LOGGER.info("Ending Save Collateral Loan Details Service");
		return "Collateral Loan Inserted";

	}

}
