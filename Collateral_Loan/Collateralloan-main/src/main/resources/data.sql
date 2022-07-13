
insert into collateral_loan  (loan_id,collateral_name,collateral_value,pledged_date) values (1,'COLLATERAL CASH DEPOSIT',100000.0,TO_DATE('17/01/2020', 'DD/MM/YYYY'));
insert into collateral_loan  (loan_id,collateral_name,collateral_value,pledged_date) values (2,'COLLATERAL CASH DEPOSIT',200000.0,TO_DATE('17/03/2020', 'DD/MM/YYYY'));
insert into collateral_loan  (loan_id,collateral_name,collateral_value,pledged_date) values (3,'COLLATERAL REAL ESTATE', 300000.0,TO_DATE('17/08/2020', 'DD/MM/YYYY'));
insert into collateral_loan  (loan_id,collateral_name,collateral_value,pledged_date) values (4,'COLLATERAL REAL ESTATE', 350000.0,TO_DATE('17/05/2019', 'DD/MM/YYYY'));

insert into collateral_cashdeposit (id,collateral_type,bank_name,current_value,interest_rate,lock_period,loan_id) 
values (1,'Fixed Deposit','HDFC',100000,6.0,10,1);
insert into collateral_cashdeposit (id,collateral_type,bank_name,current_value,interest_rate,lock_period,loan_id) 
values (2,'Fixed Deposit','SBI',200000,5.0,10,2);

insert into collateral_realestate  (id,collateral_type,address,current_value,rate_per_sq_ft,depreciation_rate,loan_id)
values (1,'a','Kolkata',400000.0,2000,3,3);
insert into collateral_realestate  (id,collateral_type,address,current_value,rate_per_sq_ft,depreciation_rate,loan_id)
values (2,'a','Chennai',500000,2500,5,4);
