package com.hackathon.thoven.repositories;

import com.hackathon.thoven.model.CardInfo;
import com.hackathon.thoven.model.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardInfoJpaRepository extends JpaRepository<CardInfo, Long> {

    List<CardInfo> findAllByGroupInfo(GroupInfo groupInfo);
}
