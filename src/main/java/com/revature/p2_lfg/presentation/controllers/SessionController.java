package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.session.requests.CancelGroupRequest;
import com.revature.p2_lfg.presentation.models.session.requests.CreateGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.requests.WaitingRoomRequest;
import com.revature.p2_lfg.presentation.models.session.response.SessionResponse;
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
    public SessionResponse hostGroupSession(@RequestHeader("Authorization") String token, @RequestBody CreateGroupSessionRequest groupSession){
        dLog.debug("Creating a group session: " + groupSession);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.createGroupSession(groupSession, parsedJWT);
        else return null;
    }

    @GetMapping("/refresh")
    public SessionResponse refreshGroupSession(@RequestHeader("Authorization") String token, @RequestParam int groupId, @RequestParam int gameId){
        dLog.debug("Refreshing members in group Session: " + groupId);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.getGroupSession(groupId, gameId, parsedJWT);
        else return null;
    }

    @PostMapping("/join")
    public SessionResponse joinGroupSession(@RequestParam int groupId, @RequestParam int gameId, @RequestHeader("Authorization") String token){
        dLog.debug("Joining a group session: " + groupId);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.joinGroupSession(parsedJWT, groupId, gameId);
        else return null;
    }

    @GetMapping("/check")
    public SessionResponse getWaitingRoomResponse(@RequestHeader("Authorization") String token, @RequestParam int groupId){
        dLog.debug("Checking session status: " + groupId);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.checkSessionStatus(parsedJWT, groupId);
        else return null;
    }

    @GetMapping("/member-check")
    public SessionResponse getGroupMembers(@RequestHeader("Authorization") String token, @RequestParam int groupId){
        dLog.debug("Checking session status: " + groupId);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.getGroupMembersResponse(parsedJWT, groupId);
        else return null;
    }

    @PostMapping("/respond")
    public SessionResponse respondToUser(@RequestHeader("Authorization") String token, @RequestBody WaitingRoomRequest roomRequest){
        dLog.debug("Responding to user in with session status false: " + roomRequest);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.respondToUserSession(parsedJWT, roomRequest);
        return null;
    }

    @DeleteMapping("/cancel")
    public boolean cancelGroup(@RequestHeader("Authorization") String token, @RequestBody CancelGroupRequest cancelGroup){
        dLog.debug("Cancelling an active group session: " + cancelGroup);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.cancelSession(parsedJWT, cancelGroup);
        return false;
    }

    @DeleteMapping("/leave")
    public boolean leaveGroupSession(@RequestParam int groupId, @RequestParam int gameId, @RequestHeader("Authorization") String token){
        dLog.debug("Joining a group session: " + groupId);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.leaveSession(parsedJWT, groupId, gameId);
        return false;
    }

}
