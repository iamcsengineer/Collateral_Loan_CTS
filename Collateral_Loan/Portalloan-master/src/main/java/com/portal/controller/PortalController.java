package com.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.portal.client.AuthClient;
import com.portal.client.LoanClient;
import com.portal.client.RiskClient;
import com.portal.pojo.CollateralLoanJSP;
import com.portal.pojo.DataCollateralCashdeposit;
import com.portal.pojo.DataCollateralLoan;
import com.portal.pojo.DataCollateralRealestate;
import com.portal.pojo.DataCollateralRisk;
import com.portal.pojo.DataCustomer;
import com.portal.pojo.UserData;

@Controller
public class PortalController {
	@Autowired
	AuthClient authClient;
	@Autowired
	LoanClient loanClient;
	@Autowired
	RiskClient riskClient;

	private static final Logger LOGGER = LoggerFactory.getLogger(PortalController.class);

	@GetMapping("/health")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Health is OK", HttpStatus.OK);
	}

//	For Login Page 
	@GetMapping("/")
	public ModelAndView redirectLogin() {
		LOGGER.info("Starting Portal");
		LOGGER.info("Ending Portal");
		return new ModelAndView("Login");
	}

//	after login page
	@PostMapping("/login")
	public ModelAndView postLogin(@ModelAttribute UserData user, HttpServletRequest request) {
		LOGGER.info("Starting Post Login");
		UserData res = null;
		try {
			res = authClient.login(user);
		} catch (Exception e) {
			ModelAndView modelandview = new ModelAndView("Login");
			String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
					+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
					+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
					+ "			Your Username / Password is <strong> Wrong!</strong>\r\n" + "		</div>";
			modelandview.addObject("msg", str);
			LOGGER.error(e.getMessage());
			LOGGER.info("Ending Post Login");
			return modelandview;
		}
		request.getSession().setAttribute("token", "Bearer " + res.getAuthToken());
		request.getSession().setAttribute("name", user.getUserid());
		LOGGER.info(res.toString());
		LOGGER.info("Ending Post Login");
		return viewAllBranch(request);
	}
	
//  Displaying all Branches
	@GetMapping("/portalbranch")
	public ModelAndView viewAllBranch(HttpServletRequest request) {
		LOGGER.info("Starting View all branch");
		ModelAndView modelandview = new ModelAndView("PortalBranch");
		LOGGER.info("Ending View all branch");
		return modelandview;
	}

//	Getting Loan Details
	@GetMapping("/getloan")
	public ModelAndView getLoanPage(HttpServletRequest request) {
		LOGGER.info("Starting Get all Detail");
		LOGGER.info("Ending Get all Detail");
		return new ModelAndView("GetLoanDetails");
	}

