package com.loanmanagement.dao.impl;

import com.loanmanagement.dao.RepaymentDao;
import com.loanmanagement.model.Repayment;
import com.loanmanagement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepaymentDaoImpl implements RepaymentDao {
    @Override
    public void addRepayment(Repayment repayment) {
        String sql = "INSERT INTO repayments " +
                "(loan_id, amount, payment_date, payment_mode, " +
                "reference_no, remarks, recorded_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, repayment.getLoanId());
            ps.setDouble(2, repayment.getAmount());
            ps.setString(3, repayment.getPaymentDate());
            ps.setString(4, repayment.getPaymentMode());
            ps.setString(5, repayment.getReferenceNo());
            ps.setString(6, repayment.getRemarks());
            ps.setInt(7, repayment.getRecordedBy());

            ps.executeUpdate();

            System.out.println("Repayment added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Repayment getRepaymentById(int repaymentId) {
        String sql = "SELECT * FROM repayments WHERE repayment_id = ?";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, repaymentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Repayment repayment = new Repayment();

                repayment.setRepaymentId(rs.getInt("repayment_id"));
                repayment.setLoanId(rs.getInt("loan_id"));
                repayment.setAmount(rs.getDouble("amount"));
                repayment.setPaymentDate(rs.getString("payment_date"));
                repayment.setPaymentMode(rs.getString("payment_mode"));
                repayment.setReferenceNo(rs.getString("reference_no"));
                repayment.setRemarks(rs.getString("remarks"));
                repayment.setRecordedBy(rs.getInt("recorded_by"));
                repayment.setCreatedAt(rs.getString("created_at"));

                return repayment;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateRepayment(Repayment repayment) {
        String sql = "UPDATE repayments SET " +
                "loan_id=?, amount=?, payment_date=?, payment_mode=?, " +
                "reference_no=?, remarks=?, recorded_by=? " +
                "WHERE repayment_id=?";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, repayment.getLoanId());
            ps.setDouble(2, repayment.getAmount());
            ps.setString(3, repayment.getPaymentDate());
            ps.setString(4, repayment.getPaymentMode());
            ps.setString(5, repayment.getReferenceNo());
            ps.setString(6, repayment.getRemarks());
            ps.setInt(7, repayment.getRecordedBy());
            ps.setInt(8, repayment.getRepaymentId());

            ps.executeUpdate();

            System.out.println("Repayment updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteRepayment(int repaymentId) {
        String sql = "DELETE FROM repayments WHERE repayment_id=?";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, repaymentId);

            ps.executeUpdate();

            System.out.println("Repayment deleted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
