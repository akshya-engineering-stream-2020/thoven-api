package com.hackathon.thoven.controller;

import com.hackathon.thoven.model.GroupInfo;
import com.hackathon.thoven.repositories.GroupInfoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thoven/group")
public class GroupController {

    @Autowired
    private GroupInfoJpaRepository groupInfoJpaRepository;

    @GetMapping
    public List<GroupInfo> list() {
        return groupInfoJpaRepository.findAll();
    }
}
