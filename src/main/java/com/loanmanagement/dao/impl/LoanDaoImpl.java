package com.loanmanagement.dao.impl;

import com.loanmanagement.dao.LoanDao;
import com.loanmanagement.model.Loan;
import com.loanmanagement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoanDaoImpl implements LoanDao {
    @Override
    public void addLoan(Loan loan) {
        String statement = "INSERT INTO loans " +
                "(application_id, customer_id, loan_type_id, principal_amount, " +
                "interest_rate, tenure_months, total_payable, outstanding_amount, " +
                "start_date, status, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, loan.getApplicationId());
            ps.setInt(2, loan.getCustomerId());
            ps.setInt(3, loan.getLoanTypeId());
            ps.setDouble(4, loan.getPrincipalAmount());
            ps.setDouble(5, loan.getInterestRate());
            ps.setInt(6, loan.getTenureMonths());
            ps.setDouble(7, loan.getTotalPayable());
            ps.setDouble(8, loan.getOutstandingAmount());
            ps.setString(9, loan.getStartDate());
            ps.setString(10, loan.getStatus());
            ps.setInt(11, loan.getCreatedBy());

            ps.executeUpdate();

            System.out.println("Loan added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Loan getLoanById(int loanId) {
        String statement = "SELECT * FROM loans WHERE loan_id = ?";

        try {
            // Move these two lines INSIDE the try block
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, loanId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Loan loan = new Loan();

                loan.setLoanId(rs.getInt("loan_id"));
                loan.setApplicationId(rs.getInt("application_id"));
                loan.setCustomerId(rs.getInt("customer_id"));
                loan.setLoanTypeId(rs.getInt("loan_type_id"));
                loan.setPrincipalAmount(rs.getDouble("principal_amount"));
                loan.setInterestRate(rs.getDouble("interest_rate"));
                loan.setTenureMonths(rs.getInt("tenure_months"));
                loan.setTotalPayable(rs.getDouble("total_payable"));
                loan.setOutstandingAmount(rs.getDouble("outstanding_amount"));
                loan.setStartDate(rs.getString("start_date"));
                loan.setStatus(rs.getString("status"));
                loan.setCreatedBy(rs.getInt("created_by"));

                return loan;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateLoan(Loan loan) {

        String statement = "UPDATE loans SET " +
                "application_id=?, customer_id=?, loan_type_id=?, " +
                "principal_amount=?, interest_rate=?, tenure_months=?, " +
                "total_payable=?, outstanding_amount=?, start_date=?, " +
                "status=?, created_by=? WHERE loan_id=?";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, loan.getApplicationId());
            ps.setInt(2, loan.getCustomerId());
            ps.setInt(3, loan.getLoanTypeId());
            ps.setDouble(4, loan.getPrincipalAmount());
            ps.setDouble(5, loan.getInterestRate());
            ps.setInt(6, loan.getTenureMonths());
            ps.setDouble(7, loan.getTotalPayable());
            ps.setDouble(8, loan.getOutstandingAmount());
            ps.setString(9, loan.getStartDate());
            ps.setString(10, loan.getStatus());
            ps.setInt(11, loan.getCreatedBy());
            ps.setInt(12, loan.getLoanId());

            ps.executeUpdate();

            System.out.println("Loan updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();


        }
    }

        @Override
        public void deleteLoan ( int loanId){
            String statement = "DELETE FROM loans WHERE loan_id=?";

            try {
                Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(statement);

                ps.setInt(1, loanId);

                ps.executeUpdate();

                System.out.println("Loan deleted successfully!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

