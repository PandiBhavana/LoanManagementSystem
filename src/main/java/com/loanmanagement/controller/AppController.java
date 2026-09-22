package com.loanmanagement.controller;

import com.loanmanagement.dao.*;
import com.loanmanagement.dao.impl.*;
import com.loanmanagement.model.*;
import com.loanmanagement.service.CustomerService;
import com.loanmanagement.service.LoanTypeService;
import com.loanmanagement.service.UserService;
import com.loanmanagement.service.impl.CustomerServiceImpl;
import com.loanmanagement.service.impl.LoanTypeServiceImpl;
import com.loanmanagement.service.impl.UserServiceImpl;

public class AppController {
    public static void main(String[] args) {

   //    UserDao userDao = new UserDaoImpl();
//        User user3 = new User();
//        user3.setUsername("b");
//        user3.setPassword("ai");
//        user3.setRole("CUSTOMER");
//        user3.setStatus("ACTIVE");
//
//        userDao.addUser(user3);

//        System.out.println("User DAO tested");
//        User user2 = new User();
//
//        user2.setUsername("customer3");
//        user2.setPassword("pass123");
//        user2.setRole("CUSTOMER");
//        user2.setStatus("ACTIVE");
//
//        userDao.addUser(user2);

   // CustomerDao customerDao = new CustomerDaoImpl();
//        Customer customer1 = new Customer();
//        customer1.setUserId(2);
//        customer1.setFullName("sravniloka");
//        customer1.setEmail("sravs@gmail.com");
//        customer1.setPhone("7013864939");
//        customer1.setDob("2003-11-26");
//        customer1.setAddress("Hyderabad");
//        customer1.setMonthlyIncome(500000);
//        customer1.setPanNumber("HPRPB1234H");
//        customer1.setAadhaarLast4("6789");
//        customer1.setEmploymentType("SALARIED");
//        customer1.setAccountNumber("987654321");
//        customer1.setIfscCode("SBIN000234");
//        customer1.setBankName("SBI");
//        customer1.setKycStatus("PENDING");
//        customer1.setKycRemarks(null);
//        customer1.setKycVerifiedBy(2);
//        customer1.setKycVerifiedAt(null);
//        customer1.setCreditScore(890);
//        customer1.setExistingEmi(0);
//        customer1.setStatus("ACTIVE");
//
     //   customerDao.addCustomer(customer1);



  //  LoanTypeDao loanTypeDao = new LoanTypeDaoImpl();
//        LoanType loanType = new LoanType();
//        loanType.setName("Personal Loan");
//        loanType.setDescription("Loan for personal expenses");
//        loanType.setInterestRate(10.5);
//        loanType.setMinAmount(50000);
//        loanType.setMaxAmount(500000);
//        loanType.setMaxTenureMonths(60);
//        loanType.setStatus("ACTIVE");
//
//        loanTypeDao.addLoanType(loanType);

//        LoanType loanType = loanTypeDao.getLoanTypeById(2);
//
//        if (loanType != null) {
//            System.out.println("Loan Type ID: " + loanType.getLoanTypeId());
//            System.out.println("Name: " + loanType.getName());
//            System.out.println("Description: " + loanType.getDescription());
//            System.out.println("Interest Rate: " + loanType.getInterestRate());
//            System.out.println("Min Amount: " + loanType.getMinAmount());
//            System.out.println("Max Amount: " + loanType.getMaxAmount());
//            System.out.println("Max Tenure: " + loanType.getMaxTenureMonths());
//            System.out.println("Status: " + loanType.getStatus());
//        }
//        LoanApplicationDao dao = new LoanApplicationDaoImpl();
//
//        LoanApplication application = new LoanApplication();
//
//        application.setCustomerId(10);
//        application.setLoanTypeId(2);
//        application.setRequestedAmount(200000);
//        application.setTenureMonths(24);
//        application.setPurpose("Home renovation");
//        application.setStatus("PENDING");
//        application.setRemarks("Application submitted");
//
//        dao.addLoanApplication(application);

//        LoanDao dao = new LoanDaoImpl();
//
//        Loan loan = new Loan();
//
//        loan.setApplicationId(10);
//        loan.setCustomerId(10);
//        loan.setLoanTypeId(2);
//        loan.setPrincipalAmount(200000);
//        loan.setInterestRate(10.5);
//        loan.setTenureMonths(24);
//        loan.setTotalPayable(221000);
//        loan.setOutstandingAmount(221000);
//        loan.setStartDate("2026-09-20");
//        loan.setStatus("ACTIVE");
//        loan.setCreatedBy(2);
//
//        dao.addLoan(loan);
//        RepaymentDao dao = new RepaymentDaoImpl();
//
//        Repayment repayment = new Repayment();
//
//        repayment.setLoanId(8);
//        repayment.setAmount(10000);
//        repayment.setPaymentDate("2026-09-22");
//        repayment.setPaymentMode("UPI");
//        repayment.setReferenceNo("UPI123456");
//        repayment.setRemarks("Monthly repayment");
//        repayment.setRecordedBy(2);
//
//        dao.addRepayment(repayment);
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUsername("Kranthi sri");
        user.setPassword("12345");
        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");
        userService.addUser(user);

        User existingUser = userService.getUserById(1);
        existingUser.setPassword("shiny04");
        existingUser.setStatus("ACTIVE");
        userService.updateUser(existingUser);
        userService.deleteUser(1);


        LoanTypeService loanTypeService = new LoanTypeServiceImpl();
        LoanType loanType = new LoanType();
        loanType.setName("Personal Loan");
        loanType.setDescription("Personal loan for customers");
        loanType.setInterestRate(10.5);
        loanType.setMinAmount(50000);
        loanType.setMaxAmount(500000);
        loanType.setMaxTenureMonths(60);
        loanType.setStatus("ACTIVE");
        loanTypeService.addLoanType(loanType);
        LoanType existingLoanType = loanTypeService.getLoanTypeById(1);
        existingLoanType.setInterestRate(11.0);
        existingLoanType.setMaxAmount(600000);
        loanTypeService.updateLoanType(existingLoanType);
        loanTypeService.deleteLoanType(1);

        CustomerService customerService = new CustomerServiceImpl();
        Customer customer = new Customer();
        customer.setUserId(1);
        customer.setFullName("skranthisri");
        customer.setEmail("kranthi@gmail.com");
        customer.setPhone("9876543210");
        customer.setDob("2000-01-01");
        customer.setAddress("Hyderabad");
        customer.setMonthlyIncome(50000);
        customer.setPanNumber("ABCDE1234F");
        customer.setAadhaarLast4("1234");
        customer.setEmploymentType("SALARIED");
        customer.setAccountNumber("1234567890");
        customer.setIfscCode("SBIN0001234");
        customer.setBankName("SBI");
        customer.setKycStatus("PENDING");
        customer.setStatus("ACTIVE");
        customerService.addCustomer(customer);
        Customer existingCustomer = customerService.getCustomerById(1);
        existingCustomer.setAddress("Hyderabad");
        existingCustomer.setMonthlyIncome(60000);
        customerService.updateCustomer(existingCustomer);
        customerService.deleteCustomer(1);

    }
}



