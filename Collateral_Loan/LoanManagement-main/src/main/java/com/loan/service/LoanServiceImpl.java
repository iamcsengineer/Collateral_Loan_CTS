package com.loan.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class LoanServiceImpl implements LoanService {

//	private static final Logger LOGGER = LoggerFactory.getLogger(LoanServiceImpl.class);

	@Autowired
	CustomerLoanRepo customerLoanRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	LoanRepo loanRepo;
	@Autowired
	CollateralManagementClient collateralManagementClient;

	@Transactional
	public DataCollateralLoan getLoanDetails(int loanId, String token) throws NoCollateralLoanFoundException {
//		LOGGER.info("Starting Get Loan Details Service");

		try {
			DataCollateralLoan collateralByLoanId = collateralManagementClient.getCollateralByLoanId(token, loanId);
			DataCustomer dataCustomer = new DataCustomer();
			Optional<Loan> loan = loanRepo.findById(loanId);
			Customer customer = loan.get().getCustomer();
			CustomerLoan customerLoan = loan.get().getCustomerloan();
			collateralByLoanId.setTenure(customerLoan.getTenure());
			collateralByLoanId.setInterest(customerLoan.getInterest());
			collateralByLoanId.setEmi(customerLoan.getEmi());
			dataCustomer.setName(customer.getName());
			dataCustomer.setAddress(customer.getAddress());
			dataCustomer.setPhoneNo(customer.getPhoneNo());
			collateralByLoanId.setCustomer(dataCustomer);
//			LOGGER.info(collateralByLoanId.toString());
//			LOGGER.info("Ending Get Loan Details Service");
			return collateralByLoanId;
		} catch (FeignClientException e) {
//			LOGGER.info(e.getLocalizedMessage());
//			LOGGER.info("Ending Get Loan Details Service");
			throw new NoCollateralLoanFoundException("No loan with this Id");
		}

	}

	
	@Transactional
	public String saveCollaterals(DataCollateralLoan dataCollateralLoan, String token)
			throws DataMissingException, CollateralLoanAlreadyException {
//		LOGGER.info("Starting Save Loan Details Service");

		try {
			if (loanRepo.findById(dataCollateralLoan.getLoanId()).isPresent()) {
				throw new CollateralLoanAlreadyException("loan with this Id is Already present");
			} else {
				double value = dataCollateralLoan.getCollateralValue();
				double tenure = dataCollateralLoan.getTenure();
				double interest = dataCollateralLoan.getInterest();

				if (dataCollateralLoan.getLoanId() == 0 || value == 0 || tenure == 0 || interest == 0) {
					throw new DataMissingException("Data Missing");
				}

				double interestpermonth = interest / 1200;
				double totalinterest = Math.pow((1 + interestpermonth), tenure);
				double emi = (value * interestpermonth) * ((totalinterest) / (totalinterest - 1));
				Loan loan = new Loan();
				loan.setId(dataCollateralLoan.getLoanId());
				loan.setInterest(interest);
				loan.setTenure(tenure);
				int flag = 0;
				try {
					DataCollateralCashdeposit collateralCashdeposit = dataCollateralLoan.getCollateralCashdeposit();
					double currentvalue = collateralCashdeposit.getCurrentvalue();
					int eligibleamount = (int) Math.ceil(currentvalue * 1.5);
					loan.setMaximumLoanEligibilityAmount(eligibleamount);
					flag = 1;
				} catch (NullPointerException e) {
					DataCollateralRealestate collateralRealestate = dataCollateralLoan.getCollateralRealestate();
					double currentvalue = collateralRealestate.getCurrentvalue();
					int eligibleamount = (int) Math.ceil(currentvalue * 1.5);
					loan.setMaximumLoanEligibilityAmount(eligibleamount);

					if (currentvalue == 0 || collateralRealestate.getDepreciationrate() == 0
							|| collateralRealestate.getRatepersqft() == 0
							|| collateralRealestate.getAddress() == null) {
						throw new DataMissingException("Data Missing");
					}

				}
				if (flag == 1) {
					if (dataCollateralLoan.getCollateralCashdeposit().getCurrentvalue() == 0
							|| dataCollateralLoan.getCollateralCashdeposit().getInterestrate() == 0
							|| dataCollateralLoan.getCollateralCashdeposit().getLockperiod() == 0
							|| dataCollateralLoan.getCollateralCashdeposit().getBankname() == null) {
						throw new DataMissingException("Data Missing");
					}
				}
				Customer customer = new Customer();
				customer.setName(dataCollateralLoan.getCustomer().getName());
				customer.setAddress(dataCollateralLoan.getCustomer().getAddress());
				customer.setPhoneNo(dataCollateralLoan.getCustomer().getPhoneNo());
				customer.setLoan(loan);
				loan.setCustomer(customer);

				CustomerLoan cloan = new CustomerLoan();
				cloan.setLoanPrincipal(value);
				cloan.setTenure(tenure);
				cloan.setEmi(Math.ceil(emi));
				cloan.setInterest(interest);

				cloan.setLoan(loan);
				loan.setCustomerloan(cloan);
				String ret = "";
				if (loan.getMaximumLoanEligibilityAmount() >= value) {
					loanRepo.save(loan);
					collateralManagementClient.saveCollateral(token, dataCollateralLoan);
//					LOGGER.info(loanRepo.toString());
//					LOGGER.info("Your Loan has been Approved");
//					LOGGER.info("Ending Get Loan Details Service");
					ret = "Your Loan has been Approved";
				} else {
					ret = "You Cannot Take a loan above your Maximum Eligibilty Amount Rs "
							+ loan.getMaximumLoanEligibilityAmount();
//					LOGGER.info(ret);
//					LOGGER.info("Ending Get Loan Details Service");
				}
				return ret;
			}
		} catch (NullPointerException e) {
//			LOGGER.info(e.getLocalizedMessage());
//			LOGGER.info("Ending Get Loan Details Service");
			throw new DataMissingException("Data Missing");

		}
	}
}
