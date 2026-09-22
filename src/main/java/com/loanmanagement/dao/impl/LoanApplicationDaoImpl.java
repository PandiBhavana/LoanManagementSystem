package com.loanmanagement.dao.impl;

import com.loanmanagement.dao.LoanApplicationDao;
import com.loanmanagement.model.LoanApplication;
import com.loanmanagement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoanApplicationDaoImpl implements LoanApplicationDao {
    @Override
    public void addLoanApplication(LoanApplication application) {
        String sql = "INSERT INTO loan_applications " +
                "(customer_id, loan_type_id, requested_amount, tenure_months, purpose, status, remarks) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, application.getCustomerId());
            ps.setInt(2, application.getLoanTypeId());
            ps.setDouble(3, application.getRequestedAmount());
            ps.setInt(4, application.getTenureMonths());
            ps.setString(5, application.getPurpose());
            ps.setString(6, application.getStatus());
            ps.setString(7, application.getRemarks());

            ps.executeUpdate();

            System.out.println("Loan application added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LoanApplication getLoanApplicationById(int applicationId) {
        String sql = "SELECT * FROM loan_applications WHERE application_id = ?";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, applicationId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                LoanApplication application = new LoanApplication();

                application.setApplicationId(rs.getInt("application_id"));
                application.setCustomerId(rs.getInt("customer_id"));
                application.setLoanTypeId(rs.getInt("loan_type_id"));
                application.setRequestedAmount(rs.getDouble("requested_amount"));
                application.setTenureMonths(rs.getInt("tenure_months"));
                application.setPurpose(rs.getString("purpose"));
                application.setStatus(rs.getString("status"));
                application.setRemarks(rs.getString("remarks"));
                application.setReviewedBy(rs.getInt("reviewed_by"));
                application.setAppliedAt(rs.getString("applied_at"));
                application.setReviewedAt(rs.getString("reviewed_at"));

                return application;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateLoanApplication(LoanApplication application) {
        String sql = "UPDATE loan_applications SET " +
                "customer_id=?, loan_type_id=?, requested_amount=?, " +
                "tenure_months=?, purpose=?, status=?, remarks=?, " +
                "reviewed_by=?, reviewed_at=? " +
                "WHERE application_id=?";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, application.getCustomerId());
            ps.setInt(2, application.getLoanTypeId());
            ps.setDouble(3, application.getRequestedAmount());
            ps.setInt(4, application.getTenureMonths());
            ps.setString(5, application.getPurpose());
            ps.setString(6, application.getStatus());
            ps.setString(7, application.getRemarks());
            ps.setInt(8, application.getReviewedBy());
            ps.setString(9, application.getReviewedAt());
            ps.setInt(10, application.getApplicationId());

            ps.executeUpdate();

            System.out.println("Loan application updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteLoanApplication(int applicationId) {
        String sql = "DELETE FROM loan_applications WHERE application_id=?";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, applicationId);

            ps.executeUpdate();

            System.out.println("Loan application deleted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }

