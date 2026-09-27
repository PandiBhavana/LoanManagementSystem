package com.loanmanagement.controller;

import com.loanmanagement.model.Customer;
import com.loanmanagement.model.Loan;
import com.loanmanagement.model.LoanApplication;
import com.loanmanagement.model.LoanType;
import com.loanmanagement.model.User;
import com.loanmanagement.service.*;
import com.loanmanagement.service.impl.*;

import java.util.Scanner;

public class AppController {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserController userController = new UserController();
        CustomerController customerController = new CustomerController();

        LoanTypeController loanTypeController = new LoanTypeController();

        LoanApplicationController applicationController =
                new LoanApplicationController();

        LoanController loanController =
                new LoanController();
        AuthController authController=new AuthController();

        // =====================================================
        // 1. CREATE CUSTOMER USER
        // =====================================================

        System.out.println("\n========== CREATE  USER ==========");

        User customerUser = new User();

        System.out.print("Enter Username: ");
        customerUser.setUsername(sc.nextLine());

        System.out.print("Enter Password: ");
        customerUser.setPassword(sc.nextLine());

        customerUser.setRole("CUSTOMER");
        customerUser.setStatus("ACTIVE");

        userController.addUser(customerUser);

        System.out.println("\nCustomer User created successfully!");
        System.out.println("Generated User ID: "
                + customerUser.getUserId());


        // =====================================================
        // 2. CREATE CUSTOMER
        // =====================================================

        System.out.println("\n========== CREATE CUSTOMER ==========");

        Customer customer = new Customer();

        // User ID is automatically taken
        customer.setUserId(customerUser.getUserId());

        System.out.println("User ID: "
                + customer.getUserId());

        System.out.print("Enter Full Name: ");
        customer.setFullName(sc.nextLine());

        System.out.print("Enter Email: ");
        customer.setEmail(sc.nextLine());

        System.out.print("Enter Phone: ");
        customer.setPhone(sc.nextLine());

        System.out.print("Enter DOB: ");
        customer.setDob(sc.nextLine());

        System.out.print("Enter Address: ");
        customer.setAddress(sc.nextLine());

        System.out.print("Enter Monthly Income: ");
        customer.setMonthlyIncome(sc.nextDouble());

        sc.nextLine();

        System.out.print("Enter PAN Number: ");
        customer.setPanNumber(sc.nextLine());

        System.out.print("Enter Aadhaar Last 4 Digits: ");
        customer.setAadhaarLast4(sc.nextLine());

        System.out.print("Enter Employment Type: ");
        customer.setEmploymentType(sc.nextLine());

        System.out.print("Enter Account Number: ");
        customer.setAccountNumber(sc.nextLine());

        System.out.print("Enter IFSC Code: ");
        customer.setIfscCode(sc.nextLine());

        System.out.print("Enter Bank Name: ");
        customer.setBankName(sc.nextLine());

        // Initial values
        customer.setKycStatus("PENDING");
        customer.setCreditScore(0);
        customer.setExistingEmi(0);
        customer.setStatus("ACTIVE");

        customerController.addCustomer(customer);

        System.out.println("\nCustomer created successfully!");
        System.out.println("Generated Customer ID: "
                + customer.getCustomerId());


            // CUSTOMER AUTHENTICATION


        System.out.println("\n==========================================");
        System.out.println("       CUSTOMER AUTHENTICATION");
        System.out.println("==========================================");

        System.out.print("enter  Username: ");
        String loginUsername = sc.nextLine();

        System.out.print("Enter  Password: ");
        String loginPassword = sc.nextLine();

        boolean authenticated =
                authController.login(
                        loginUsername,
                        loginPassword);

        if (authenticated) {

            System.out.println(
                    "customer authentication successful!");

            System.out.println(
                    "customer can now access loan services.");

        } else {

            System.out.println(
                    "Customer authentication failed.");

            return;
        }

        // =====================================================
        // 3. SELECT EXISTING LOAN TYPE
        // =====================================================

        System.out.println("\n========== LOAN TYPE ==========");

        System.out.print("Enter Loan Type ID: ");
        int loanTypeId = sc.nextInt();

        sc.nextLine();

        LoanType loanType =
                loanTypeController.getLoanTypeById(loanTypeId);

        if (loanType == null) {
            System.out.println("Loan Type not found.");
            sc.close();
            return;
        }

