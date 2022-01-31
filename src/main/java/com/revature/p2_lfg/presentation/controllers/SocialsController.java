package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.profile.requests.CreateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.DeleteSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.responses.SocialResponse;
import com.revature.p2_lfg.service.profile.classes.SocialsService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("socialController")
@RequestMapping("/social")
public class SocialsController {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");
    @Autowired
    private SocialsService socialsService;

    @GetMapping("/user")
    public SocialResponse getUserSocial(@RequestParam int gameId, @RequestHeader("Authorization") String token){
        dLog.debug("Getting users social for game: " + gameId);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.getUserSocialResponse(parsedJWT, gameId);
        else return null;
    }

    @GetMapping("/group-users")
    public List<SocialResponse> getGroupSocials(@RequestParam int gameId, @RequestParam int groupId, @RequestHeader("Authorization") String token){
        dLog.debug("Getting group socials: " + groupId);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.getGroupSocials(gameId, groupId, parsedJWT);
        else return null;
    }

    @PostMapping("/create")
    public SocialResponse createUserSocial(@RequestHeader("Authorization") String token, @RequestBody CreateSocialRequest socialRequest){
        dLog.debug("Creating user social: " + socialRequest);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.createUserSocial(socialRequest, parsedJWT);
        else return null;
    }

    @PutMapping("/update")
    public SocialResponse updateUserSocial(@RequestHeader("Authorization") String token, @RequestBody UpdateSocialRequest updateSocial){
        dLog.debug("Updating user social: " + updateSocial);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.updateUserSocial(updateSocial, parsedJWT);
        else return null;
    }

    @DeleteMapping("/delete")
    public boolean deleteUserSocial(@RequestHeader("Authorization") String token, @RequestBody DeleteSocialRequest deleteSocial){
        dLog.debug("Deleted user social: " + deleteSocial);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.deleteSocial(deleteSocial, parsedJWT);
        else return false;
    }

}
