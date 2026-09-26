package com.loanmanagement.controller;

import com.loanmanagement.model.*;
import com.loanmanagement.service.*;
import com.loanmanagement.service.impl.*;

import java.util.Scanner;


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
            public void approveApplication(int applicationId,
                                           int loanOfficerId,
                                           String remarks) {
                loanApplicationService.approveApplication(
                        applicationId, loanOfficerId, remarks);
            }

            public void rejectApplication(int applicationId,
                                          int loanOfficerId,
                                          String remarks) {
                loanApplicationService.rejectApplication(
                        applicationId, loanOfficerId, remarks);
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

                    Scanner scanner = new Scanner(System.in);


                    //  LOGIN //
                    System.out.println("===== LOAN MANAGEMENT SYSTEM =====");

                    System.out.print("Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    boolean login = controller.login(username, password);

                    if (!login) {
                        System.out.println("Login failed.");
                        scanner.close();
                        return;
                    }

                    System.out.println("Login successful!");

                    //  LOAN APPLICATION //

                    System.out.println("\n===== APPLY FOR LOAN =====");

                    System.out.print("Customer ID: ");
                    int customerId = scanner.nextInt();

                    System.out.print("Loan Type ID: ");
                    int loanTypeId = scanner.nextInt();

                    System.out.print("Requested Amount: ");
                    double amount = scanner.nextDouble();

                    System.out.print("Tenure (months): ");
                    int tenure = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Purpose: ");
                    String purpose = scanner.nextLine();

                    LoanApplication application = new LoanApplication();

                    application.setCustomerId(customerId);
                    application.setLoanTypeId(loanTypeId);
                    application.setRequestedAmount(amount);
                    application.setTenureMonths(tenure);
                    application.setPurpose(purpose);

                    controller.addApplication(application);

                    System.out.println("Loan application created.");
                    System.out.println("Application Status: PENDING");
                    System.out.println("Application ID: "
                            + application.getApplicationId());

                    // APPROVAL

                    System.out.println("\n===== LOAN APPROVAL =====");

                    System.out.print("Loan Officer ID: ");
                    int loanOfficerId = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Approval Remarks: ");
                    String remarks = scanner.nextLine();

                    controller.approveApplication(
                            application.getApplicationId(),
                            loanOfficerId,
                            remarks
                    );

                    System.out.println("Loan application approved.");

                    //  LOAN CREATION

                    System.out.println("\n===== CREATE LOAN =====");

                    Loan loan = new Loan();

                    loan.setApplicationId(application.getApplicationId());
                    loan.setCreatedBy(loanOfficerId);

                    controller.addLoan(loan);

                    System.out.println("Loan created successfully!");
                    System.out.println("Loan ID: " + loan.getLoanId());
                    System.out.println("Principal Amount: "
                            + loan.getPrincipalAmount());
                    System.out.println("Loan Status: "
                            + loan.getStatus());

                    scanner.close();
                }
            }