//	Saving Loan Details
	@PostMapping("/postloan")
	public ModelAndView postLoanPage(HttpServletRequest request) {
		LOGGER.info("Starting Get all Detail Post");
		ModelAndView mv = new ModelAndView("GetLoanDetails");
		String token = (String) request.getSession().getAttribute("token");
		try {
			authClient.verifyToken(token);
			try {
				int loanid = Integer.parseInt(request.getParameter("loanid"));
				DataCollateralLoan details = loanClient.getLoanDetails(token, loanid);
				try {
					details.getCollateralCashdeposit();
					DataCollateralCashdeposit cash = new DataCollateralCashdeposit();
					cash.setBankname(details.getCollateralCashdeposit().getBankname());
					cash.setCollateralType(details.getCollateralCashdeposit().getCollateralType());
					cash.setCurrentvalue(details.getCollateralCashdeposit().getCurrentvalue());
					cash.setInterestrate(details.getCollateralCashdeposit().getInterestrate());
					cash.setLockperiod(details.getCollateralCashdeposit().getLockperiod());
					mv.addObject("Type", cash);
					mv.addObject("Loan", details);
					LOGGER.info(cash.toString());
					LOGGER.info(details.toString());
					LOGGER.info("Ending Get all Detail Post");
					mv.setViewName("DisplayCASH");
				} catch (NullPointerException e) {
					DataCollateralRealestate real = new DataCollateralRealestate();
					real.setAddress(details.getCollateralRealestate().getAddress());
					real.setCollateralType(details.getCollateralRealestate().getCollateralType());
					real.setCurrentvalue(details.getCollateralRealestate().getCurrentvalue());
					real.setDepreciationrate(details.getCollateralRealestate().getDepreciationrate());
					real.setRatepersqft(details.getCollateralRealestate().getRatepersqft());
					mv.addObject("Type", real);
					mv.addObject("Loan", details);
					LOGGER.info(real.toString());
					LOGGER.info(details.toString());
					LOGGER.info("Ending Get all Detail Post");
					mv.setViewName("DisplayREAL");
				}
			} catch (Exception e) {
				String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
						+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
						+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
						+ "			Loan Not<strong> Found!</strong>\r\n" + "		</div>";
				mv.addObject("msg", str);
				LOGGER.error(e.getMessage());
				LOGGER.info("Ending Get all Detail Post");
			}
			return mv;
		} catch (Exception e) {
			ModelAndView modelandview = new ModelAndView("Login");
			String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
					+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
					+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
					+ "			Your Token has <strong>Expired!</strong>\r\n" + "		</div>";
			modelandview.addObject("msg", str);
			LOGGER.error(e.getMessage());
			LOGGER.info("Ending Get all Detail Post");
			return modelandview;
		}
	}
	
//  Getting Risk Details
	@GetMapping("/getrisk")
	public ModelAndView getRiskPage(HttpServletRequest request) {
		LOGGER.info("Starting Get all Risk");
		String token = (String) request.getSession().getAttribute("token");
		try {
			authClient.verifyToken(token);
			String updateCollateralMarketValue = riskClient.updateCollateralMarketValue(token);
			LOGGER.info(updateCollateralMarketValue);
			LOGGER.info("Ending Get all Risk");
			return new ModelAndView("GetRiskDetails");
		} catch (Exception e) {
			ModelAndView modelandview = new ModelAndView("Login");
			String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
					+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
					+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
					+ "			Your Token has <strong>Expired!</strong>\r\n" + "		</div>";
			modelandview.addObject("msg", str);
			LOGGER.error(e.getMessage());
			LOGGER.info("Ending Get all Risk");
			return modelandview;
		}
	}

//	Saving Risk details by Loan id 
	@PostMapping("/postrisk")
	public ModelAndView postRiskPage(HttpServletRequest request) {
		LOGGER.info("Starting Get all Risk Post");
		ModelAndView mv = new ModelAndView("GetRiskDetails");
		String token = (String) request.getSession().getAttribute("token");
		try {
			authClient.verifyToken(token);
			try {
				int riskid = Integer.parseInt(request.getParameter("riskid"));
				DataCollateralRisk risk = riskClient.getCollateralRiskByLoanId(token, riskid);
				mv.setViewName("DisplayRISK");
				mv.addObject("risk", risk);
				LOGGER.info(risk.toString());
			} catch (Exception e) {
				String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
						+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
						+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
						+ "			Loan Not<strong> Found!</strong>\r\n" + "		</div>";
				mv.addObject("msg", str);
				LOGGER.error(e.getMessage());
			}
			LOGGER.info("Ending Get all Risk Post");
			return mv;
		} catch (Exception e) {
			ModelAndView modelandview = new ModelAndView("Login");
			String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
					+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
					+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
					+ "			Your Token has <strong>Expired!</strong>\r\n" + "		</div>";
			modelandview.addObject("msg", str);
			LOGGER.error(e.getMessage());
			// LOGGER.info("Ending Get all Risk Post");
			return modelandview;
		}
	}