        System.out.println("\nLoan Type Details");
        System.out.println("Name: "
                + loanType.getName());

        System.out.println("Interest Rate: "
                + loanType.getInterestRate());

        System.out.println("Minimum Amount: "
                + loanType.getMinAmount());

        System.out.println("Maximum Amount: "
                + loanType.getMaxAmount());

        System.out.println("Maximum Tenure: "
                + loanType.getMaxTenureMonths());

        System.out.println("Status: "
                + loanType.getStatus());


        // =====================================================
        // 4. CREATE LOAN APPLICATION
        // =====================================================

        System.out.println(
                "\n========== CREATE LOAN APPLICATION ==========");

        LoanApplication application =
                new LoanApplication();

        // IDs are automatically taken
        application.setCustomerId(
                customer.getCustomerId());

        application.setLoanTypeId(
                loanType.getLoanTypeId());

        System.out.println("Customer ID: "
                + application.getCustomerId());

        System.out.println("Loan Type ID: "
                + application.getLoanTypeId());

        System.out.print("Enter Requested Amount: ");
        application.setRequestedAmount(
                sc.nextDouble());

        System.out.print("Enter Tenure (months): ");
        application.setTenureMonths(
                sc.nextInt());

        sc.nextLine();

        System.out.print("Enter Purpose: ");
        application.setPurpose(
                sc.nextLine());

        applicationController.addApplication(
                application);

        System.out.println(
                "\nLoan Application created successfully!");

        System.out.println("Generated Application ID: "
                + application.getApplicationId());


        // =====================================================
        // 5. VERIFY APPLICATION STATUS
        // =====================================================

        System.out.println(
                "\n========== APPLICATION STATUS ==========");

        LoanApplication savedApplication =
                applicationController.getApplicationById(
                        application.getApplicationId());

        System.out.println("Application ID: "
                + savedApplication.getApplicationId());

        System.out.println("Application Status: "
                + savedApplication.getStatus());


        // =====================================================
        // 6. FETCH EXISTING LOAN OFFICER
        // =====================================================

        System.out.println(
                "\n========== LOAN OFFICER ==========");

        System.out.print("Enter Loan Officer ID: ");
        int officerId = sc.nextInt();

        sc.nextLine();

        User officer =
                userController.getUserById(officerId);

        if (officer == null) {
            System.out.println("Loan Officer not found.");
            sc.close();
            return;
        }

        System.out.println("\nLoan Officer Details");

        System.out.println("User ID: "
                + officer.getUserId());

        System.out.println("Username: "
                + officer.getUsername());

        System.out.println("Role: "
                + officer.getRole());

        System.out.println("Status: "
                + officer.getStatus());


        // =====================================================
        // 7. APPROVE APPLICATION
        // =====================================================

        System.out.println(
                "\n========== APPROVE APPLICATION ==========");

        System.out.print("Enter Approval Remarks: ");
        String remarks = sc.nextLine();

        applicationController.approveApplication(
                application.getApplicationId(),
                officer.getUserId(),
                remarks
        );

        System.out.println(
                "\nApplication approved successfully!");


        // =====================================================
        // 8. CREATE LOAN
        // =====================================================

        System.out.println(
                "\n========== CREATE LOAN ==========");

        Loan loan = new Loan();

        // Only Application ID and Officer ID are required.
        // Other loan details are fetched by LoanService.
        loan.setApplicationId(
                application.getApplicationId());

        loan.setCreatedBy(
                officer.getUserId());

        loanController.addLoan(loan);

        System.out.println(
                "\nLoan created successfully!");

        System.out.println("Generated Loan ID: "
                + loan.getLoanId());

        System.out.println("Customer ID: "
                + loan.getCustomerId());

        System.out.println("Loan Type ID: "
                + loan.getLoanTypeId());

        System.out.println("Principal Amount: "
                + loan.getPrincipalAmount());

        System.out.println("Interest Rate: "
                + loan.getInterestRate());

        System.out.println("Tenure: "
                + loan.getTenureMonths());

        System.out.println("Loan Status: "
                + loan.getStatus());


        // =====================================================
        // WORKFLOW COMPLETED
        // =====================================================

        System.out.println(
                "\n==========================================");

        System.out.println(
                "   LOAN CREATED SUCCESSFULLY");

        System.out.println(
                "==========================================");

        sc.close();
    }
}













