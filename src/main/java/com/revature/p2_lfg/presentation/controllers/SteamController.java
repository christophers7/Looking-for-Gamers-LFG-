package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.service.game.classes.GameService;
import com.revature.p2_lfg.service.profile.classes.SocialsService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController("steamController")
@RequestMapping("/steam")
public class SteamController {


    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private SocialsService socialsService;

    @Autowired
    private GameService gameService;

    String steamApiKey = "9AEEA5F03CDDB51E2408BBAA26EB114F";

    @GetMapping("/profile")
    public Object getSteamProfile(@RequestParam int gameId, @RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) {
            String userId = socialsService.getUserSocial(parsedJWT.getUserId(), gameId).getGamertag();
            String url = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="
                    + steamApiKey
                    + "&steamids=" + userId;
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(url, Object.class);
        }
        return null;
    }


    @GetMapping("/achievements")
    private Object getSteamAchievement(@RequestParam int gameId, @RequestHeader("Authorization") String token) {
        dLog.debug("Finding Steam Achievements");
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if (parsedJWT != null) {
            String userId = socialsService.getUserSocial(parsedJWT.getUserId(), gameId).getGamertag();
            int platformKey = gameService.getGamePlatformKey(gameId);
            String url = "http://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001/?appid=" + platformKey
                    + "&key=" + steamApiKey
                    + "&steamid=" + userId;
            RestTemplate restTemplate = new RestTemplate();
            dLog.debug("URL: " + url);
            return restTemplate.getForObject(url, Object.class);
        }
        return null;
    }
}
