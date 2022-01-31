package com.revature.p2_lfg.service.session.classes;

import com.revature.p2_lfg.presentation.models.session.*;
import com.revature.p2_lfg.repository.interfaces.LoginRepository;
import com.revature.p2_lfg.repository.interfaces.SessionDetailsRepository;
import com.revature.p2_lfg.repository.interfaces.SessionRepository;
import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import com.revature.p2_lfg.repository.entities.session.Games;
import com.revature.p2_lfg.repository.entities.session.Session;
import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import com.revature.p2_lfg.repository.entities.session.Tag;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.service.session.MaxUsersException;
import com.revature.p2_lfg.service.session.exception.InvalidHostUserException;
import com.revature.p2_lfg.service.session.exception.InvalidUserException;
import com.revature.p2_lfg.service.session.interfaces.SessionServiceable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.p2_lfg.utility.JWTInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.p2_lfg.service.session.dto.GroupUser;

import java.util.*;

@Service("sessionService")
    public class SessionService implements SessionServiceable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private SessionDetailsRepository sessionDetailsRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private LoginRepository loginRepository;

    public SessionResponse createGroupSession(CreateGroupSessionRequest createGroup, JWTInfo parsedJWT) {
        dLog.debug("Creating group session response from group create request: " + createGroup);
        SessionDetails sessionDetails = createGroupSessionInDatabase(createGroup.getGameId() , createGroup.getMaxUsers(), createGroup.getDescription());
        createUserSession(sessionDetails, parsedJWT, parsedJWT.getUserId(), true);
        return new SessionResponse.SessionResponseBuilder(getHostUserWithGroupId(sessionDetails.getGroupid()), sessionDetails).build();
    }

    @Override
    public SessionResponse joinGroupSession(JWTInfo parsedJWT, int groupId, int gameId) throws MaxUsersException {
        dLog.debug("Joining group session waiting room Group ID: " + groupId + " JWT: " + parsedJWT);
        return enterWaitingRoom(groupId, parsedJWT);
    }

    private SessionResponse enterWaitingRoom(int groupId, JWTInfo parsedJWT) {
        dLog.debug("Attempting to enter waiting room: " + groupId);
        SessionDetails sessionDetails = getSessionDetailsByGroupId(groupId);
        createUserSession(sessionDetails, parsedJWT, findByHostId(groupId),  false);
        return new SessionResponse.SessionResponseBuilder(getHostUserWithGroupId(groupId), sessionDetails)
                .groupMembers(getGroupMembersOfSession(groupId))
                .build();
    }

    private GroupUser getHostUserWithGroupId(int groupId) {
        dLog.debug("Getting host user");
        GroupUser host = new GroupUser();
        host.setUsername(getHostUserWithId(findByHostId(groupId)).getUsername());
        host.setGroupId(groupId);
        host.setInsideSession(true);
        return host;
    }

    private UserCredential getHostUserWithId(int hostId) {
        dLog.debug("Getting Host user with Host Id: " + hostId);
        return loginRepository.findById(hostId).orElseThrow(InvalidHostUserException::new);
    }

    private SessionDetails sessionDetailsForId(int groupId){
        return new SessionDetails(groupId, new Games(), 0, 0, "", new HashSet<>());
    }

    private int findByHostId(int groupId) {
        dLog.debug("Finding host Id by Group ID: " + groupId);
        return sessionRepository.findFirst1HostidByGroupsession(sessionDetailsForId(groupId)).getHostid();
    }

    private List<GroupUser> getGroupMembersOfSession(int groupId) {
        dLog.debug("Getting group users associated by group Id: " + groupId);
        List<Session> userInSession = sessionRepository.findAllByGroupIdAndInsession(groupId, true);
        List<GroupUser> groupUsers = new ArrayList<>();
        userInSession.forEach(s -> {
            Optional<UserCredential> user = loginRepository.findById(s.getUserid());
            groupUsers.add(new GroupUser(user.orElseThrow(InvalidUserException::new).getUsername(), s.getGroupsession().getGroupid(), s.isInsession()));
        });
        return groupUsers;
    }

    private List<GroupUser> getWaitingMembersOfSession(int groupId) {
        dLog.debug("Getting group users associated by group Id: " + groupId);
        List<Session> userInSession = sessionRepository.findAllByGroupIdAndInsession(groupId, false);
        List<GroupUser> groupUsers = new ArrayList<>();
        userInSession.forEach(s -> {
            Optional<UserCredential> user = loginRepository.findById(s.getUserid());
            groupUsers.add(new GroupUser(user.isPresent()? user.get().getUsername() : "NOT PRESENT", s.getGroupsession().getGroupid(), s.isInsession()));
        });
        return groupUsers;
    }

    private GroupSessionId createUserSession(SessionDetails sessionDetails, JWTInfo parsedJWT, int hostId, boolean status) throws MaxUsersException {
        dLog.debug("Creating user Session for a group: " + sessionDetails);
        if(sessionDetails.getCurrentusers() < sessionDetails.getMaxusers()) {
            sessionDetails.setCurrentusers(sessionDetails.getCurrentusers() + 1);
            if(sessionRepository.save(new Session(parsedJWT.getUserId(), hostId, sessionDetails, status)) != null)
                return new GroupSessionId(parsedJWT.getUserId(), hostId);
        } else throw new MaxUsersException();
        return null;
    }

    private SessionDetails getSessionDetailsByGroupId(int groupId) {
        dLog.debug("Getting session details by group ID: " + groupId);
        Optional<SessionDetails> session = sessionDetailsRepository.findById(groupId);
        return session.orElse(null);
    }

    private SessionDetails createGroupSessionInDatabase(int gameId, int maxUsers, String description) {
        dLog.debug("Creating a group session: GameId - " + gameId + " maxUsers - " + maxUsers + " Description: " + description);
        Set<Tag> tags = new HashSet<>();
        Games shellGame = new Games(gameId, 0, "", "");
        return sessionDetailsRepository.save(
                new SessionDetails(0,shellGame,maxUsers,1,description,tags));
    }

    @Override
    public SessionResponse checkSessionStatus(JWTInfo parsedJWT, int groupId) {
        dLog.debug("Checking session status: " + groupId + " + userId: " + parsedJWT.getUserId());
        return createWaitingRoomResponse(getSessionForUser(parsedJWT, groupId));
    }

    private SessionResponse createWaitingRoomResponse(Session session) {
        if(session.isInsession()) return new SessionResponse.SessionResponseBuilder( getHostUserWithGroupId(session.getGroupsession().getGroupid()), session.getGroupsession())
                .success(session.isInsession())
                .groupMembers(getGroupMembersOfSession(session.getGroupsession().getGroupid()))
                .build();
        else return new SessionResponse.SessionResponseBuilder( getHostUserWithGroupId(session.getGroupsession().getGroupid()), session.getGroupsession())
                .success(session.isInsession())
                .build();
    }

    private SessionDetails getSessionDetailsByHostId(int hostid) {
        dLog.debug("Getting SessionDetails by Host Id: " + hostid);
        return sessionRepository.findFirst1ByHostid(hostid).getGroupsession();
    }

    private Session getSessionForUser(JWTInfo parsedJWT, int groupId) {
        return sessionRepository.findByUserIdAndGroupId(parsedJWT.getUserId(), groupId);
    }

    @Override
    public SessionResponse respondToUserSession(JWTInfo parsedJWT, WaitingRoomRequest roomRequest) {
        dLog.debug("Responding to user in session: " + roomRequest);
        int userRespondingId = loginRepository.findByUsername(roomRequest.getWaitingUsername()).getUserid();
        int groupId = roomRequest.getGroupId();
        Session session = sessionRepository.findByUserIdAndGroupId(userRespondingId, roomRequest.getGroupId());
        if(roomRequest.isSuccess()) {
            session.setInsession(true);
            sessionRepository.save(session);
        }else{
            sessionRepository.delete(session);
        }
        return new SessionResponse.SessionResponseBuilder(getHostUserWithGroupId(groupId), getSessionDetailsByGroupId(groupId))
                .success(roomRequest.isSuccess())
                .groupMembers(getGroupMembersOfSession(groupId))
                .waitingMembers(getWaitingMembersOfSession(groupId))
                .build();
    }

    @Override
    public boolean cancelSession(JWTInfo parsedJWT, CancelGroupRequest cancelGroup) {
        dLog.debug("Canceling group session: " + cancelGroup);
        try{
            sessionRepository.deleteAllByGroupId(cancelGroup.getGroupId());
            sessionDetailsRepository.delete(new SessionDetails(cancelGroup.getGroupId(), new Games(), 0, 0, "", new HashSet<>()));
            return true;
        }catch(Exception e){
            dLog.debug(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean leaveSession(JWTInfo parsedJWT, int groupId, int gameId) {
        dLog.debug("Attempting to leave Group Session: " + groupId);
        try{
            sessionRepository.deleteByUserIdAndGroupId(parsedJWT.getUserId(), groupId);
            return true;
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public SessionResponse getGroupSession(int groupId, int gameId, JWTInfo parsedJWT) {
        dLog.debug("Attempting to get Group Session: " + groupId);
        return new SessionResponse.SessionResponseBuilder(getHostUserWithGroupId(groupId), getSessionDetailsByGroupId(groupId))
                .groupMembers(getGroupMembersOfSession(groupId))
                .waitingMembers(getWaitingMembersOfSession(groupId))
                .build();
    }

    @Override
    public SessionResponse getGroupMembersResponse(JWTInfo parsedJWT, int groupId) {
        dLog.debug("Getting group members with group Id: " + groupId);
        return new SessionResponse.SessionResponseBuilder(getHostUserWithGroupId(groupId), getSessionDetailsByGroupId(groupId))
                .groupMembers(getGroupMembersOfSession(groupId))
                .build();
    }
}
