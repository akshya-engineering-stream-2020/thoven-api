package com.hackathon.thoven.controller;

import com.hackathon.thoven.ThovenApiApplication;
import com.hackathon.thoven.model.CardInfo;
import com.hackathon.thoven.model.GroupInfo;
import com.hackathon.thoven.model.UserGroupInfo;
import com.hackathon.thoven.model.UserInfo;
import com.hackathon.thoven.repositories.CardInfoJpaRepository;
import com.hackathon.thoven.repositories.GroupInfoJpaRepository;
import com.hackathon.thoven.repositories.UserGroupInfoJpaRepository;
import com.hackathon.thoven.repositories.UserInfoJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ThovenApiApplication.class
)
public class ThovenControllerTest {

    @Autowired
    private ThovenController thovenController;

    @MockBean
    private UserInfoJpaRepository userInfoJpaRepository;

    @MockBean
    private GroupInfoJpaRepository groupInfoJpaRepository;

    @MockBean
    private CardInfoJpaRepository cardInfoJpaRepository;

    @MockBean
    private UserGroupInfoJpaRepository userGroupInfoJpaRepository;

    @Test
    public void getAllUsers() {
        List<UserInfo> expectedUserInfoList = new ArrayList<>();
        expectedUserInfoList.add(setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature"));

        Mockito.doReturn(expectedUserInfoList).when(userInfoJpaRepository).findAll();

        List<UserInfo> actualUserInfoList = thovenController.getAllUsers();
        assertFalse(actualUserInfoList.isEmpty());
    }

    @Test
    public void getAllGroups() {
        List<GroupInfo> expectedGroupInfoList = new ArrayList<>();
        expectedGroupInfoList.add(setGroupInfo(1, "groupName", "groupDesp"));

        Mockito.doReturn(expectedGroupInfoList).when(groupInfoJpaRepository).findAll();

        List<GroupInfo> actualGroupInfoList = thovenController.getAllGroups();
        assertFalse(actualGroupInfoList.isEmpty());
    }

    @Test
    public void getAllCards() {
        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        List<CardInfo> expectedCardInfoList = new ArrayList<>();
        expectedCardInfoList.add(setCardInfo(1, "cardTitle", "cardDesp", "cardUrl", 1l, "cardLeve",
                groupInfo));

        Mockito.doReturn(expectedCardInfoList).when(cardInfoJpaRepository).findAll();

        List<CardInfo> actualCardInfoList = thovenController.getAllCards();
        assertFalse(actualCardInfoList.isEmpty());

    }

    @Test
    public void getAllUserGroups() {
        UserInfo userInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        List<UserGroupInfo> expectedUserGroupInfoList = new ArrayList<>();
        expectedUserGroupInfoList.add(setUserGroupInfo(1, userInfo, groupInfo, "yes"));

        Mockito.doReturn(expectedUserGroupInfoList).when(userGroupInfoJpaRepository).findAll();

        List<UserGroupInfo> actualUserGroupInfoList = thovenController.getAllUserGroups();
        assertFalse(actualUserGroupInfoList.isEmpty());
    }

    private UserInfo setUserInfo(Integer userInfoId, String firstName, String lastName, String username, String email,
                                 String password, String tribe, String feature) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoId(userInfoId);
        userInfo.setFirstName(username);
        userInfo.setFirstName(firstName);
        userInfo.setLastName(lastName);
        userInfo.setUsername(username);
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setTribe(tribe);
        userInfo.setFeature(feature);
        return userInfo;
    }

    private GroupInfo setGroupInfo(Integer groupInfoId, String groupName, String groupDesp) {
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupInfoId(groupInfoId);
        groupInfo.setGroupName(groupName);
        groupInfo.setGroupDesp(groupDesp);
        return groupInfo;
    }

    private CardInfo setCardInfo(Integer cardInfoId, String cardTitle, String cardDesp, String cardUrl, Long creatorInfoId, String cardLevel,
                                 GroupInfo groupInfo) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardInfoId(cardInfoId);
        cardInfo.setCardTitle(cardTitle);
        cardInfo.setCardDesp(cardDesp);
        cardInfo.setCardUrl(cardUrl);
        cardInfo.setCreatorInfoId(creatorInfoId);
        cardInfo.setCardLevel(cardLevel);
        cardInfo.setGroupInfo(groupInfo);
        cardInfo.setCardShortUrl(null);
        return cardInfo;
    }

    private UserGroupInfo setUserGroupInfo(Integer userGroupInfoId, UserInfo userInfo, GroupInfo groupInfo, String admin) {
        UserGroupInfo userGroupInfo = new UserGroupInfo();
        userGroupInfo.setUserGroupInfoId(userGroupInfoId);
        userGroupInfo.setUserInfo(userInfo);
        userGroupInfo.setGroupInfo(groupInfo);
        userGroupInfo.setAdmin(admin);
        return userGroupInfo;
    }
}
