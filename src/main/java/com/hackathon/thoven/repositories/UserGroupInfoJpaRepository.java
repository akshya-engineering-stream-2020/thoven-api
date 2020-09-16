package com.hackathon.thoven.repositories;

import com.hackathon.thoven.model.UserGroupInfo;
import com.hackathon.thoven.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupInfoJpaRepository extends JpaRepository<UserGroupInfo, Long> {

    public List<UserGroupInfo> findAllByUserInfo(UserInfo userInfo);
}
