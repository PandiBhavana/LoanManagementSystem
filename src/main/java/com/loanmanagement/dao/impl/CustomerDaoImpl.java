package com.loanmanagement.dao.impl;

import com.loanmanagement.dao.CustomerDao;
import com.loanmanagement.model.Customer;
import com.loanmanagement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void addCustomer(Customer customer) {
        String statement = "INSERT INTO customers " +
                "(user_id, full_name, email, phone, dob, address, monthly_income, " +
                "pan_number, aadhaar_last4, employment_type, account_number, " +
                "ifsc_code, bank_name, kyc_status, kyc_remarks, kyc_verified_by, " +
                "kyc_verified_at, credit_score, existing_emi, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, customer.getUserId());
            ps.setString(2, customer.getFullName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getDob());
            ps.setString(6, customer.getAddress());
            ps.setDouble(7, customer.getMonthlyIncome());
            ps.setString(8, customer.getPanNumber());
            ps.setString(9, customer.getAadhaarLast4());
            ps.setString(10, customer.getEmploymentType());
            ps.setString(11, customer.getAccountNumber());
            ps.setString(12, customer.getIfscCode());
            ps.setString(13, customer.getBankName());
            ps.setString(14, customer.getKycStatus());
            ps.setString(15, customer.getKycRemarks());
            ps.setInt(16, customer.getKycVerifiedBy());
            ps.setString(17, customer.getKycVerifiedAt());
            ps.setInt(18, customer.getCreditScore());
            ps.setDouble(19, customer.getExistingEmi());
            ps.setString(20, customer.getStatus());
            if (customer.getKycVerifiedBy() == null) {
                ps.setNull(16, java.sql.Types.INTEGER);
            } else {
                ps.setInt(16, customer.getKycVerifiedBy());
            }

            ps.executeUpdate();
            System.out.println("Customer added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String statement = "SELECT * FROM customers WHERE customer_id = ?";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Customer customer = new Customer();

                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setUserId(rs.getInt("user_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setDob(rs.getString("dob"));
                customer.setAddress(rs.getString("address"));
                customer.setMonthlyIncome(rs.getDouble("monthly_income"));
                customer.setPanNumber(rs.getString("pan_number"));
                customer.setAadhaarLast4(rs.getString("aadhaar_last4"));
                customer.setEmploymentType(rs.getString("employment_type"));
                customer.setAccountNumber(rs.getString("account_number"));
                customer.setIfscCode(rs.getString("ifsc_code"));
                customer.setBankName(rs.getString("bank_name"));
                customer.setKycStatus(rs.getString("kyc_status"));
                customer.setKycRemarks(rs.getString("kyc_remarks"));
                customer.setKycVerifiedBy(rs.getInt("kyc_verified_by"));
                customer.setKycVerifiedAt(rs.getString("kyc_verified_at"));
                customer.setCreditScore(rs.getInt("credit_score"));
                customer.setExistingEmi(rs.getDouble("existing_emi"));
                customer.setStatus(rs.getString("status"));

                return customer;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {
        String statement = "UPDATE customers SET " +
                "user_id=?, full_name=?, email=?, phone=?, dob=?, address=?, " +
                "monthly_income=?, pan_number=?, aadhaar_last4=?, employment_type=?, " +
                "account_number=?, ifsc_code=?, bank_name=?, kyc_status=?, " +
                "kyc_remarks=?, kyc_verified_by=?, kyc_verified_at=?, " +
                "credit_score=?, existing_emi=?, status=? " +
                "WHERE customer_id=?";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, customer.getUserId());
            ps.setString(2, customer.getFullName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getDob());
            ps.setString(6, customer.getAddress());
            ps.setDouble(7, customer.getMonthlyIncome());
            ps.setString(8, customer.getPanNumber());
            ps.setString(9, customer.getAadhaarLast4());
            ps.setString(10, customer.getEmploymentType());
            ps.setString(11, customer.getAccountNumber());
            ps.setString(12, customer.getIfscCode());
            ps.setString(13, customer.getBankName());
            ps.setString(14, customer.getKycStatus());
            ps.setString(15, customer.getKycRemarks());
            ps.setInt(16, customer.getKycVerifiedBy());
            ps.setString(17, customer.getKycVerifiedAt());
            ps.setInt(18, customer.getCreditScore());
            ps.setDouble(19, customer.getExistingEmi());
            ps.setString(20, customer.getStatus());
            ps.setInt(21, customer.getCustomerId());

            ps.executeUpdate();

            System.out.println("Customer updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Override
        public void deleteCustomer( int customerId){
            String statement = "DELETE FROM customers WHERE customer_id=?";

            try {
                Connection con = new DBConnection().getConnection();

                PreparedStatement ps = con.prepareStatement(statement);

                ps.setInt(1, customerId);

                ps.executeUpdate();

                System.out.println("Customer deleted successfully!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

