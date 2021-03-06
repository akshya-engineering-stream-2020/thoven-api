package com.hackathon.thoven.repositories;

import com.hackathon.thoven.model.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupInfoJpaRepository extends JpaRepository<GroupInfo, Integer> {

    public GroupInfo getByGroupName(String groupName);
}
