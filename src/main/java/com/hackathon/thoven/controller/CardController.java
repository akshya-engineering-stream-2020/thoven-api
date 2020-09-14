package com.hackathon.thoven.controller;

import com.hackathon.thoven.model.CardInfo;
import com.hackathon.thoven.model.GroupInfo;
import com.hackathon.thoven.repositories.CardInfoJpaRepository;
import com.hackathon.thoven.repositories.GroupInfoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thoven/card")
public class CardController {

    @Autowired
    private CardInfoJpaRepository cardInfoJpaRepository;

    @Autowired
    private GroupInfoJpaRepository groupInfoJpaRepository;

    @GetMapping
    public List<CardInfo> list() {
        return cardInfoJpaRepository.findAll();
    }

    @GetMapping("/testing")
    public List<CardInfo> something() {
        GroupInfo groupInfo = groupInfoJpaRepository.getOne(1L);
        return cardInfoJpaRepository.findAllByGroupInfo(groupInfo);
    }

}
