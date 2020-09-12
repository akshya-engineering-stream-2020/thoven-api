package com.hackathon.thoven.repositories;

import com.hackathon.thoven.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
}