//	Displaying Realestate  Values
	@GetMapping("/getrealestate")
	public ModelAndView getRealEstatePage(HttpServletRequest request, Model model) {
		 LOGGER.info("Starting Get Real Estate");
		model.addAttribute("realestate", new CollateralLoanJSP());
		 LOGGER.info("Ending Get Real Estate");
		return new ModelAndView("PostRealEstate");
	}

//	Saving Realestate Details
	@PostMapping("/postrealestate")
	public ModelAndView postRealEstatePage(@ModelAttribute("realestate") @Valid CollateralLoanJSP realestate,
			BindingResult result, HttpServletRequest request) {
		LOGGER.info("Starting Get Real Estate Post");
		ModelAndView mv = new ModelAndView("PortalBranch");
		if (result.hasErrors()) {
			LOGGER.error(result.toString());
			// LOGGER.info("Ending Get Real Estate Post");
			mv.setViewName("PostRealEstate");
			return mv;
		}
		String token = (String) request.getSession().getAttribute("token");
		try {
			authClient.verifyToken(token);
			try {
				DataCollateralLoan loan = new DataCollateralLoan();
				DataCollateralRealestate real = new DataCollateralRealestate();
				DataCustomer cust = new DataCustomer();

				cust.setName(realestate.getCustname());
				cust.setPhoneNo(Long.parseLong(realestate.getCustphoneNo()));
				cust.setAddress(realestate.getCustaddress());
				loan.setCustomer(cust);

				real.setAddress(realestate.getDetail());
				real.setCollateralType(realestate.getCollateralType());
				real.setCurrentvalue(Double.parseDouble(realestate.getCurrentvalue()));
				real.setDepreciationrate(Double.parseDouble(realestate.getDetail2()));
				real.setRatepersqft(Double.parseDouble(realestate.getDetail1()));
				loan.setCollateralRealestate(real);

				loan.setLoanId(Integer.parseInt(realestate.getLoanId()));
				loan.setCollateralValue(Double.parseDouble(realestate.getCollateralValue()));
				loan.setInterest(Double.parseDouble(realestate.getInterest()));
				loan.setTenure(Double.parseDouble(realestate.getTenure()));

				String collaterals = loanClient.saveCollaterals(token, loan);
				// LOGGER.info(collaterals);
				if (collaterals.equalsIgnoreCase("Your Loan has been Approved")) {
					String str = "<div class=\"alert alert-success alert-dismissible text-center\">\r\n"
							+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
							+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
							+ "			Your Loan has been<strong> Approved</strong>\r\n" + "		</div>";
					mv.addObject("msg", str);
					// LOGGER.info(str);
				} else {
					String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
							+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
							+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
							+ "			<strong>" + collaterals + "</strong>\r\n" + "		</div>";
					mv.setViewName("PostRealEstate");
					mv.addObject("msg", str);
					LOGGER.error(collaterals);
				}
			} catch (Exception e) {
				String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
						+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
						+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
						+ "			Id <strong>Already</strong> Present!\r\n" + "		</div>";
				mv.setViewName("PostRealEstate");
				mv.addObject("msg", str);
				LOGGER.error(e.getLocalizedMessage());
			}
			 LOGGER.info("Ending Get Real Estate Post");
			return mv;
		} catch (Exception e) {
			ModelAndView modelandview = new ModelAndView("Login");
			String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
					+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
					+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
					+ "			Your Token has <strong>Expired!</strong>\r\n" + "		</div>";
			modelandview.addObject("msg", str);
			LOGGER.error(e.getLocalizedMessage());
			// LOGGER.info("Ending Get Real Estate Post");
			return modelandview;
		}
	}

//	Retrieving Deposits Details
	@GetMapping("/getdeposit")
	public ModelAndView getDepositPage(HttpServletRequest request, Model model) {

		 LOGGER.info("Starting Get Cash Deposit");
		model.addAttribute("deposit", new CollateralLoanJSP());
		// LOGGER.info("Ending Get Cash Deposit");
		return new ModelAndView("PostCashDeposit");
	}

