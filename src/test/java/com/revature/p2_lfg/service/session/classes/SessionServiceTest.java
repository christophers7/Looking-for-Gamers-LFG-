package com.revature.p2_lfg.service.session.classes;

import com.revature.p2_lfg.presentation.models.session.JoinGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.JoinGroupSessionResponse;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDao;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDetailsDao;
import com.revature.p2_lfg.repository.entities.*;
import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import com.revature.p2_lfg.service.session.dto.GroupUser;
import com.revature.p2_lfg.utility.JWTInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.revature.p2_lfg.presentation.models.session.CreateGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.CreatedGroupSessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SessionServiceTest {

    @MockBean
    private SessionDetailsDao sessionDetailDao;

    @MockBean
    private SessionDao sessionDao;

    @Autowired
    private SessionService sessionService;


    private CreateGroupSessionRequest createGroupSessionRequest;
    private JWTInfo parsedJWT;
    private CreatedGroupSessionResponse createGroupSessionResponse;

    private List<GroupUser> groupMembers;

    private SessionDetails createdSessionDetails;
    private Games game;

    private Session storedSession;
    private JoinGroupSessionRequest joinGroupSessionRequest;
    private JoinGroupSessionResponse joinGroupSessionResponse;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        int gameId = 1;
        int maxUsers = 3;
        String description = "this is a description";
        Set<Tag> tags = new HashSet<>();

        game = new Games(
                1, "gameTitle", "link"
        );

        GroupUser user1 = new GroupUser(
                "user1", 1, new Socials(1, game.getGameID(), "gamer1"), "sick", true
        );

        GroupUser user2 = new GroupUser(
                "user2", 1, new Socials(2, game.getGameID(), "gamer2"), "dope", false
        );

        groupMembers = new ArrayList<>();
        groupMembers.add(user1);

        parsedJWT = new JWTInfo(
                "oldFirstName", "oldLastName", "username", 3
        );

        createGroupSessionRequest = new CreateGroupSessionRequest(
                gameId, maxUsers, description
        );

        createGroupSessionResponse = new CreatedGroupSessionResponse(
                1, gameId, maxUsers, description, groupMembers
        );

        createdSessionDetails = new SessionDetails(
                1, game, maxUsers, 1, description, tags
        );

        SessionDetails mockSessionDetail =  new SessionDetails(
                        0,
                        new Games(1, "", ""),
                        maxUsers,
                        1,
                        description,
                        tags);

        Session mockSession = new Session(
                parsedJWT.getUserId(), parsedJWT.getUserId(), createdSessionDetails, true
        );

        storedSession = new Session(
                1, 1, createdSessionDetails, true
        );

        SessionDetails sessionDetailJustForId = new SessionDetails(
                1, new Games(), 0, 0,  "", new HashSet<>()
        );

        GroupSessionId sessionId = new GroupSessionId(storedSession.getUserId(), storedSession.getHostId());

        joinGroupSessionRequest = new JoinGroupSessionRequest(
                createdSessionDetails.getGroupId(),
                storedSession.getHostId(),
                createdSessionDetails.getGame().getGameID(),
                user2.getUsername());

        GroupSessionId sessionId2 = new GroupSessionId(2, joinGroupSessionRequest.getHostId());

        joinGroupSessionResponse = new JoinGroupSessionResponse(
                sessionId2,
                user2.getUsername(),
                createdSessionDetails.getGame().getGameID(),
                createdSessionDetails.getGroupId(),
                storedSession.getHostId()
        );

        JWTInfo parsedJWT2 = new JWTInfo(
                "name", "name", "user2", 2
        );

        Mockito.when(sessionDetailDao.createGroupSession(mockSessionDetail)).thenReturn(createdSessionDetails.getGroupId());
        Mockito.when(sessionDetailDao.getSessionDetails(sessionDetailJustForId)).thenReturn(createdSessionDetails);
        Mockito.when(sessionDao.createUserSessionEntry(mockSession)).thenReturn(sessionId);
        Mockito.when(sessionDao.getGroupMembersByGroupId(createdSessionDetails.getGroupId())).thenReturn(groupMembers);
        Mockito.when(sessionDao.createUserSessionEntry(new Session(parsedJWT2.getUserId(), joinGroupSessionRequest.getHostId(), createdSessionDetails, false))).thenReturn(sessionId2);
    }

    @Test
    void createGroupSession() {
        assertEquals(createGroupSessionResponse, sessionService.createGroupSession(createGroupSessionRequest, parsedJWT));
    }

    @Test
    void joinGroupSession() {
        assertEquals(joinGroupSessionResponse, sessionService.joinGroupSession(joinGroupSessionRequest, parsedJWT));
    }
}