package com.hackathon.thoven.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "card_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_info_id")
    private Integer cardInfoId;

    @Column(name = "card_title")
    private String cardTitle;

    @Column(name = "card_desp")
    private String cardDesp;

    @Column(name = "card_url")
    private String cardUrl;

    @Column(name = "creator_info_id")
    private Long creatorInfoId;

    @Column(name = "card_level")
    private String cardLevel;

    @ManyToOne
    @JoinColumn(name = "group_info_id")
    private GroupInfo groupInfo;

    @Column(name = "card_short_url")
    private String cardShortUrl;
}
