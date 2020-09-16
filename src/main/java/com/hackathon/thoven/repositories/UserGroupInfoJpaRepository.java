package com.hackathon.thoven.repositories;

import com.hackathon.thoven.model.GroupInfo;
import com.hackathon.thoven.model.UserGroupInfo;
import com.hackathon.thoven.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupInfoJpaRepository extends JpaRepository<UserGroupInfo, Integer> {

    List<UserGroupInfo> findAllByUserInfo(UserInfo userInfo);

    Integer countAllByGroupInfo(GroupInfo groupInfo);

    List<UserGroupInfo> findAllByGroupInfo(GroupInfo groupInfo);
}
