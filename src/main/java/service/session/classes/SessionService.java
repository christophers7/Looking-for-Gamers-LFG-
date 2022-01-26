package service.session.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.session.CreateGroupSessionRequest;
import presentation.models.session.CreatedGroupSessionResponse;
import repository.DAO.implementation.SessionDao;
import repository.DAO.implementation.SessionDetailsDao;
import repository.entities.Games;
import repository.entities.Session;
import repository.entities.SessionDetails;
import repository.entities.Tag;
import repository.entities.compositeKeys.GroupSessionId;
import service.session.dto.GroupUser;
import service.session.interfaces.SessionServiceable;
import utility.JWTInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SessionService implements SessionServiceable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private final SessionDetailsDao sessionDetailsDao;
    private final SessionDao sessionDao;

    public SessionService(SessionDetailsDao sessionDetailsDao, SessionDao sessionDao) {
        this.sessionDetailsDao = sessionDetailsDao;
        this.sessionDao = sessionDao;
    }


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

    private List<GroupUser> getGroupMembersOfSession(int groupId) {
        dLog.debug("Getting group users associated by group Id: " + groupId);
        return sessionDao.getGroupMembersByGroupId(groupId);
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
