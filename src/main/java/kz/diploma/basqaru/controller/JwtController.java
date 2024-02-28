package kz.diploma.basqaru.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.diploma.basqaru.model.JwtAuthenticationRequest;
import kz.diploma.basqaru.service.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "JwtController", description = "Endpoints for JWT authentication")
public class JwtController {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    @Operation(summary = "Get JWT token for authenticated user", description = "Authenticates user and generates JWT token")
    public String getTokenForAuthenticatedUser(@RequestBody JwtAuthenticationRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (authRequest.getUsername(), authRequest.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user credentials");
        }
    }

}
