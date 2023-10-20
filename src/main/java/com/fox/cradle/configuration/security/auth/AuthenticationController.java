package com.fox.cradle.configuration.security.auth;

import com.fox.cradle.configuration.security.config.TokenCheckException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        var response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request)
    {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @GetMapping("/check-token")
    public ResponseEntity<Void> checkToken(HttpServletRequest request)
    {
        boolean isValidToken;

        try {
            isValidToken = authService.checkToken(request);
        }
        catch (TokenCheckException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (isValidToken) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}