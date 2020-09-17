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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ThovenApiApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ThovenControllerTestIT {

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    @Autowired
    private CardInfoJpaRepository cardInfoJpaRepository;

    @Autowired
    private GroupInfoJpaRepository groupInfoJpaRepository;

    @Autowired
    private UserGroupInfoJpaRepository userGroupInfoJpaRepository;


    @Test
    public void findAllUsers() {
        UserInfo userInfo1 = setUserInfo("akshya", "chettiar", "akshya.chettiar", "test.hello@gmail.com",
                "password", "e3f", "koko");
        UserInfo userInfo2 = setUserInfo("harry", "potter", "harry.potter", "gg@123.com",
                "password", "fim", "tomcat");
        UserInfo saveUserInfo1 = userInfoJpaRepository.saveAndFlush(userInfo1);
        UserInfo saveUserInfo2 = userInfoJpaRepository.saveAndFlush(userInfo2);

        List<UserInfo> allUserInfo = userInfoJpaRepository.findAll();
        assertEquals(allUserInfo.size(), 2);
    }

    @Test
    public void findAllGroups() {
        GroupInfo groupInfo1 = setGroupInfo("group1", "this is test1");
        GroupInfo groupInfo2 = setGroupInfo("group2", "this is test2");
        GroupInfo saveGroupInfo1 = groupInfoJpaRepository.saveAndFlush(groupInfo1);
        GroupInfo saveGroupInfo2 = groupInfoJpaRepository.saveAndFlush(groupInfo2);

        List<GroupInfo> allGroupInfo = groupInfoJpaRepository.findAll();
        assertEquals(allGroupInfo.size(), 2);
    }

    @Test
    public void findAllCards() {
        GroupInfo groupInfo1 = setGroupInfo("group1", "this is test1");
        GroupInfo saveGroupInfo1 = groupInfoJpaRepository.saveAndFlush(groupInfo1);
        CardInfo cardInfo1 = setCardInfo("title1", "this is test1", "www.google1.com", 1l, "tribe",
                saveGroupInfo1);
        CardInfo cardInfo2 = setCardInfo("title2", "this is test2", "www.github1.com", 1l, "feature",
                saveGroupInfo1);
        CardInfo saveCardInfo1 = cardInfoJpaRepository.saveAndFlush(cardInfo1);
        CardInfo saveCardInfo2 = cardInfoJpaRepository.saveAndFlush(cardInfo2);

        List<CardInfo> allCardInfo = cardInfoJpaRepository.findAll();
        assertEquals(allCardInfo.size(), 2);
    }

    @Test
    public void findAllUserGroups() {
        UserInfo userInfo1 = setUserInfo("akshya", "chettiar", "akshya.chettiar", "test.hello@gmail.com",
                "password", "e3f", "koko");
        UserInfo saveUserInfo1 = userInfoJpaRepository.saveAndFlush(userInfo1);
        GroupInfo groupInfo1 = setGroupInfo("group1", "this is test1");
        GroupInfo saveGroupInfo1 = groupInfoJpaRepository.saveAndFlush(groupInfo1);
        UserGroupInfo userGroupInfo1 = setUserGroupInfo(saveUserInfo1, saveGroupInfo1, "no");
        UserGroupInfo userGroupInfo2 = setUserGroupInfo(saveUserInfo1, saveGroupInfo1, "yes");

        UserGroupInfo saveUserGroup1 = userGroupInfoJpaRepository.saveAndFlush(userGroupInfo1);
        UserGroupInfo saveUserGroup2 = userGroupInfoJpaRepository.saveAndFlush(userGroupInfo2);

        List<UserGroupInfo> allUserGroupInfo = userGroupInfoJpaRepository.findAll();
        assertEquals(allUserGroupInfo.size(), 2);
    }

    private UserInfo setUserInfo(String firstName, String lastName, String username, String email,
                                 String password, String tribe, String feature) {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(firstName);
        userInfo.setLastName(lastName);
        userInfo.setUsername(username);
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setTribe(tribe);
        userInfo.setFeature(feature);
        return userInfo;
    }

    private GroupInfo setGroupInfo(String groupName, String groupDesp) {
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupName(groupName);
        groupInfo.setGroupDesp(groupDesp);
        return groupInfo;
    }

    private CardInfo setCardInfo(String cardTitle, String cardDesp, String cardUrl, Long creatorInfoId, String cardLevel,
                                 GroupInfo groupInfo) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardTitle(cardTitle);
        cardInfo.setCardDesp(cardDesp);
        cardInfo.setCardUrl(cardUrl);
        cardInfo.setCreatorInfoId(creatorInfoId);
        cardInfo.setCardLevel(cardLevel);
        cardInfo.setGroupInfo(groupInfo);
        cardInfo.setCardShortUrl(null);
        return cardInfo;
    }

    private UserGroupInfo setUserGroupInfo(UserInfo userInfo, GroupInfo groupInfo, String admin) {
        UserGroupInfo userGroupInfo = new UserGroupInfo();
        userGroupInfo.setUserInfo(userInfo);
        userGroupInfo.setGroupInfo(groupInfo);
        userGroupInfo.setAdmin(admin);
        return userGroupInfo;
    }


}
