package com.loanmanagement;

import com.loanmanagement.model.User;
import com.loanmanagement.service.UserService;
import com.loanmanagement.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

public class UserServiceImplTest {
    private UserService userService = new UserServiceImpl();
    @Test
    public void testAddUser() {

        User user = new User();

        user.setUsername("service_test_user");
        user.setPassword("12345");
        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");

        userService.addUser(user);
    }
    @Test
    public void testGetUserById() {

        User user = userService.getUserById(1);

        System.out.println("User ID: " + user.getUserId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Role: " + user.getRole());
    }
    @Test
    public void testUpdateUser() {

        User user = userService.getUserById(1);

        user.setPassword("updated123");
        user.setStatus("ACTIVE");

        userService.updateUser(user);
    }
    @Test
    public void testDeleteUser() {

        userService.deleteUser(1);
    }

}
