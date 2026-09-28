package com.loanmanagement.controller;

import com.loanmanagement.model.Customer;
import com.loanmanagement.model.Loan;
import com.loanmanagement.model.LoanApplication;
import com.loanmanagement.model.LoanType;
import com.loanmanagement.model.User;
import com.loanmanagement.service.*;
import com.loanmanagement.service.impl.*;

import java.util.List;
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

        AuthController authController =
                new AuthController();
        while (true) {

            System.out.println("\n================================");
            System.out.println("      LOAN MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. User Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Loan Type Management");
            System.out.println("4. Loan Application Management");
            System.out.println("5. Loan Management");
            System.out.println("6. Loan Workflow");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    // User CRUD
                    while (true) {

                        System.out.println("\n========== USER MANAGEMENT ==========");
                        System.out.println("1. Add User");
                        System.out.println("2. Get User By ID");
                        System.out.println("3. Get All Users");
                        System.out.println("4. Update User");
                        System.out.println("5. Delete User");
                        System.out.println("6. Back");
                        System.out.print("Enter your choice: ");

                        int userChoice = sc.nextInt();
                        sc.nextLine();

                        switch (userChoice) {

                            case 1:
                                // We will add Add User logic next
                                while (true) {
                                    try {
                                        User user = new User();

                                        System.out.println("\n========== ADD USER ==========");

                                        System.out.print("Enter Username: ");
                                        user.setUsername(sc.nextLine());

                                        System.out.print("Enter Password: ");
                                        user.setPassword(sc.nextLine());

                                        System.out.print("Enter Role (ADMIN/LOAN_OFFICER/CUSTOMER): ");
                                        user.setRole(sc.nextLine());

                                        System.out.print("Enter Status (ACTIVE/INACTIVE): ");
                                        user.setStatus(sc.nextLine());

                                        userController.addUser(user);

                                        System.out.println("\nUser added successfully!");
                                        System.out.println("Generated User ID: "
                                                + user.getUserId());

                                        break;

                                    } catch (Exception e) {

                                        System.out.println(
                                                "\nInvalid or duplicate value: "
                                                        + e.getMessage());

                                        System.out.println(
                                                "Please enter the user details again.\n");
                                    }
                                }
                                break;

                            case 2:
                                // Get User
                                while (true) {
                                    try {
                                        System.out.println("\n========== GET USER ==========");

                                        System.out.print("Enter User ID: ");
                                        int userId = sc.nextInt();
                                        sc.nextLine();

                                        User user = userController.getUserById(userId);

                                        if (user == null) {
                                            System.out.println(
                                                    "User not found. Please enter a valid User ID.");
                                            continue;
                                        }

                                        System.out.println("\nUser Details");
                                        System.out.println("----------------------------");
                                        System.out.println("User ID   : " + user.getUserId());
                                        System.out.println("Username  : " + user.getUsername());
                                        System.out.println("Role      : " + user.getRole());
                                        System.out.println("Status    : " + user.getStatus());
                                        System.out.println("Created At: " + user.getCreatedAt());

                                        break;

                                    } catch (Exception e) {

                                        System.out.println(
                                                "Invalid User ID. Please enter again.");
                                    }
                                }

                                break;

                            case 3:
                                // Get All Users
                                try {
                                    System.out.println("\n========== ALL USERS ==========");

                                    List<User> users = userController.getAllUsers();

                                    if (users == null || users.isEmpty()) {
                                        System.out.println("No users found.");
                                    } else {

                                        for (User user : users) {

                                            System.out.println("----------------------------");
                                            System.out.println("User ID   : " + user.getUserId());
                                            System.out.println("Username  : " + user.getUsername());
                                            System.out.println("Role      : " + user.getRole());
                                            System.out.println("Status    : " + user.getStatus());
                                            System.out.println("Created At: " + user.getCreatedAt());
                                        }

                                        System.out.println("----------------------------");
                                    }

                                } catch (Exception e) {

                                    System.out.println(
                                            "Unable to retrieve users: " + e.getMessage());
                                }

                                break;

                            case 4:
                                // Update User
                                while (true) {
                                    try {
                                        System.out.println("\n========== UPDATE USER ==========");

                                        System.out.print("Enter User ID: ");
                                        int userId = sc.nextInt();
                                        sc.nextLine();

                                        User user = userController.getUserById(userId);

                                        if (user == null) {
                                            System.out.println(
                                                    "User not found. Please enter a valid User ID.");
                                            continue;
                                        }

                                        System.out.println("\nCurrent User Details:");
                                        System.out.println("Username : " + user.getUsername());
                                        System.out.println("Role     : " + user.getRole());
                                        System.out.println("Status   : " + user.getStatus());

                                        System.out.print("Enter New Username: ");
                                        user.setUsername(sc.nextLine());

                                        System.out.print("Enter New Password: ");
                                        user.setPassword(sc.nextLine());

                                        System.out.print(
                                                "Enter New Role (ADMIN/LOAN_OFFICER/CUSTOMER): ");
                                        user.setRole(sc.nextLine());

                                        System.out.print(
                                                "Enter New Status (ACTIVE/INACTIVE): ");
                                        user.setStatus(sc.nextLine());

                                        userController.updateUser(user);

                                        System.out.println("\nUser updated successfully!");
                                        break;

                                    } catch (Exception e) {

                                        System.out.println(
                                                "\nInvalid or duplicate value: "
                                                        + e.getMessage());

                                        System.out.println(
                                                "Please enter the user details again.\n");
                                    }
                                }
                                break;

                            case 5:
                                // Delete User
                                while (true) {
                                    try {
                                        System.out.println("\n========== DELETE USER ==========");

                                        System.out.print("Enter User ID: ");
                                        int userId = sc.nextInt();
                                        sc.nextLine();

                                        User user = userController.getUserById(userId);

                                        if (user == null) {
                                            System.out.println(
                                                    "User not found. Please enter a valid User ID.");
                                            continue;
                                        }

                                        System.out.println("\nUser Details");
                                        System.out.println("User ID  : " + user.getUserId());
                                        System.out.println("Username : " + user.getUsername());
                                        System.out.println("Role     : " + user.getRole());
                                        System.out.println("Status   : " + user.getStatus());

                                        System.out.print(
                                                "Are you sure you want to delete this user? (YES/NO): ");

                                        String confirmation = sc.nextLine();

                                        if (!confirmation.equalsIgnoreCase("YES")) {
                                            System.out.println("Delete operation cancelled.");
                                            break;
                                        }

                                        userController.deleteUser(userId);

                                        System.out.println("\nUser deleted successfully!");
                                        break;

                                    } catch (Exception e) {

                                        System.out.println(
                                                "Unable to delete user: " + e.getMessage());

                                        System.out.println(
                                                "Please try again.\n");
                                    }
                                }
                                break;

                            case 6:
                                break;

                            default:
                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }

                        if (userChoice == 6) {
                            break;
                        }
                    }
                    break;

                case 2:
                    // Customer CRUD
                    while (true) {

                        System.out.println("\n========== CUSTOMER MANAGEMENT ==========");
                        System.out.println("1. Add Customer");
                        System.out.println("2. Get Customer By ID");
                        System.out.println("3. Get All Customers");
                        System.out.println("4. Update Customer");
                        System.out.println("5. Delete Customer");
                        System.out.println("6. Back");
                        System.out.print("Enter your choice: ");

                        int customerChoice;

                        try {
                            customerChoice = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println(
                                    "Invalid choice. Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        switch (customerChoice) {

                            case 1:
                                // Add Customer
                                while (true) {
                                    try {
                                        Customer customer = new Customer();

                                        System.out.println("\n========== ADD CUSTOMER ==========");

                                        System.out.print("Enter User ID: ");
                                        customer.setUserId(sc.nextInt());
                                        sc.nextLine();

                                        System.out.print("Enter Full Name: ");
                                        customer.setFullName(sc.nextLine());

                                        System.out.print("Enter Email: ");
                                        customer.setEmail(sc.nextLine());

                                        System.out.print("Enter Phone: ");
                                        customer.setPhone(sc.nextLine());

                                        System.out.print("Enter DOB (YYYY-MM-DD): ");
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

                                        customerController.addCustomer(customer);

                                        System.out.println("\nCustomer added successfully!");
                                        System.out.println("Generated Customer ID: "
                                                + customer.getCustomerId());

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nInvalid or duplicate value: "
                                                        + e.getMessage());
                                        System.out.println(
                                                "Please enter the customer details again.\n");
                                    }
                                }

                                break;

                            case 2:
                                // Get Customer By ID
                                while (true) {
                                    try {
                                        System.out.println("\n========== GET CUSTOMER ==========");

                                        System.out.print("Enter Customer ID: ");
                                        int customerId = sc.nextInt();
                                        sc.nextLine();

                                        Customer customer =
                                                customerController.getCustomerById(customerId);

                                        if (customer == null) {
                                            System.out.println(
                                                    "Customer not found. Please enter a valid Customer ID.");
                                            continue;
                                        }

                                        System.out.println("\nCustomer Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Customer ID       : " + customer.getCustomerId());
                                        System.out.println("User ID           : " + customer.getUserId());
                                        System.out.println("Full Name         : " + customer.getFullName());
                                        System.out.println("Email             : " + customer.getEmail());
                                        System.out.println("Phone             : " + customer.getPhone());
                                        System.out.println("DOB               : " + customer.getDob());
                                        System.out.println("Address           : " + customer.getAddress());
                                        System.out.println("Monthly Income    : " + customer.getMonthlyIncome());
                                        System.out.println("PAN Number        : " + customer.getPanNumber());
                                        System.out.println("Aadhaar Last 4    : " + customer.getAadhaarLast4());
                                        System.out.println("Employment Type   : " + customer.getEmploymentType());
                                        System.out.println("Account Number    : " + customer.getAccountNumber());
                                        System.out.println("IFSC Code         : " + customer.getIfscCode());
                                        System.out.println("Bank Name         : " + customer.getBankName());
                                        System.out.println("KYC Status        : " + customer.getKycStatus());
                                        System.out.println("KYC Remarks       : " + customer.getKycRemarks());
                                        System.out.println("Credit Score      : " + customer.getCreditScore());
                                        System.out.println("Existing EMI      : " + customer.getExistingEmi());
                                        System.out.println("Status            : " + customer.getStatus());

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "Invalid Customer ID. Please enter again.");
                                    }
                                }

                                break;

                            case 3:
                                // Get All Customers
                                try {
                                    System.out.println("\n========== ALL CUSTOMERS ==========");

                                    List<Customer> customers =
                                            customerController.getAllCustomers();

                                    if (customers == null || customers.isEmpty()) {
                                        System.out.println("No customers found.");
                                    } else {
                                        for (Customer customer : customers) {

                                            System.out.println("----------------------------");
                                            System.out.println("Customer ID    : " + customer.getCustomerId());
                                            System.out.println("User ID        : " + customer.getUserId());
                                            System.out.println("Full Name      : " + customer.getFullName());
                                            System.out.println("Email          : " + customer.getEmail());
                                            System.out.println("Phone          : " + customer.getPhone());
                                            System.out.println("Monthly Income : " + customer.getMonthlyIncome());
                                            System.out.println("KYC Status     : " + customer.getKycStatus());
                                            System.out.println("Credit Score   : " + customer.getCreditScore());
                                            System.out.println("Status         : " + customer.getStatus());
                                        }

                                        System.out.println("----------------------------");
                                    }

                                } catch (Exception e) {
                                    System.out.println(
                                            "Unable to retrieve customers: " + e.getMessage());
                                }
                                break;

                            case 4:
                                // Update Customer
                                while (true) {
                                    try {
                                        System.out.println("\n========== UPDATE CUSTOMER ==========");

                                        System.out.print("Enter Customer ID: ");
                                        int customerId = sc.nextInt();
                                        sc.nextLine();

                                        Customer customer =
                                                customerController.getCustomerById(customerId);

                                        if (customer == null) {
                                            System.out.println(
                                                    "Customer not found. Please enter a valid Customer ID.");
                                            continue;
                                        }

                                        System.out.println("\nCurrent Customer Details:");
                                        System.out.println("Full Name : " + customer.getFullName());
                                        System.out.println("Email     : " + customer.getEmail());
                                        System.out.println("Phone     : " + customer.getPhone());

                                        System.out.print("Enter New Full Name: ");
                                        customer.setFullName(sc.nextLine());

                                        System.out.print("Enter New Email: ");
                                        customer.setEmail(sc.nextLine());

                                        System.out.print("Enter New Phone: ");
                                        customer.setPhone(sc.nextLine());

                                        System.out.print("Enter New DOB (YYYY-MM-DD): ");
                                        customer.setDob(sc.nextLine());

                                        System.out.print("Enter New Address: ");
                                        customer.setAddress(sc.nextLine());

                                        System.out.print("Enter New Monthly Income: ");
                                        customer.setMonthlyIncome(sc.nextDouble());
                                        sc.nextLine();

                                        System.out.print("Enter New PAN Number: ");
                                        customer.setPanNumber(sc.nextLine());

                                        System.out.print("Enter New Aadhaar Last 4 Digits: ");
                                        customer.setAadhaarLast4(sc.nextLine());

                                        System.out.print("Enter New Employment Type: ");
                                        customer.setEmploymentType(sc.nextLine());

                                        System.out.print("Enter New Account Number: ");
                                        customer.setAccountNumber(sc.nextLine());

                                        System.out.print("Enter New IFSC Code: ");
                                        customer.setIfscCode(sc.nextLine());

                                        System.out.print("Enter New Bank Name: ");
                                        customer.setBankName(sc.nextLine());

                                        customerController.updateCustomer(customer);

                                        System.out.println("\nCustomer updated successfully!");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nInvalid or duplicate value: "
                                                        + e.getMessage());
                                        System.out.println(
                                                "Please enter the customer details again.\n");
                                    }
                                }

                                break;

                            case 5:
                                // Delete Customer
                                while (true) {
                                    try {
                                        System.out.println("\n========== DELETE CUSTOMER ==========");

                                        System.out.print("Enter Customer ID: ");
                                        int customerId = sc.nextInt();
                                        sc.nextLine();

                                        Customer customer =
                                                customerController.getCustomerById(customerId);

                                        if (customer == null) {
                                            System.out.println(
                                                    "Customer not found. Please enter a valid Customer ID.");
                                            continue;
                                        }

                                        System.out.println("\nCustomer Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Customer ID : " + customer.getCustomerId());
                                        System.out.println("User ID     : " + customer.getUserId());
                                        System.out.println("Full Name   : " + customer.getFullName());
                                        System.out.println("Email       : " + customer.getEmail());
                                        System.out.println("Phone       : " + customer.getPhone());
                                        System.out.println("Status      : " + customer.getStatus());

                                        System.out.print(
                                                "Are you sure you want to delete this customer? (YES/NO): ");

                                        String confirmation = sc.nextLine();

                                        if (!confirmation.equalsIgnoreCase("YES")) {
                                            System.out.println("Delete operation cancelled.");
                                            break;
                                        }

                                        customerController.deleteCustomer(customerId);

                                        System.out.println("\nCustomer deleted successfully!");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "Unable to delete customer: " + e.getMessage());
                                        System.out.println("Please try again.\n");
                                    }
                                }
                                break;

                            case 6:
                                break;

                            default:
                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }

                        if (customerChoice == 6) {
                            break;
                        }
                    }
                    break;

                case 3:
                    // Loan Type CRUD
                    while (true) {

                        System.out.println("\n========== LOAN TYPE MANAGEMENT ==========");
                        System.out.println("1. Add Loan Type");
                        System.out.println("2. Get Loan Type By ID");
                        System.out.println("3. Get All Loan Types");
                        System.out.println("4. Update Loan Type");
                        System.out.println("5. Delete Loan Type");
                        System.out.println("6. Back");
                        System.out.print("Enter your choice: ");

                        int loanTypeChoice;

                        try {
                            loanTypeChoice = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println(
                                    "Invalid choice. Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        switch (loanTypeChoice) {

                            case 1:
                                // Add Loan Type
                                while (true) {
                                    try {
                                        LoanType loanType = new LoanType();

                                        System.out.println("\n========== ADD LOAN TYPE ==========");

                                        System.out.print("Enter Loan Type Name: ");
                                        loanType.setName(sc.nextLine());

                                        System.out.print("Enter Description: ");
                                        loanType.setDescription(sc.nextLine());

                                        System.out.print("Enter Interest Rate: ");
                                        loanType.setInterestRate(sc.nextDouble());

                                        System.out.print("Enter Minimum Amount: ");
                                        loanType.setMinAmount(sc.nextDouble());

                                        System.out.print("Enter Maximum Amount: ");
                                        loanType.setMaxAmount(sc.nextDouble());

                                        System.out.print("Enter Maximum Tenure (months): ");
                                        loanType.setMaxTenureMonths(sc.nextInt());
                                        sc.nextLine();

                                        System.out.print("Enter Status (ACTIVE/INACTIVE): ");
                                        loanType.setStatus(sc.nextLine());

                                        loanTypeController.addLoanType(loanType);

                                        System.out.println("\nLoan Type added successfully!");
                                        System.out.println("Generated Loan Type ID: "
                                                + loanType.getLoanTypeId());

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nInvalid or duplicate value: "
                                                        + e.getMessage());
                                        System.out.println(
                                                "Please enter the loan type details again.\n");

                                        // Clear invalid scanner input if necessary
                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }
                                break;

                            case 2:
                                // Get Loan Type By ID
                                while (true) {
                                    try {
                                        System.out.println("\n========== GET LOAN TYPE ==========");

                                        System.out.print("Enter Loan Type ID: ");
                                        int loanTypeId = sc.nextInt();
                                        sc.nextLine();

                                        LoanType loanType =
                                                loanTypeController.getLoanTypeById(loanTypeId);

                                        if (loanType == null) {
                                            System.out.println(
                                                    "Loan Type not found. Please enter a valid Loan Type ID.");
                                            continue;
                                        }

                                        System.out.println("\nLoan Type Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Loan Type ID       : " + loanType.getLoanTypeId());
                                        System.out.println("Name               : " + loanType.getName());
                                        System.out.println("Description        : " + loanType.getDescription());
                                        System.out.println("Interest Rate      : " + loanType.getInterestRate());
                                        System.out.println("Minimum Amount     : " + loanType.getMinAmount());
                                        System.out.println("Maximum Amount     : " + loanType.getMaxAmount());
                                        System.out.println("Maximum Tenure     : "
                                                + loanType.getMaxTenureMonths() + " months");
                                        System.out.println("Status             : " + loanType.getStatus());

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "Invalid Loan Type ID. Please enter again.");
                                    }
                                }

                                break;

                            case 3:
                                // Get All Loan Types
                                try {
                                    System.out.println("\n========== ALL LOAN TYPES ==========");

                                    List<LoanType> loanTypes =
                                            loanTypeController.getAllLoanTypes();

                                    if (loanTypes == null || loanTypes.isEmpty()) {
                                        System.out.println("No loan types found.");
                                    } else {

                                        for (LoanType loanType : loanTypes) {

                                            System.out.println("----------------------------");
                                            System.out.println("Loan Type ID       : "
                                                    + loanType.getLoanTypeId());
                                            System.out.println("Name               : "
                                                    + loanType.getName());
                                            System.out.println("Description        : "
                                                    + loanType.getDescription());
                                            System.out.println("Interest Rate      : "
                                                    + loanType.getInterestRate());
                                            System.out.println("Minimum Amount     : "
                                                    + loanType.getMinAmount());
                                            System.out.println("Maximum Amount     : "
                                                    + loanType.getMaxAmount());
                                            System.out.println("Maximum Tenure     : "
                                                    + loanType.getMaxTenureMonths() + " months");
                                            System.out.println("Status             : "
                                                    + loanType.getStatus());
                                        }

                                        System.out.println("----------------------------");
                                    }

                                } catch (Exception e) {

                                    System.out.println(
                                            "Unable to retrieve loan types: "
                                                    + e.getMessage());
                                }


                                break;

                            case 4:
                                // Update Loan Type
                                while (true) {
                                    try {
                                        System.out.println("\n========== UPDATE LOAN TYPE ==========");

                                        System.out.print("Enter Loan Type ID: ");
                                        int loanTypeId = sc.nextInt();
                                        sc.nextLine();

                                        LoanType loanType =
                                                loanTypeController.getLoanTypeById(loanTypeId);

                                        if (loanType == null) {
                                            System.out.println(
                                                    "Loan Type not found. Please enter a valid Loan Type ID.");
                                            continue;
                                        }

                                        System.out.println("\nCurrent Loan Type Details:");
                                        System.out.println("Name           : " + loanType.getName());
                                        System.out.println("Description    : " + loanType.getDescription());
                                        System.out.println("Interest Rate  : " + loanType.getInterestRate());
                                        System.out.println("Minimum Amount : " + loanType.getMinAmount());
                                        System.out.println("Maximum Amount : " + loanType.getMaxAmount());
                                        System.out.println("Maximum Tenure : "
                                                + loanType.getMaxTenureMonths() + " months");
                                        System.out.println("Status         : " + loanType.getStatus());

                                        System.out.print("\nEnter New Name: ");
                                        loanType.setName(sc.nextLine());

                                        System.out.print("Enter New Description: ");
                                        loanType.setDescription(sc.nextLine());

                                        System.out.print("Enter New Interest Rate: ");
                                        loanType.setInterestRate(sc.nextDouble());

                                        System.out.print("Enter New Minimum Amount: ");
                                        loanType.setMinAmount(sc.nextDouble());

                                        System.out.print("Enter New Maximum Amount: ");
                                        loanType.setMaxAmount(sc.nextDouble());

                                        System.out.print("Enter New Maximum Tenure (months): ");
                                        loanType.setMaxTenureMonths(sc.nextInt());
                                        sc.nextLine();

                                        System.out.print("Enter New Status (ACTIVE/INACTIVE): ");
                                        loanType.setStatus(sc.nextLine());

                                        loanTypeController.updateLoanType(loanType);

                                        System.out.println("\nLoan Type updated successfully!");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nInvalid value: " + e.getMessage());
                                        System.out.println(
                                                "Please enter the loan type details again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }

                                break;

                            case 5:
                                // Delete Loan Type
                                while (true) {
                                    try {
                                        System.out.println("\n========== DELETE LOAN TYPE ==========");

                                        System.out.print("Enter Loan Type ID: ");
                                        int loanTypeId = sc.nextInt();
                                        sc.nextLine();

                                        LoanType loanType =
                                                loanTypeController.getLoanTypeById(loanTypeId);

                                        if (loanType == null) {
                                            System.out.println(
                                                    "Loan Type not found. Please enter a valid Loan Type ID.");
                                            continue;
                                        }

                                        System.out.println("\nLoan Type Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Loan Type ID : " + loanType.getLoanTypeId());
                                        System.out.println("Name         : " + loanType.getName());
                                        System.out.println("Status       : " + loanType.getStatus());

                                        System.out.print(
                                                "Are you sure you want to delete this loan type? (YES/NO): ");

                                        String confirmation = sc.nextLine();

                                        if (!confirmation.equalsIgnoreCase("YES")) {
                                            System.out.println("Delete operation cancelled.");
                                            break;
                                        }

                                        loanTypeController.deleteLoanType(loanTypeId);

                                        System.out.println(
                                                "\nLoan Type delete operation completed successfully.");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "Unable to delete loan type: " + e.getMessage());
                                        System.out.println("Please try again.\n");
                                    }
                                }

                                break;

                            case 6:
                                break;

                            default:
                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }

                        if (loanTypeChoice == 6) {
                            break;
                        }
                    }

                    break;

                case 4:
                    // Loan Application CRUD
                    while (true) {

                        System.out.println("\n========== LOAN APPLICATION MANAGEMENT ==========");
                        System.out.println("1. Add Loan Application");
                        System.out.println("2. Get Application By ID");
                        System.out.println("3. Get All Applications");
                        System.out.println("4. Update Application");
                        System.out.println("5. Approve Application");
                        System.out.println("6. Reject Application");
                        System.out.println("7. Delete Application");
                        System.out.println("8. Back");
                        System.out.print("Enter your choice: ");

                        int applicationChoice;

                        try {
                            applicationChoice = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println(
                                    "Invalid choice. Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        switch (applicationChoice) {

                            case 1:
                                // Add Loan Application
                                while (true) {
                                    try {
                                        LoanApplication application = new LoanApplication();

                                        System.out.println("\n========== ADD LOAN APPLICATION ==========");

                                        System.out.print("Enter Customer ID: ");
                                        application.setCustomerId(sc.nextInt());

                                        System.out.print("Enter Loan Type ID: ");
                                        application.setLoanTypeId(sc.nextInt());

                                        System.out.print("Enter Requested Amount: ");
                                        application.setRequestedAmount(sc.nextDouble());

                                        System.out.print("Enter Tenure (months): ");
                                        application.setTenureMonths(sc.nextInt());
                                        sc.nextLine();

                                        System.out.print("Enter Purpose: ");
                                        application.setPurpose(sc.nextLine());

                                        applicationController.addApplication(application);

                                        System.out.println("\nLoan Application added successfully!");
                                        System.out.println("Generated Application ID: "
                                                + application.getApplicationId());
                                        System.out.println("Application Status: "
                                                + application.getStatus());

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nInvalid application details: "
                                                        + e.getMessage());
                                        System.out.println(
                                                "Please enter the application details again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }

                                break;

                            case 2:
                                // Get Application By ID
                                while (true) {
                                    try {
                                        System.out.println("\n========== GET LOAN APPLICATION ==========");

                                        System.out.print("Enter Application ID: ");
                                        int applicationId = sc.nextInt();
                                        sc.nextLine();

                                        LoanApplication application =
                                                applicationController.getApplicationById(applicationId);

                                        if (application == null) {
                                            System.out.println(
                                                    "Application not found. Please enter a valid Application ID.");
                                            continue;
                                        }

                                        System.out.println("\nLoan Application Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Application ID   : " + application.getApplicationId());
                                        System.out.println("Customer ID      : " + application.getCustomerId());
                                        System.out.println("Loan Type ID     : " + application.getLoanTypeId());
                                        System.out.println("Requested Amount : " + application.getRequestedAmount());
                                        System.out.println("Tenure           : "
                                                + application.getTenureMonths() + " months");
                                        System.out.println("Purpose          : " + application.getPurpose());
                                        System.out.println("Status           : " + application.getStatus());
                                        System.out.println("Remarks          : " + application.getRemarks());
                                        System.out.println("Reviewed By      : " + application.getReviewedBy());
                                        System.out.println("Applied At       : " + application.getAppliedAt());
                                        System.out.println("Reviewed At      : " + application.getReviewedAt());

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "Invalid Application ID. Please enter again.");
                                    }
                                }

                                break;

                            case 3:
                                // Get All Applications
                                try {
                                    System.out.println("\n========== ALL LOAN APPLICATIONS ==========");

                                    List<LoanApplication> applications =
                                            applicationController.getAllApplications();

                                    if (applications == null || applications.isEmpty()) {

                                        System.out.println("No loan applications found.");

                                    } else {

                                        for (LoanApplication application : applications) {

                                            System.out.println("----------------------------");
                                            System.out.println("Application ID   : "
                                                    + application.getApplicationId());
                                            System.out.println("Customer ID      : "
                                                    + application.getCustomerId());
                                            System.out.println("Loan Type ID     : "
                                                    + application.getLoanTypeId());
                                            System.out.println("Requested Amount : "
                                                    + application.getRequestedAmount());
                                            System.out.println("Tenure           : "
                                                    + application.getTenureMonths() + " months");
                                            System.out.println("Purpose          : "
                                                    + application.getPurpose());
                                            System.out.println("Status           : "
                                                    + application.getStatus());
                                            System.out.println("Remarks          : "
                                                    + application.getRemarks());
                                            System.out.println("Reviewed By      : "
                                                    + application.getReviewedBy());
                                            System.out.println("Applied At       : "
                                                    + application.getAppliedAt());
                                            System.out.println("Reviewed At      : "
                                                    + application.getReviewedAt());
                                        }

                                        System.out.println("----------------------------");
                                    }

                                } catch (Exception e) {

                                    System.out.println(
                                            "Unable to retrieve applications: "
                                                    + e.getMessage());
                                }

                                break;

                            case 4:
                                // Update Application
                                while (true) {
                                    try {
                                        System.out.println("\n========== UPDATE LOAN APPLICATION ==========");

                                        System.out.print("Enter Application ID: ");
                                        int applicationId = sc.nextInt();
                                        sc.nextLine();

                                        LoanApplication application =
                                                applicationController.getApplicationById(applicationId);

                                        if (application == null) {
                                            System.out.println(
                                                    "Application not found. Please enter a valid Application ID.");
                                            continue;
                                        }

                                        // Only PENDING applications can be updated
                                        if (!"PENDING".equalsIgnoreCase(application.getStatus())) {
                                            System.out.println(
                                                    "Only PENDING applications can be updated.");
                                            break;
                                        }

                                        System.out.println("\nCurrent Application Details:");
                                        System.out.println("Customer ID      : "
                                                + application.getCustomerId());
                                        System.out.println("Loan Type ID     : "
                                                + application.getLoanTypeId());
                                        System.out.println("Requested Amount : "
                                                + application.getRequestedAmount());
                                        System.out.println("Tenure           : "
                                                + application.getTenureMonths());
                                        System.out.println("Purpose          : "
                                                + application.getPurpose());

                                        System.out.print("\nEnter New Requested Amount: ");
                                        application.setRequestedAmount(sc.nextDouble());

                                        System.out.print("Enter New Tenure (months): ");
                                        application.setTenureMonths(sc.nextInt());
                                        sc.nextLine();

                                        System.out.print("Enter New Purpose: ");
                                        application.setPurpose(sc.nextLine());

                                        applicationController.updateApplication(application);

                                        System.out.println(
                                                "\nLoan Application updated successfully!");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nInvalid application details: "
                                                        + e.getMessage());
                                        System.out.println(
                                                "Please enter the details again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }

                                break;

                            case 5:
                                // Approve Application
                                while (true) {
                                    try {
                                        System.out.println("\n========== APPROVE LOAN APPLICATION ==========");

                                        System.out.print("Enter Application ID: ");
                                        int applicationId = sc.nextInt();

                                        System.out.print("Enter Loan Officer ID: ");
                                        int loanOfficerId = sc.nextInt();
                                        sc.nextLine();

                                        LoanApplication application =
                                                applicationController.getApplicationById(applicationId);

                                        if (application == null) {
                                            System.out.println(
                                                    "Application not found. Please enter a valid Application ID.");
                                            continue;
                                        }

                                        if (!"PENDING".equalsIgnoreCase(application.getStatus())) {
                                            System.out.println(
                                                    "Only PENDING applications can be approved.");
                                            break;
                                        }

                                        System.out.println("\nApplication Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Application ID   : "
                                                + application.getApplicationId());
                                        System.out.println("Customer ID      : "
                                                + application.getCustomerId());
                                        System.out.println("Loan Type ID     : "
                                                + application.getLoanTypeId());
                                        System.out.println("Requested Amount : "
                                                + application.getRequestedAmount());
                                        System.out.println("Tenure           : "
                                                + application.getTenureMonths());
                                        System.out.println("Status           : "
                                                + application.getStatus());

                                        System.out.print("\nEnter Approval Remarks: ");
                                        String remarks = sc.nextLine();

                                        applicationController.approveApplication(
                                                applicationId,
                                                loanOfficerId,
                                                remarks);

                                        System.out.println(
                                                "\nLoan Application approved successfully!");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nUnable to approve application: "
                                                        + e.getMessage());
                                        System.out.println("Please try again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }

                                break;

                            case 6:
                                // Reject Application
                                while (true) {
                                    try {
                                        System.out.println("\n========== REJECT LOAN APPLICATION ==========");

                                        System.out.print("Enter Application ID: ");
                                        int applicationId = sc.nextInt();

                                        System.out.print("Enter Loan Officer ID: ");
                                        int loanOfficerId = sc.nextInt();
                                        sc.nextLine();

                                        LoanApplication application =
                                                applicationController.getApplicationById(applicationId);

                                        if (application == null) {
                                            System.out.println(
                                                    "Application not found. Please enter a valid Application ID.");
                                            continue;
                                        }

                                        if (!"PENDING".equalsIgnoreCase(application.getStatus())) {
                                            System.out.println(
                                                    "Only PENDING applications can be rejected.");
                                            break;
                                        }

                                        System.out.println("\nApplication Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Application ID   : "
                                                + application.getApplicationId());
                                        System.out.println("Customer ID      : "
                                                + application.getCustomerId());
                                        System.out.println("Loan Type ID     : "
                                                + application.getLoanTypeId());
                                        System.out.println("Requested Amount : "
                                                + application.getRequestedAmount());
                                        System.out.println("Tenure           : "
                                                + application.getTenureMonths());
                                        System.out.println("Status           : "
                                                + application.getStatus());

                                        System.out.print("\nEnter Rejection Remarks: ");
                                        String remarks = sc.nextLine();

                                        if (remarks.trim().isEmpty()) {
                                            System.out.println(
                                                    "Rejection remarks are required.");
                                            continue;
                                        }

                                        applicationController.rejectApplication(
                                                applicationId,
                                                loanOfficerId,
                                                remarks);

                                        System.out.println(
                                                "\nLoan Application rejected successfully!");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nUnable to reject application: "
                                                        + e.getMessage());
                                        System.out.println("Please try again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }
                                break;

                            case 7:
                                // Delete Application
                                while (true) {
                                    try {
                                        System.out.println("\n========== DELETE LOAN APPLICATION ==========");

                                        System.out.print("Enter Application ID: ");
                                        int applicationId = sc.nextInt();
                                        sc.nextLine();

                                        LoanApplication application =
                                                applicationController.getApplicationById(applicationId);

                                        if (application == null) {
                                            System.out.println(
                                                    "Application not found. Please enter a valid Application ID.");
                                            continue;
                                        }

                                        System.out.println("\nApplication Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Application ID   : "
                                                + application.getApplicationId());
                                        System.out.println("Customer ID      : "
                                                + application.getCustomerId());
                                        System.out.println("Loan Type ID     : "
                                                + application.getLoanTypeId());
                                        System.out.println("Requested Amount : "
                                                + application.getRequestedAmount());
                                        System.out.println("Status           : "
                                                + application.getStatus());

                                        System.out.print(
                                                "Are you sure you want to delete this application? (YES/NO): ");

                                        String confirmation = sc.nextLine();

                                        if (!confirmation.equalsIgnoreCase("YES")) {
                                            System.out.println("Delete operation cancelled.");
                                            break;
                                        }

                                        applicationController.deleteApplication(applicationId);

                                        System.out.println(
                                                "\nLoan Application deleted successfully!");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "Unable to delete application: "
                                                        + e.getMessage());
                                        System.out.println("Please try again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }

                                break;

                            case 8:
                                break;

                            default:
                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }

                        if (applicationChoice == 8) {
                            break;
                        }
                    }

                    break;

                case 5:
                    // Loan CRUD
                    while (true) {

                        System.out.println("\n========== LOAN MANAGEMENT ==========");
                        System.out.println("1. Create Loan");
                        System.out.println("2. Get Loan By ID");
                        System.out.println("3. Get All Loans");
                        System.out.println("4. Update Loan");
                        System.out.println("5. Delete Loan");
                        System.out.println("6. Back");
                        System.out.print("Enter your choice: ");

                        int loanChoice;

                        try {
                            loanChoice = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println(
                                    "Invalid choice. Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        switch (loanChoice) {

                            case 1:
                                // Create Loan
                                while (true) {
                                    try {
                                        System.out.println("\n========== CREATE LOAN ==========");

                                        System.out.print("Enter Approved Application ID: ");
                                        int applicationId = sc.nextInt();

                                        System.out.print("Enter Loan Officer ID: ");
                                        int loanOfficerId = sc.nextInt();
                                        sc.nextLine();

                                        Loan loan = new Loan();
                                        loan.setApplicationId(applicationId);
                                        loan.setCreatedBy(loanOfficerId);

                                        loanController.addLoan(loan);

                                        System.out.println("\nLoan created successfully!");
                                        System.out.println("Generated Loan ID: "
                                                + loan.getLoanId());

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nUnable to create loan: "
                                                        + e.getMessage());
                                        System.out.println(
                                                "Please enter the details again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }
                                break;

                            case 2:
                                // Get Loan By ID
                                while (true) {
                                    try {
                                        System.out.println("\n========== GET LOAN ==========");

                                        System.out.print("Enter Loan ID: ");
                                        int loanId = sc.nextInt();
                                        sc.nextLine();

                                        Loan loan = loanController.getLoanById(loanId);

                                        if (loan == null) {
                                            System.out.println(
                                                    "Loan not found. Please enter a valid Loan ID.");
                                            continue;
                                        }

                                        System.out.println("\nLoan Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Loan ID            : " + loan.getLoanId());
                                        System.out.println("Application ID     : " + loan.getApplicationId());
                                        System.out.println("Customer ID        : " + loan.getCustomerId());
                                        System.out.println("Loan Type ID       : " + loan.getLoanTypeId());
                                        System.out.println("Principal Amount   : " + loan.getPrincipalAmount());
                                        System.out.println("Interest Rate      : " + loan.getInterestRate());
                                        System.out.println("Tenure             : "
                                                + loan.getTenureMonths() + " months");
                                        System.out.println("Total Payable      : " + loan.getTotalPayable());
                                        System.out.println("Outstanding Amount : " + loan.getOutstandingAmount());
                                        System.out.println("Start Date         : " + loan.getStartDate());
                                        System.out.println("Status             : " + loan.getStatus());
                                        System.out.println("Created By         : " + loan.getCreatedBy());

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "Invalid Loan ID. Please enter again.");
                                    }
                                }
                                break;

                            case 3:
                                // Get All Loans
                                try {
                                    System.out.println("\n========== ALL LOANS ==========");

                                    List<Loan> loans = loanController.getAllLoans();

                                    if (loans == null || loans.isEmpty()) {

                                        System.out.println("No loans found.");

                                    } else {

                                        for (Loan loan : loans) {

                                            System.out.println("----------------------------");

                                            System.out.println("Loan ID            : "
                                                    + loan.getLoanId());

                                            System.out.println("Application ID     : "
                                                    + loan.getApplicationId());

                                            System.out.println("Customer ID        : "
                                                    + loan.getCustomerId());

                                            System.out.println("Loan Type ID       : "
                                                    + loan.getLoanTypeId());

                                            System.out.println("Principal Amount   : "
                                                    + loan.getPrincipalAmount());

                                            System.out.println("Interest Rate      : "
                                                    + loan.getInterestRate());

                                            System.out.println("Tenure             : "
                                                    + loan.getTenureMonths() + " months");

                                            System.out.println("Total Payable      : "
                                                    + loan.getTotalPayable());

                                            System.out.println("Outstanding Amount : "
                                                    + loan.getOutstandingAmount());

                                            System.out.println("Start Date         : "
                                                    + loan.getStartDate());

                                            System.out.println("Status             : "
                                                    + loan.getStatus());

                                            System.out.println("Created By         : "
                                                    + loan.getCreatedBy());
                                        }

                                        System.out.println("----------------------------");
                                    }

                                } catch (Exception e) {

                                    System.out.println(
                                            "Unable to retrieve loans: "
                                                    + e.getMessage());
                                }

                                break;

                            case 4:
                                // Update Loan
                                while (true) {
                                    try {
                                        System.out.println("\n========== UPDATE LOAN ==========");

                                        System.out.print("Enter Loan ID: ");
                                        int loanId = sc.nextInt();
                                        sc.nextLine();

                                        Loan loan = loanController.getLoanById(loanId);

                                        if (loan == null) {
                                            System.out.println(
                                                    "Loan not found. Please enter a valid Loan ID.");
                                            continue;
                                        }

                                        System.out.println("\nCurrent Loan Details:");
                                        System.out.println("Principal Amount   : "
                                                + loan.getPrincipalAmount());
                                        System.out.println("Interest Rate      : "
                                                + loan.getInterestRate());
                                        System.out.println("Tenure             : "
                                                + loan.getTenureMonths() + " months");
                                        System.out.println("Total Payable      : "
                                                + loan.getTotalPayable());
                                        System.out.println("Outstanding Amount : "
                                                + loan.getOutstandingAmount());
                                        System.out.println("Status             : "
                                                + loan.getStatus());

                                        System.out.print("\nEnter New Principal Amount: ");
                                        loan.setPrincipalAmount(sc.nextDouble());

                                        System.out.print("Enter New Interest Rate: ");
                                        loan.setInterestRate(sc.nextDouble());

                                        System.out.print("Enter New Tenure (months): ");
                                        loan.setTenureMonths(sc.nextInt());

                                        System.out.print("Enter New Total Payable: ");
                                        loan.setTotalPayable(sc.nextDouble());

                                        System.out.print("Enter New Outstanding Amount: ");
                                        loan.setOutstandingAmount(sc.nextDouble());
                                        sc.nextLine();

                                        System.out.print("Enter New Status (ACTIVE/CLOSED): ");
                                        loan.setStatus(sc.nextLine());

                                        loanController.updateLoan(loan);

                                        System.out.println("\nLoan updated successfully!");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "\nUnable to update loan: "
                                                        + e.getMessage());
                                        System.out.println(
                                                "Please enter the loan details again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }
                                break;

                            case 5:
                                // Delete Loan
                                while (true) {
                                    try {
                                        System.out.println("\n========== DELETE LOAN ==========");

                                        System.out.print("Enter Loan ID: ");
                                        int loanId = sc.nextInt();
                                        sc.nextLine();

                                        Loan loan = loanController.getLoanById(loanId);

                                        if (loan == null) {
                                            System.out.println(
                                                    "Loan not found. Please enter a valid Loan ID.");
                                            continue;
                                        }

                                        System.out.println("\nLoan Details");
                                        System.out.println("----------------------------");
                                        System.out.println("Loan ID            : " + loan.getLoanId());
                                        System.out.println("Application ID     : " + loan.getApplicationId());
                                        System.out.println("Customer ID        : " + loan.getCustomerId());
                                        System.out.println("Principal Amount   : " + loan.getPrincipalAmount());
                                        System.out.println("Outstanding Amount : "
                                                + loan.getOutstandingAmount());
                                        System.out.println("Status             : " + loan.getStatus());

                                        System.out.print(
                                                "\nAre you sure you want to delete this loan? (YES/NO): ");

                                        String confirmation = sc.nextLine();

                                        if (!confirmation.equalsIgnoreCase("YES")) {
                                            System.out.println("Delete operation cancelled.");
                                            break;
                                        }

                                        loanController.deleteLoan(loanId);

                                        System.out.println("\nLoan deleted successfully!");

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(
                                                "Unable to delete loan: " + e.getMessage());
                                        System.out.println("Please try again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }
                                break;

                            case 6:
                                break;

                            default:
                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }

                        if (loanChoice == 6) {
                            break;
                        }
                    }
                    break;

                case 6:
                    // ================= LOAN WORKFLOW =================
                    while (true) {

                        System.out.println("\n========== LOAN WORKFLOW ==========");
                        System.out.println("1. Start Loan Workflow");
                        System.out.println("2. Back");
                        System.out.print("Enter your choice: ");

                        int workflowChoice;

                        try {
                            workflowChoice = sc.nextInt();
                            sc.nextLine();

                        } catch (Exception e) {

                            System.out.println(
                                    "Invalid choice. Please enter a number.");

                            sc.nextLine();
                            continue;
                        }

                        switch (workflowChoice) {

                            // =================================================
                            // START LOAN WORKFLOW
                            // =================================================
                            case 1:

                                // ================= LOGIN =================

                                while (true) {

                                    try {

                                        System.out.println("\n========== LOGIN ==========");

                                        System.out.print("Enter Username: ");
                                        String username = sc.nextLine();

                                        System.out.print("Enter Password: ");
                                        String password = sc.nextLine();

                                        boolean loggedIn =
                                                authController.login(
                                                        username,
                                                        password);

                                        if (!loggedIn) {

                                            System.out.println(
                                                    "\nLogin failed. Invalid credentials or inactive user.");

                                            System.out.println(
                                                    "Please try again.\n");

                                            continue;
                                        }

                                        System.out.println(
                                                "\nLogin successful!");
                                        Customer customer =
                                                customerController.getCustomerByUsername(username);

                                        if (customer == null) {
                                            System.out.println("Customer profile not found.");
                                            continue;
                                        }

                                        int customerId = customer.getCustomerId();

                                        System.out.println("Customer ID: " + customerId);

                                        break;

                                    } catch (Exception e) {

                                        System.out.println(
                                                "\nLogin failed: "
                                                        + e.getMessage());

                                        System.out.println(
                                                "Please try again.\n");
                                    }
                                }


                                // ================= APPLY FOR LOAN =================

                                LoanApplication application = null;

                                while (true) {

                                    try {

                                        System.out.println(
                                                "\n========== APPLY FOR LOAN ==========");

                                       System.out.print(
                                                            "Enter Customer ID: ");
                                      int customerId = sc.nextInt();

                                        System.out.print(
                                                "Enter Loan Type ID: ");
                                        int loanTypeId = sc.nextInt();

                                        System.out.print(
                                                "Enter Requested Amount: ");
                                        double amount = sc.nextDouble();

                                        System.out.print(
                                                "Enter Tenure (months): ");
                                        int tenure = sc.nextInt();

                                        sc.nextLine();

                                        System.out.print(
                                                "Enter Purpose: ");
                                        String purpose = sc.nextLine();

                                        application =
                                                new LoanApplication();

                                        application.setCustomerId(
                                                customerId);

                                        application.setLoanTypeId(
                                                loanTypeId);

                                        application.setRequestedAmount(
                                                amount);

                                        application.setTenureMonths(
                                                tenure);

                                        application.setPurpose(
                                                purpose);

                                        applicationController
                                                .addApplication(application);

                                        System.out.println(
                                                "\nLoan application submitted successfully!");

                                        System.out.println(
                                                "Application ID: "
                                                        + application
                                                        .getApplicationId());

                                        System.out.println(
                                                "Status: "
                                                        + application.getStatus());

                                        break;

                                    } catch (Exception e) {

                                        System.out.println(
                                                "\nUnable to submit application: "
                                                        + e.getMessage());

                                        System.out.println(
                                                "Please enter the application details again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }


                                // ================= LOAN OFFICER REVIEW =================

                                while (true) {

                                    try {

                                        System.out.println(
                                                "\n========== LOAN OFFICER REVIEW ==========");

                                        System.out.print(
                                                "Application ID: ");
                                        int applicationId = sc.nextInt();

                                        System.out.print(
                                                "Enter Loan Officer ID: ");
                                        int loanOfficerId = sc.nextInt();

                                        sc.nextLine();

                                        System.out.println(
                                                "\n1. Approve Application");

                                        System.out.println(
                                                "2. Reject Application");

                                        System.out.print(
                                                "Enter choice: ");

                                        int decision = sc.nextInt();
                                        sc.nextLine();

                                        System.out.print(
                                                "Enter Remarks: ");
                                        String remarks = sc.nextLine();


                                        // ================= APPROVE =================

                                        if (decision == 1) {

                                            applicationController
                                                    .approveApplication(
                                                            applicationId,
                                                            loanOfficerId,
                                                            remarks);

                                            System.out.println(
                                                    "\nApplication approved successfully.");


                                            // ================= CREATE LOAN =================

                                            while (true) {

                                                try {

                                                    Loan loan =
                                                            new Loan();

                                                    loan.setApplicationId(
                                                            applicationId);

                                                    loan.setCreatedBy(
                                                            loanOfficerId);

                                                    loanController.addLoan(
                                                            loan);

                                                    System.out.println(
                                                            "Loan created successfully.");

                                                    System.out.println(
                                                            "Generated Loan ID: "
                                                                    + loan.getLoanId());

                                                    System.out.println(
                                                            "\n========== LOAN CREATION SUCCESSFUL ==========");



                                                    break;

                                                } catch (Exception e) {

                                                    System.out.println(
                                                            "\nLoan creation failed: "
                                                                    + e.getMessage());

                                                    System.out.println(
                                                            "Please try creating the loan again.");
                                                }
                                            }

                                            break;
                                        }


                                        // ================= REJECT =================

                                        else if (decision == 2) {

                                            if (remarks.trim().isEmpty()) {

                                                System.out.println(
                                                        "Rejection remarks are required.");

                                                continue;
                                            }

                                            applicationController
                                                    .rejectApplication(
                                                            applicationId,
                                                            loanOfficerId,
                                                            remarks);

                                            System.out.println(
                                                    "\nLoan Application rejected successfully.");

                                            break;
                                        }


                                        // ================= INVALID CHOICE =================

                                        else {

                                            System.out.println(
                                                    "Invalid choice. Please select 1 or 2.");
                                        }


                                    } catch (Exception e) {

                                        System.out.println(
                                                "\nUnable to process application: "
                                                        + e.getMessage());

                                        System.out.println(
                                                "Please enter the details again.\n");

                                        if (sc.hasNextLine()) {
                                            sc.nextLine();
                                        }
                                    }
                                }


                                System.out.println(
                                        "\n========== WORKFLOW COMPLETED ==========");

                                System.out.println(
                                        "Loan application processing completed.");

                                break;


                            // =================================================
                            // BACK
                            // =================================================
                            case 2:

                                break;


                            // =================================================
                            // INVALID WORKFLOW CHOICE
                            // =================================================
                            default:

                                System.out.println(
                                        "Invalid choice. Please try again.");
                        }


                        // Exit Loan Workflow menu
                        if (workflowChoice == 2) {
                            break;
                        }
                    }

                    break;


// =================================================
// EXIT
// =================================================
                case 7:

                    System.out.println(
                            "Thank you for using Loan Management System.");

                    sc.close();

                    return;


// =================================================
// INVALID MAIN MENU CHOICE
// =================================================
                default:

                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }

    }
}











