package com.fox.cradle.configuration.security.auth;

import com.fox.cradle.configuration.security.jwt.JwtService;
import com.fox.cradle.configuration.security.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("test")
class AuthenticationServiceIntegrationTest
{
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authService;

    @Test
    void authenticateTest()
    {
        //GIVEN
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("icecream@gmail.com");
        request.setPassword("startrek");

        //WHEN
        AuthenticationResponse response = authService.authenticate(request);

        //THEN
        assert response.getToken() != null;
        assertTrue(response.getToken().length() > 0);
        assert jwtService.isTokenValid(response.getToken(), new User(1,"icecream@gmail.com", "startrek", "Ice Cream Company", true));
    }

    @Test
    void testRefreshToken()
    {
        // GIVEN
        User user = new User();
        user.setEmail("icecream@gmail.com");
        String token = jwtService.generateToken(user);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        // WHEN
        AuthenticationResponse authResponse = authService.refreshToken(request);
        String refreshToken = authResponse.getToken();
        // THEN
        assertNotNull(refreshToken, "Refresh token should not be null");
        assertTrue(jwtService.isTokenValid(refreshToken, user), "Refresh token should be valid for the user");
    }

    //Test for invalid token has a problem that I can not catch the error
}
