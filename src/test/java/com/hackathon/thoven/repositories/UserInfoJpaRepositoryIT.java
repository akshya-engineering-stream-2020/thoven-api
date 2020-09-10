package com.hackathon.thoven.repositories;

import com.hackathon.thoven.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserInfoJpaRepositoryIT {

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    @Test
    public void list() {
        List<UserInfo> userInfoList = userInfoJpaRepository.findAll();
        assertTrue(userInfoList.size() > 0);
    }
}