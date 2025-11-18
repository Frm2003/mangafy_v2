package com.mangafy.api.adapters.controller;

import com.mangafy.api.adapters.dto.LoginDto;
import com.mangafy.api.adapters.dto.TokenDto;
import com.mangafy.api.infra.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha()));

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.gerarToken(user);

        return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token));
    }
}

