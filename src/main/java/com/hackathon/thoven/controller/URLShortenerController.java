package com.hackathon.thoven.controller;

import com.hackathon.thoven.exception.InvalidUrlException;
import com.hackathon.thoven.model.CardInfo;
import com.hackathon.thoven.repositories.CardInfoJpaRepository;
import com.hackathon.thoven.repositories.GroupInfoJpaRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/thoven")
@CrossOrigin(origins = "*")
public class URLShortenerController {

    @Autowired
    private CardInfoJpaRepository cardInfoJpaRepository;

    @Autowired
    private GroupInfoJpaRepository groupInfoJpaRepository;

    @PostMapping("/url-shortener/{cardID}")
    public String generateShortUrl(@PathVariable("cardID") Integer cardId, @RequestBody String word) throws Exception {
        UrlValidator urlValidator = new UrlValidator();
        String urlId = null;
        String baseUrl = "http://localhost:8888/thoven/";

        CardInfo cardInfo = cardInfoJpaRepository.getOne(cardId);
        if (cardInfo.getCardShortUrl() == null) {
            if (urlValidator.isValid(word)) {
                urlId = word + word.length() + cardId.toString();
                String shortUrlId = Base64.getEncoder()
                        .encodeToString(urlId.getBytes(StandardCharsets.UTF_8.toString())).substring(cardId % word.length(), 7 + cardId % word.length());
                cardInfo.setCardShortUrl(shortUrlId);
                cardInfoJpaRepository.saveAndFlush(cardInfo);
                return baseUrl + cardInfo.getGroupInfo().getGroupName() + "/" + cardInfo.getCardLevel() + "/" + shortUrlId;
            } else {
                throw new InvalidUrlException("Url is invalid");
            }
        } else {
            return baseUrl + cardInfo.getGroupInfo().getGroupName() + "/" + cardInfo.getCardLevel() + "/" + cardInfo.getCardShortUrl();
        }
    }

    @GetMapping("/{group}/{level}/{code}")
    public ResponseEntity<?> redirectToUrl(@PathVariable("group") String groupName, @PathVariable("level") String level,
                                           @PathVariable("code") String shortUrlCode) throws URISyntaxException {

        if (groupInfoJpaRepository.getByGroupName(groupName) != null &&
                cardInfoJpaRepository.getByCardLevelAndCardShortUrl(level, shortUrlCode) != null) {

            String longUrl = cardInfoJpaRepository.getByCardLevelAndCardShortUrl(level, shortUrlCode).getCardUrl();
            URI uri = new URI(longUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            return new ResponseEntity(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
