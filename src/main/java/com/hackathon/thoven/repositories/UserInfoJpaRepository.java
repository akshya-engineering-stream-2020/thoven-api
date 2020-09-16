package com.hackathon.thoven.repositories;

import com.hackathon.thoven.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
    public UserDetails findByUserInfoId(String username);

    public UserInfo findByFirstName(String username);

    public UserInfo findByUsername(String username);

    public UserInfo getByUsername(String username);
}
