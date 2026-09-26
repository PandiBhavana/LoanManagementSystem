package com.loanmanagement.controller;

import com.loanmanagement.dao.*;
import com.loanmanagement.dao.impl.*;
import com.loanmanagement.model.*;
import com.loanmanagement.service.*;
import com.loanmanagement.service.impl.*;


        public class AppController {

            private final UserService userService =
                    new UserServiceImpl();

            private final LoanTypeService loanTypeService =
                    new LoanTypeServiceImpl();

            private final CustomerService customerService =
                    new CustomerServiceImpl();

            private final LoanApplicationService loanApplicationService =
                    new LoanApplicationServiceImpl();

            private final LoanService loanService =
                    new LoanServiceImpl();
            private final AuthService authService =
                    new AuthServiceImpl();
            // Authentication

            public boolean login(String username, String password) {
                return authService.login(username, password);
            }

            public void logout(int userId) {
                authService.logout(userId);
            }

            // User Management

            public void addUser(User user) {
                userService.addUser(user);
            }

            public User getUserById(int userId) {
                return userService.getUserById(userId);
            }

            public void updateUser(User user) {
                userService.updateUser(user);
            }

            public void deleteUser(int userId) {
                userService.deleteUser(userId);
            }


            // Loan Type Management

            public void addLoanType(LoanType loanType) {
                loanTypeService.addLoanType(loanType);
            }

            public LoanType getLoanTypeById(int loanTypeId) {
                return loanTypeService.getLoanTypeById(loanTypeId);
            }

            public void updateLoanType(LoanType loanType) {
                loanTypeService.updateLoanType(loanType);
            }

            public void deleteLoanType(int loanTypeId) {
                loanTypeService.deleteLoanType(loanTypeId);
            }


            // Customer Management

            public void addCustomer(Customer customer) {
                customerService.addCustomer(customer);
            }

            public Customer getCustomerById(int customerId) {
                return customerService.getCustomerById(customerId);
            }

            public void updateCustomer(Customer customer) {
                customerService.updateCustomer(customer);
            }

            public void deleteCustomer(int customerId) {
                customerService.deleteCustomer(customerId);
            }


            // Loan Application Management

            public void addApplication(LoanApplication application) {
                loanApplicationService.addApplication(application);
            }

            public LoanApplication getApplicationById(int applicationId) {
                return loanApplicationService.getApplicationById(applicationId);
            }

            public void updateApplication(LoanApplication application) {
                loanApplicationService.updateApplication(application);
            }

            public void deleteApplication(int applicationId) {
                loanApplicationService.deleteApplication(applicationId);
            }


            // Loan Management

            public void addLoan(Loan loan) {
                loanService.addLoan(loan);
            }

            public Loan getLoanById(int loanId) {
                return loanService.getLoanById(loanId);
            }

            public void updateLoan(Loan loan) {
                loanService.updateLoan(loan);
            }

            public void deleteLoan(int loanId) {
                loanService.deleteLoan(loanId);
            }


            public static void main(String[] args) {

                AppController controller = new AppController();

                System.out.println("Loan Management System started successfully.");
            }
        }




