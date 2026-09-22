package com.loanmanagement.dao.impl;

import com.loanmanagement.dao.LoanTypeDao;
import com.loanmanagement.model.LoanType;
import com.loanmanagement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoanTypeDaoImpl implements LoanTypeDao {
    @Override
    public void addLoanType(LoanType loanType) {
        String sql = "INSERT INTO loan_types " +
                "(name, description, interest_rate, min_amount, max_amount, max_tenure_months, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, loanType.getName());
            ps.setString(2, loanType.getDescription());
            ps.setDouble(3, loanType.getInterestRate());
            ps.setDouble(4, loanType.getMinAmount());
            ps.setDouble(5, loanType.getMaxAmount());
            ps.setInt(6, loanType.getMaxTenureMonths());
            ps.setString(7, loanType.getStatus());

            ps.executeUpdate();

            System.out.println("Loan type added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LoanType getLoanTypeById(int loanTypeId) {
        String sql = "SELECT * FROM loan_types WHERE loan_type_id = ?";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, loanTypeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                LoanType loanType = new LoanType();

                loanType.setLoanTypeId(rs.getInt("loan_type_id"));
                loanType.setName(rs.getString("name"));
                loanType.setDescription(rs.getString("description"));
                loanType.setInterestRate(rs.getDouble("interest_rate"));
                loanType.setMinAmount(rs.getDouble("min_amount"));
                loanType.setMaxAmount(rs.getDouble("max_amount"));
                loanType.setMaxTenureMonths(rs.getInt("max_tenure_months"));
                loanType.setStatus(rs.getString("status"));

                return loanType;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateLoanType(LoanType loanType) {
        String sql = "UPDATE loan_types SET " +
                "name=?, description=?, interest_rate=?, min_amount=?, " +
                "max_amount=?, max_tenure_months=?, status=? " +
                "WHERE loan_type_id=?";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, loanType.getName());
            ps.setString(2, loanType.getDescription());
            ps.setDouble(3, loanType.getInterestRate());
            ps.setDouble(4, loanType.getMinAmount());
            ps.setDouble(5, loanType.getMaxAmount());
            ps.setInt(6, loanType.getMaxTenureMonths());
            ps.setString(7, loanType.getStatus());
            ps.setInt(8, loanType.getLoanTypeId());

            ps.executeUpdate();

            System.out.println("Loan type updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLoanType(int loanTypeId) {

        String sql = "DELETE FROM loan_types WHERE loan_type_id=?";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, loanTypeId);

            ps.executeUpdate();

            System.out.println("Loan type deleted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