//	Saving Deposits Details
	@PostMapping("/postdeposit")
	public ModelAndView postDepositPage(@ModelAttribute("deposit") @Valid CollateralLoanJSP deposit,
			BindingResult result, HttpServletRequest request) {
		LOGGER.info("Starting Get Cash Deposit Post");
		ModelAndView mv = new ModelAndView("PortalBranch");
		if (result.hasErrors()) {
			LOGGER.error(result.toString());
			// LOGGER.info("Ending Get Cash Deposit Post");
			mv.setViewName("PostCashDeposit");
			return mv;
		}
		String token = (String) request.getSession().getAttribute("token");
		try {
			authClient.verifyToken(token);
			try {
				DataCollateralLoan loan = new DataCollateralLoan();
				DataCollateralCashdeposit real = new DataCollateralCashdeposit();
				DataCustomer cust = new DataCustomer();

				cust.setName(deposit.getCustname());
				cust.setPhoneNo(Long.parseLong(deposit.getCustphoneNo()));
				cust.setAddress(deposit.getCustaddress());
				loan.setCustomer(cust);

				real.setBankname(deposit.getDetail());
				real.setCollateralType(deposit.getCollateralType());
				real.setCurrentvalue(Double.parseDouble(deposit.getCurrentvalue()));
				real.setLockperiod(Double.parseDouble(deposit.getDetail2()));
				real.setInterestrate(Double.parseDouble(deposit.getDetail1()));
				loan.setCollateralCashdeposit(real);

				loan.setLoanId(Integer.parseInt(deposit.getLoanId()));
				loan.setCollateralValue(Double.parseDouble(deposit.getCollateralValue()));
				loan.setInterest(Double.parseDouble(deposit.getInterest()));
				loan.setTenure(Double.parseDouble(deposit.getTenure()));

				String collaterals = loanClient.saveCollaterals(token, loan);
				// LOGGER.info(collaterals);
				if (collaterals.equalsIgnoreCase("Your Loan has been Approved")) {
					String str = "<div class=\"alert alert-success alert-dismissible text-center\">\r\n"
							+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
							+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
							+ "			Your Loan has been<strong> Approved</strong>\r\n" + "		</div>";
					mv.addObject("msg", str);
					// LOGGER.info(str);
				} else {
					String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
							+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
							+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
							+ "			<strong>" + collaterals + "</strong>\r\n" + "		</div>";
					mv.addObject("msg", str);
					mv.setViewName("PostCashDeposit");
					LOGGER.error(collaterals);
				}
			} catch (Exception e) {
				String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
						+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
						+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
						+ "			Id <strong>Already</strong> Present!\r\n" + "		</div>";
				mv.setViewName("PostCashDeposit");
				mv.addObject("msg", str);
				LOGGER.error(e.getLocalizedMessage());
			}
			// LOGGER.info("Ending Get Cash Deposit Post");
			return mv;
		} catch (Exception e) {
			ModelAndView modelandview = new ModelAndView("Login");
			String str = "<div class=\"alert alert-danger alert-dismissible text-center\">\r\n"
					+ "			<button class=\"close\" type=\"button\" data-dismiss=\"alert\">\r\n"
					+ "				<span>&times;</span>\r\n" + "			</button>\r\n"
					+ "			Your Token has <strong>Expired!</strong>\r\n" + "		</div>";
			modelandview.addObject("msg", str);
			LOGGER.error(e.getLocalizedMessage());
			return modelandview;
		}
	}

//	For Logout 
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		 LOGGER.info("Starting Log Out");
		session.setAttribute("token", null);
		session.setAttribute("name", null);
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView("Login");
		 LOGGER.info("Ending Log Out");
		return modelAndView;
	}
}
