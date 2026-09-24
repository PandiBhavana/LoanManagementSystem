package com.loanmanagement.controller;

import com.loanmanagement.dao.*;
import com.loanmanagement.dao.impl.*;
import com.loanmanagement.model.*;
import com.loanmanagement.service.*;
import com.loanmanagement.service.impl.*;

public class AppController {
    public static void main(String[] args) {
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
       // userService.deleteUser(1);


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
      //  loanTypeService.deleteLoanType(1);

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
       // customerService.deleteCustomer(1);

        LoanApplicationService loanApplicationService = new LoanApplicationServiceImpl();
        LoanApplication application = new LoanApplication();
        application.setCustomerId(1);
        application.setLoanTypeId(1);
        application.setRequestedAmount(200000);
        application.setTenureMonths(24);
        application.setPurpose("Home renovation");
        application.setStatus("PENDING");
        application.setRemarks("Application submitted");
        loanApplicationService.addApplication(application);
        LoanApplication existingApplication =
                loanApplicationService.getApplicationById(1);
        existingApplication.setRequestedAmount(250000);
        existingApplication.setRemarks("Amount updated");
        loanApplicationService.updateApplication(existingApplication);
      //  loanApplicationService.deleteApplication(1);


        LoanService loanService = new LoanServiceImpl();
        Loan loan = new Loan();
        loan.setApplicationId(1);
        loan.setCustomerId(1);
        loan.setLoanTypeId(1);
        loan.setPrincipalAmount(200000);
        loan.setInterestRate(10.5);
        loan.setTenureMonths(24);
        loan.setTotalPayable(242000);
        loan.setOutstandingAmount(242000);
        loan.setStartDate("2026-09-22");
        loan.setStatus("ACTIVE");
        loan.setCreatedBy(1);
        loanService.addLoan(loan);
        Loan existingLoan = loanService.getLoanById(1);
        existingLoan.setInterestRate(11.0);
        existingLoan.setOutstandingAmount(240000);
        loanService.updateLoan(existingLoan);
       // loanService.deleteLoan(1);



    }
}



