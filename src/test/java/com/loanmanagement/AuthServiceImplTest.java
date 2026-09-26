package com.loanmanagement;

import com.loanmanagement.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class AuthServiceImplTest {
    @Test
    void loginWithValidCredentials() {

        AuthServiceImpl authService = new AuthServiceImpl();

        boolean result = authService.login("deepthi", "1994");

        assertTrue(result);
    }
    @Test
    void loginWithWrongPassword() {

        AuthServiceImpl authService = new AuthServiceImpl();

        boolean result = authService.login("YOUR_USERNAME", "wrongPassword");

        assertFalse(result);
    }
    @Test
    void loginWithInactiveUser() {

        AuthServiceImpl authService = new AuthServiceImpl();

        boolean result = authService.login("INACTIVE_USERNAME", "PASSWORD");

        assertFalse(result);
    }
}
