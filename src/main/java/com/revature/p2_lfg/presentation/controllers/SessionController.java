package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.session.CreateGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.CreatedGroupSessionResponse;
import com.revature.p2_lfg.service.session.classes.SessionService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("sessionController")
@RequestMapping("/group")
public class SessionController {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private SessionService sessionService;

    @PostMapping("/host")
    public CreatedGroupSessionResponse hostGroupSession(@RequestHeader("Authorization") String token, @RequestBody CreateGroupSessionRequest groupSession){
        dLog.debug("Creating a group session: " + groupSession);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.createGroupSession(groupSession, parsedJWT);
        else return null;
    }

}
