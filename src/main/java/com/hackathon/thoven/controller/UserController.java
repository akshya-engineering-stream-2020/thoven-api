package com.hackathon.thoven.controller;

import com.hackathon.thoven.model.UserInfo;
import com.hackathon.thoven.repositories.UserInfoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thoven/user")
public class UserController {

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    @GetMapping
    public List<UserInfo> list() {
        return userInfoJpaRepository.findAll();
    }
}
