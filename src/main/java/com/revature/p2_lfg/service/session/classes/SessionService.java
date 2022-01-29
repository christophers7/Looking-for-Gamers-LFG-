package com.revature.p2_lfg.service.session.classes;

import com.revature.p2_lfg.presentation.models.session.*;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDao;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDetailsDao;
import com.revature.p2_lfg.repository.DAO.implementation.UserCredentialsDao;
import com.revature.p2_lfg.repository.entities.*;
import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import com.revature.p2_lfg.service.session.MaxUsersException;
import com.revature.p2_lfg.service.session.interfaces.SessionServiceable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.p2_lfg.utility.JWTInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.p2_lfg.service.session.dto.GroupUser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("sessionService")
    public class SessionService implements SessionServiceable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private SessionDetailsDao sessionDetailsDao;
    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private UserCredentialsDao userCredentialsDao;

    public CreatedGroupSessionResponse createGroupSession(CreateGroupSessionRequest createGroup, JWTInfo parsedJWT) {
        dLog.debug("Creating group session response from group create request: " + createGroup);
        SessionDetails sessionDetails = getSessionDetailsByGroupId(createGroupSessionInDatabase(createGroup.getGameId() , createGroup.getMaxUsers(), createGroup.getDescription()));

        if(sessionDetails != null) {
            iLog.info("New Group Session created and stored in database: " + sessionDetails);
            try{
                GroupSessionId sessionId = createUserSession(sessionDetails, parsedJWT, parsedJWT.getUserId(), true);
                if(sessionId != null) {
                    iLog.info("Successful entry of a user to a session: " + sessionId);
                    return new CreatedGroupSessionResponse(
                            sessionDetails.getGroupId(),
                            sessionDetails.getGame().getGameID(),
                            sessionDetails.getMaxUsers(),
                            sessionDetails.getDescription(),
                            getGroupMembersOfSession(sessionDetails.getGroupId())
                    );
                }
            }catch(Exception e){
                dLog.error(e.getMessage(), e);
            }
        }
        dLog.error("Failed to create group session");
        return null;
    }

    @Override
    public JoinGroupSessionResponse joinGroupSession(JWTInfo parsedJWT, int groupId, int gameId) throws MaxUsersException {
        dLog.debug("Joining group session response from group join request: " + groupId);
        return enterWaitingRoom(groupId, parsedJWT);
    }

    private JoinGroupSessionResponse enterWaitingRoom(int groupId, JWTInfo parsedJWT) throws MaxUsersException {
        SessionDetails sessionDetails = getSessionDetailsByGroupId(groupId);
        iLog.info("Joining group session: " + sessionDetails);
        GroupSessionId sessionId = createUserSession(sessionDetails, parsedJWT, sessionDao.getHostId(groupId),  false);
        return new JoinGroupSessionResponse(
                sessionId,
                sessionDetails.getGame().getGameID(),
                sessionDetails.getGroupId()
        );
    }

    private int getHostId(int groupId) {
        return sessionDao.getHostId(groupId);
    }

    private List<GroupUser> getGroupMembersOfSession(int groupId) {
        dLog.debug("Getting group users associated by group Id: " + groupId);
        return sessionDao.getGroupMembersByGroupId(groupId);
    }

    private GroupSessionId createUserSession(SessionDetails sessionDetails, JWTInfo parsedJWT, int hostId, boolean status) throws MaxUsersException {
        dLog.debug("Creating user Session for a group: " + sessionDetails);
        if(sessionDetails.getCurrentUsers() < sessionDetails.getMaxUsers()) {
            sessionDetails.setCurrentUsers(sessionDetails.getCurrentUsers() + 1);
            return sessionDao.createUserSessionEntry(
                    new Session(
                            parsedJWT.getUserId(), hostId, sessionDetails, status
                    )
            );
        }
        else throw new MaxUsersException();
    }

    private SessionDetails getSessionDetailsByGroupId(int groupId) {
        dLog.debug("Getting session details by group ID: " + groupId);
        return sessionDetailsDao.getSessionDetails(
                new SessionDetails(
                        1,
                        new Games(),
                        0,
                        0,
                        "",
                        new HashSet<>()
                )
        );
    }

    private int createGroupSessionInDatabase(int gameId, int maxUsers, String description) {
        dLog.debug("Creating a group session: GameId - " + gameId + " maxUsers - " + maxUsers + " Description: " + description);
        Set<Tag> tags = new HashSet<>();
        Games shellGame = new Games(gameId, "", "");
       return sessionDetailsDao.createGroupSession(
                new SessionDetails(
                        0,
                        shellGame,
                        maxUsers,
                        0,
                        description,
                        tags));
    }

    @Override
    public CheckWaitingRoomResponse checkSessionStatus(JWTInfo parsedJWT, int groupId) {
        dLog.debug("Checking session status: " + groupId + " + userId: " + parsedJWT.getUserId());
        return createWaitingRoomResponse(getSessionForUser(parsedJWT, groupId));
    }

    private CheckWaitingRoomResponse createWaitingRoomResponse(Session session) {
        return new CheckWaitingRoomResponse(
                session.isInSession(),
                session.getGroupSession().getGame().getGameID(),
                session.getGroupSession().getGroupId()
        );
    }

    private Session getSessionForUser(JWTInfo parsedJWT, int groupId) {
        return sessionDao.getUserSession(parsedJWT.getUserId(), groupId);
    }

    @Override
    public WaitingRoomResponse respondToUserSession(JWTInfo parsedJWT, WaitingRoomRequest roomRequest) {
        dLog.debug("Responding to user in session: " + roomRequest);
        int userRespondingId = userCredentialsDao.getUserWithUsername(roomRequest.getWaitingUsername()).getUserID();
        Session session = sessionDao.getUserSession(userRespondingId, roomRequest.getGroupId());
        if(roomRequest.isSuccess()) {
            session.setInSession(true);
            sessionDao.save(session);
            return new WaitingRoomResponse(
                    true,
                    new GroupUser(roomRequest.getWaitingUsername(), roomRequest.getGroupId(), new Socials(), "", true )
            );
        }else{
            sessionDao.delete(session);
            return new WaitingRoomResponse(
              false, new GroupUser()
            );
        }
    }

    @Override
    public CancelGroupResponse cancelSession(JWTInfo parsedJWT, CancelGroupRequest cancelGroup) {
        dLog.debug("Canceling group session: " + cancelGroup);
        return null;
    }
}
