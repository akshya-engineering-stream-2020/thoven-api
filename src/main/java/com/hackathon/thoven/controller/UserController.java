package com.hackathon.thoven.controller;

import com.hackathon.thoven.model.AuthRequest;
import com.hackathon.thoven.model.UserInfo;
import com.hackathon.thoven.repositories.UserInfoJpaRepository;
import com.hackathon.thoven.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thoven/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    @GetMapping("/getUsername")
    public String getUsername() {
        return "akshya";
    }

    @GetMapping
    public List<UserInfo> list() {
        return userInfoJpaRepository.findAll();
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invald username/password");
        }

        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
