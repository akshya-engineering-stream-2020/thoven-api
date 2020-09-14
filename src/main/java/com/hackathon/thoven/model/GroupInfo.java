package com.hackathon.thoven.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "group_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GroupInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_info_id")
    private Long groupInfoId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_desp")
    private String groupDesp;

    @ManyToMany(mappedBy = "groupInfos")
    @JsonIgnore
    private List<UserInfo> userInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "groupInfo")
    @JsonIgnore
    private List<CardInfo> cardInfoList = new ArrayList<>();
}
