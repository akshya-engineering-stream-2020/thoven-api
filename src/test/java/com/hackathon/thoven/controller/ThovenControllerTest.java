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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

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
        expectedCardInfoList.add(setCardInfo(1, "cardTitle", "cardDesp", "cardUrl", 1l, "cardLevel",
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

    @Test
    public void getUserInfoByUsername() {
        UserInfo expectedUserInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        Mockito.doReturn(expectedUserInfo).when(userInfoJpaRepository).findByUsername(anyString());

        UserInfo actualUserInfo = thovenController.getUserInfoByUsername("username");
        assertTrue(actualUserInfo.getUsername().equalsIgnoreCase("username"));
    }

    @Test
    public void getGroupByGroupName() {
        GroupInfo expectedGroupInfo = setGroupInfo(1, "groupName", "groupDesp");

        Mockito.doReturn(expectedGroupInfo).when(groupInfoJpaRepository).getByGroupName(anyString());

        GroupInfo actualGroupInfo = thovenController.getGroupByGroupName("groupname");
        assertTrue(actualGroupInfo.getGroupName().equalsIgnoreCase("groupname"));
    }

    @Test
    public void getAllCardsOfUser() {
        UserInfo userInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        List<UserGroupInfo> expectedUserGroupInfoList = new ArrayList<>();
        expectedUserGroupInfoList.add(setUserGroupInfo(1, userInfo, groupInfo, "yes"));
        List<CardInfo> expectedCardInfoList = new ArrayList<>();
        expectedCardInfoList.add(setCardInfo(1, "cardTitle", "cardDesp", "cardUrl", 1l, "cardLevel",
                groupInfo));

        Mockito.doReturn(expectedUserGroupInfoList).when(userGroupInfoJpaRepository).findAllByUserInfo(any());
        Mockito.doReturn(expectedCardInfoList).when(cardInfoJpaRepository).findAllByGroupInfo(any());

        UserInfo actualUserInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        List<CardInfo> actualCardList = thovenController.getAllCardsOfUser(actualUserInfo);
        assertFalse(actualCardList.isEmpty());
    }

    @Test
    public void getAllGroupsOfUser() {
        UserInfo userInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        List<UserGroupInfo> expectedUserGroupInfoList = new ArrayList<>();
        expectedUserGroupInfoList.add(setUserGroupInfo(1, userInfo, groupInfo, "yes"));

        Mockito.doReturn(expectedUserGroupInfoList).when(userGroupInfoJpaRepository).findAllByUserInfo(any());

        UserInfo actualUserInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        List<GroupInfo> actualGroupList = thovenController.getAllGroupsOfUser(actualUserInfo);
        assertFalse(actualGroupList.isEmpty());
    }

    @Test
    public void getAllGroupsAdminDetailsOfUser() {
        UserInfo userInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        List<UserGroupInfo> expectedUserGroupInfoList = new ArrayList<>();
        expectedUserGroupInfoList.add(setUserGroupInfo(1, userInfo, groupInfo, "yes"));

        Mockito.doReturn(expectedUserGroupInfoList).when(userGroupInfoJpaRepository).findAllByUserInfo(any());

        UserInfo actualUserInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        List<UserGroupInfo> actualUserGroupList = thovenController.getAllGroupsAdminDetailsOfUser(actualUserInfo);
        assertFalse(actualUserGroupList.isEmpty());
    }

    @Test
    public void getCountOfCardsOfGroup() {
        Mockito.doReturn(10).when(cardInfoJpaRepository).countAllByGroupInfo(any());

        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        assertEquals((int) thovenController.getCountOfCardsOfGroup(groupInfo), 10);
    }

    @Test
    public void getCountOfMembersOfGroup() {
        Mockito.doReturn(10).when(userGroupInfoJpaRepository).countAllByGroupInfo(any());

        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        assertEquals((int) thovenController.getCountOfMembersOfGroup(groupInfo), 10);
    }

    @Test
    public void getCardsOfGroup() {
        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        List<CardInfo> expectedCardInfoList = new ArrayList<>();
        expectedCardInfoList.add(setCardInfo(1, "cardTitle", "cardDesp", "cardUrl", 1l, "cardLevel",
                groupInfo));

        Mockito.doReturn(expectedCardInfoList).when(cardInfoJpaRepository).findAllByGroupInfo(any());

        GroupInfo actualGroupInfo = setGroupInfo(1, "groupName", "groupDesp");
        List<CardInfo> actualCardInfoList = thovenController.getCardsOfGroup(actualGroupInfo);
        assertFalse(actualCardInfoList.isEmpty());
    }

    @Test
    public void getUsersOfGroup() {
        UserInfo userInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        List<UserGroupInfo> expectedUserGroupInfoList = new ArrayList<>();
        expectedUserGroupInfoList.add(setUserGroupInfo(1, userInfo, groupInfo, "yes"));

        Mockito.doReturn(expectedUserGroupInfoList).when(userGroupInfoJpaRepository).findAllByGroupInfo(any());

        GroupInfo actualGroupInfo = setGroupInfo(1, "groupName", "groupDesp");
        List<UserGroupInfo> actualUserGroupInfoList = thovenController.getUsersOfGroup(actualGroupInfo);
        assertFalse(actualUserGroupInfoList.isEmpty());
    }

    @Test
    public void createGroup() {
        GroupInfo expectedGroupInfo = setGroupInfo(1, "groupName", "groupDesp");

        Mockito.doReturn(expectedGroupInfo).when(groupInfoJpaRepository).saveAndFlush(any());

        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        GroupInfo actualGroupInfo = thovenController.createGroup(groupInfo);
        assertTrue(actualGroupInfo.getGroupName().equalsIgnoreCase("groupName"));
    }

    @Test
    public void createUserGroups() {
        UserInfo userInfo = setUserInfo(1, "firstName", "lastName", "username", "email.com",
                "password", "tribe", "feature");
        GroupInfo groupInfo = setGroupInfo(1, "groupName", "groupDesp");
        UserGroupInfo expectedUserGroupInfo = setUserGroupInfo(1, userInfo, groupInfo, "yes");

        Mockito.doReturn(expectedUserGroupInfo).when(userGroupInfoJpaRepository).saveAndFlush(any());

        UserGroupInfo userGroupInfo = setUserGroupInfo(1, userInfo, groupInfo, "yes");
        UserGroupInfo actualUserGroupInfo = thovenController.createUserGroups(userGroupInfo);
        assertTrue(actualUserGroupInfo.getUserInfo().getUsername().equalsIgnoreCase("username"));
    }

    @Test
    public void deleteCardByCardId() {
        Mockito.doNothing().when(cardInfoJpaRepository).deleteById(anyInt());
        thovenController.deleteCardByCardId(2);
        Mockito.verify(cardInfoJpaRepository).deleteById(2);
    }

    @Test
    public void deleteUserGroupByUserGroupId() {
        Mockito.doNothing().when(userGroupInfoJpaRepository).deleteById(anyInt());
        thovenController.deleteUserGroupByUserGroupId(2);
        Mockito.verify(userGroupInfoJpaRepository).deleteById(2);
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
