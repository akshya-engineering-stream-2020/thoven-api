package com.hackathon.thoven.controller;

import com.hackathon.thoven.model.*;
import com.hackathon.thoven.repositories.CardInfoJpaRepository;
import com.hackathon.thoven.repositories.GroupInfoJpaRepository;
import com.hackathon.thoven.repositories.UserGroupInfoJpaRepository;
import com.hackathon.thoven.repositories.UserInfoJpaRepository;
import com.hackathon.thoven.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/thoven")
@CrossOrigin(origins = "*")
public class ThovenController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    @Autowired
    private CardInfoJpaRepository cardInfoJpaRepository;

    @Autowired
    private GroupInfoJpaRepository groupInfoJpaRepository;

    @Autowired
    private UserGroupInfoJpaRepository userGroupInfoJpaRepository;

    @GetMapping("/users")
    public List<UserInfo> getAllUsers() {
        return userInfoJpaRepository.findAll();
    }

    @GetMapping("/groups")
    public List<GroupInfo> getAllGroups() {
        return groupInfoJpaRepository.findAll();
    }

    @GetMapping("/user-groups")
    public List<UserGroupInfo> getAllUserGroups() {
        return userGroupInfoJpaRepository.findAll();
    }

    @GetMapping("/cards")
    public List<CardInfo> getAllCards() {
        return cardInfoJpaRepository.findAll();
    }

    @GetMapping("/user/{username}")
    public UserInfo getUserInfoByUsername(@PathVariable String username) {
        return userInfoJpaRepository.findByUsername(username);
    }

    @GetMapping("/group/{groupName}")
    public GroupInfo getGroupByGroupName(@PathVariable String groupName) {
        return groupInfoJpaRepository.getByGroupName(groupName);
    }

    @PostMapping("/create-card")
    public CardInfo createCard(@RequestBody CardInfo cardInfo) {
        return cardInfoJpaRepository.saveAndFlush(cardInfo);
    }

    @PostMapping("/user-cards")
    public List<CardInfo> getAllCardsOfUser(@RequestBody UserInfo userInfo) {
        List<CardInfo> cardInfoList = new ArrayList<>();

        List<UserGroupInfo> getUserGroupInfo = userGroupInfoJpaRepository.
                findAllByUserInfo(userInfo);
        for (UserGroupInfo userGroupInfo : getUserGroupInfo) {
            List<CardInfo> getCardInfo = cardInfoJpaRepository.
                    findAllByGroupInfo(userGroupInfo.getGroupInfo());

            for (CardInfo cardInfo : getCardInfo) {
                cardInfoList.add(cardInfo);
            }
        }

        return cardInfoList;
    }

    @PostMapping("/user-groups")
    public List<GroupInfo> getAllGroupsOfUser(@RequestBody UserInfo userInfo) {
        List<GroupInfo> groupInfoList = new ArrayList<>();

        List<UserGroupInfo> getUserGroupInfo = userGroupInfoJpaRepository.
                findAllByUserInfo(userInfo);
        for (UserGroupInfo userGroupInfo : getUserGroupInfo) {
            GroupInfo groupInfo = userGroupInfo.getGroupInfo();
            groupInfoList.add(groupInfo);
        }

        return groupInfoList;
    }

    @PostMapping("/user-groups-admin-details")
    public List<UserGroupInfo> getAllGroupsAdminDetailsOfUser(@RequestBody UserInfo userInfo) {
        return userGroupInfoJpaRepository.findAllByUserInfo(userInfo);
    }

    @PostMapping("/count-cards")
    public Integer getCountOfCardsOfGroup(@RequestBody GroupInfo groupInfo) {
        return cardInfoJpaRepository.countAllByGroupInfo(groupInfo);
    }

    @PostMapping("/count-members")
    public Integer getCountOfMembersOfGroup(@RequestBody GroupInfo groupInfo) {
        return userGroupInfoJpaRepository.countAllByGroupInfo(groupInfo);
    }

    @PostMapping("/group-cards")
    public List<CardInfo> getCardsOfGroup(@RequestBody GroupInfo groupInfo) {
        return cardInfoJpaRepository.findAllByGroupInfo(groupInfo);
    }

    @PostMapping("/group-members")
    public List<UserGroupInfo> getUsersOfGroup(@RequestBody GroupInfo groupInfo) {
        return userGroupInfoJpaRepository.findAllByGroupInfo(groupInfo);
    }

    @PostMapping("/create-group")
    public GroupInfo createGroup(@RequestBody GroupInfo groupInfo) {
        return groupInfoJpaRepository.saveAndFlush(groupInfo);
    }

    @PostMapping("/create-user-groups")
    public UserGroupInfo createUserGroups(@RequestBody UserGroupInfo userGroupInfo) {
        return userGroupInfoJpaRepository.saveAndFlush(userGroupInfo);
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
