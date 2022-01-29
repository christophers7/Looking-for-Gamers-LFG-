package com.revature.p2_lfg.service.session.classes;

import com.revature.p2_lfg.presentation.models.session.CreateGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.CreatedGroupSessionResponse;
import com.revature.p2_lfg.presentation.models.session.JoinGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.JoinGroupSessionResponse;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDao;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDetailsDao;
import com.revature.p2_lfg.repository.entities.Games;
import com.revature.p2_lfg.repository.entities.Session;
import com.revature.p2_lfg.repository.entities.SessionDetails;
import com.revature.p2_lfg.repository.entities.Tag;
import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
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

    public CreatedGroupSessionResponse createGroupSession(CreateGroupSessionRequest createGroup, JWTInfo parsedJWT) {
        dLog.debug("Creating group session response from group create request: " + createGroup);
        SessionDetails sessionDetails = getSessionDetailsByGroupId(createGroupSessionInDatabase(createGroup.getGameId() , createGroup.getMaxUsers(), createGroup.getDescription()));

        if(sessionDetails != null) {
            iLog.info("New Group Session created and stored in database: " + sessionDetails);
            try{
                GroupSessionId sessionId = createUserSession(sessionDetails, parsedJWT);
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
    public JoinGroupSessionResponse joinGroupSession(JoinGroupSessionRequest joinSession, JWTInfo parsedJWT) {
        dLog.debug("Joining group session response from group join request: " + joinSession);
        SessionDetails sessionDetails = getSessionDetailsByGroupId(joinSession.getGroupId());
        if(sessionDetails != null){
            iLog.info("Joining group session: " + sessionDetails);
            Session session = new Session(parsedJWT.getUserId(), joinSession.getHostId(), sessionDetails, false);
            GroupSessionId sessionId = sessionDao.createUserSessionEntry(session);
            return new JoinGroupSessionResponse(
                    sessionId,
                    joinSession.getUsername(),
                    joinSession.getGameId(),
                    sessionDetails.getGroupId(),
                    joinSession.getHostId()
            );
        }
        return null;
    }

    private List<GroupUser> getGroupMembersOfSession(int groupId) {
        
    }

    private GroupSessionId createUserSession(SessionDetails sessionDetails, JWTInfo parsedJWT) {
        dLog.debug("Creating user Session for a group: " + sessionDetails);
        return sessionDao.createUserSessionEntry(
                new Session(
                        parsedJWT.getUserId(), parsedJWT.getUserId(), sessionDetails, true
                )
        );
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
                        1,
                        description,
                        tags));
    }

}
