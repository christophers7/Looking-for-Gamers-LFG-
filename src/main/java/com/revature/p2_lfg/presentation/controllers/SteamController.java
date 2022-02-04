package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.service.game.classes.GameService;
import com.revature.p2_lfg.service.profile.classes.SocialsService;
import com.revature.p2_lfg.service.social.SteamService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("steamController")
@RequestMapping("/steam")
public class SteamController {

    @Autowired
    private SocialsService socialsService;

    @Autowired
    private GameService gameService;

    @Autowired
    private SteamService steamService;

    @GetMapping("/profile")
    public Object getSteamProfile(@RequestParam int gameId, @RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) {
            return steamService.getSteamProfile(socialsService.getUserSocial(parsedJWT.getUserId(), gameId));
        }
        return null;
    }


    @GetMapping("/achievements")
    public Object getSteamAchievement(@RequestParam int gameId, @RequestHeader("Authorization") String token) {
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if (parsedJWT != null) {
            System.out.println(parsedJWT);
            return steamService.getSteamAchievements(socialsService.getUserSocial(parsedJWT.getUserId(), gameId));
        }
        return null;
    }


}
