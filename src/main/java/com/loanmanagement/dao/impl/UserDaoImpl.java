package com.loanmanagement.dao.impl;

import com.loanmanagement.dao.UserDao;
import com.loanmanagement.model.User;
import com.loanmanagement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        String statement = "INSERT INTO users (username, password, role, status) VALUES (?, ?, ?, ?)";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getStatus());

            ps.executeUpdate();

            System.out.println("User added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUserById(int userId) {
        String statement = "SELECT * FROM users WHERE user_id = ?";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setCreatedAt(rs.getString("created_at"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        String statement = "UPDATE users SET username=?, password=?, role=?, status=? WHERE user_id=?";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getStatus());
            ps.setInt(5, user.getUserId());

            ps.executeUpdate();

            System.out.println("User updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(int userId) {
        String statement = "DELETE FROM users WHERE user_id=?";

        try {
            Connection con = new DBConnection().getConnection();

            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, userId);

            ps.executeUpdate();

            System.out.println("User deleted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }

