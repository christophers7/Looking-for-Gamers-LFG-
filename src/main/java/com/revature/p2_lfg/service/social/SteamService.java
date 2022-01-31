package com.revature.p2_lfg.service.social;

import com.revature.p2_lfg.repository.entities.user.Socials;
import com.revature.p2_lfg.service.game.classes.GameService;
import com.revature.p2_lfg.service.profile.classes.SocialsService;
import com.revature.p2_lfg.utility.SteamDev;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service("steamService")
public class SteamService {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private GameService gameService;

    public Object getSteamProfile(Socials userSocial) {
        dLog.debug("Getting user Steam Profile: " + userSocial);
        String userId = userSocial.getGamertag();
        String url = new SteamUrl.SteamUrlBuilder(SteamDev.BASE_URL, SteamDev.SUMMARY)
                .key(SteamDev.KEY)
                .userId("&steamids=" + userId)
                .build()
                .toString();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getSteamAchievements(Socials userSocial) {
        dLog.debug("Getting user steam achievements: " + userSocial);
        int platformKey = gameService.getGamePlatformKey(userSocial.getGameid());
        String userId = userSocial.getGamertag();
        String url = new SteamUrl.SteamUrlBuilder(SteamDev.BASE_URL, SteamDev.ACHIEVEMENTS)
                .appId("?appid=" + platformKey)
                .key(SteamDev.KEY)
                .userId("&steamids=" + userId)
                .build()
                .toString();
        RestTemplate restTemplate = new RestTemplate();
        dLog.debug("URL: " + url);
        return restTemplate.getForObject(url, Object.class);
    }

}
