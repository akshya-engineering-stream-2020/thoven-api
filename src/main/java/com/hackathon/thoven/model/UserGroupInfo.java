package com.hackathon.thoven.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "user_group_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserGroupInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_info_id")
    private Integer userGroupInfoId;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "group_info_id")
    private GroupInfo groupInfo;

    private String admin;

}
