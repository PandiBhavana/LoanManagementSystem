package com.loanmanagement.controller;

import com.loanmanagement.dao.CustomerDao;
import com.loanmanagement.dao.LoanApplicationDao;
import com.loanmanagement.dao.LoanTypeDao;
import com.loanmanagement.dao.UserDao;
import com.loanmanagement.dao.impl.CustomerDaoImpl;
import com.loanmanagement.dao.impl.LoanApplicationDaoImpl;
import com.loanmanagement.dao.impl.LoanTypeDaoImpl;
import com.loanmanagement.dao.impl.UserDaoImpl;
import com.loanmanagement.model.Customer;
import com.loanmanagement.model.LoanApplication;
import com.loanmanagement.model.LoanType;
import com.loanmanagement.model.User;

public class AppController {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        User user = new User();

        user.setUsername("controllerTest");
        user.setPassword("1234");
        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");

        userDao.addUser(user);

        System.out.println("User DAO tested");

        CustomerDao customerDao = new CustomerDaoImpl();
        Customer customer = new Customer();
        customer.setUserId(1);
        customer.setFullName("Test Customer");
        customer.setEmail("testcustomer@gmail.com");
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
        customer.setKycRemarks(null);
        customer.setKycVerifiedBy(0);
        customer.setKycVerifiedAt(null);
        customer.setCreditScore(750);
        customer.setExistingEmi(0);
        customer.setStatus("ACTIVE");

        customerDao.addCustomer(customer);



        LoanTypeDao loanTypeDao = new LoanTypeDaoImpl();
        LoanType loanType = new LoanType();
        loanType.setName("Personal Loan");
        loanType.setDescription("Loan for personal expenses");
        loanType.setInterestRate(10.5);
        loanType.setMinAmount(50000);
        loanType.setMaxAmount(500000);
        loanType.setMaxTenureMonths(60);
        loanType.setStatus("ACTIVE");

        loanTypeDao.addLoanType(loanType);

    }
}