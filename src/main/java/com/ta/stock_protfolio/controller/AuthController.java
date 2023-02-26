package com.ta.stock_protfolio.controller;

import com.ta.stock_protfolio.beans.LoginParams;
import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.exceptions.SystemException;
import com.ta.stock_protfolio.security.JWTUtil;
import com.ta.stock_protfolio.security.MyUserDetailsService;
import com.ta.stock_protfolio.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final MyUserDetailsService myUserDetailsService;
    private final UserService userService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> login(@RequestBody LoginParams loginParams) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(loginParams.getEmail(), loginParams.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginParams.getEmail());
        Map<String, Object> claims = new HashMap<>();
        final String jwt = jwtUtil.generateToken(userDetails, claims);
        User user = userService.findByEmail(jwtUtil.extractUsername(jwt));
        userService.setUser(user);
        Map<String, Object> map = new HashMap<>();
        map.put("jwt_token", jwt);
        map.put("username", loginParams.getEmail());
        return map;
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserAccount(@RequestBody @Valid User user) throws SystemException {
        userService.registerNewUserAccount(user);
    }

}
