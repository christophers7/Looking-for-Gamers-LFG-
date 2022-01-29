package com.revature.p2_lfg.service.session.classes;

import com.revature.p2_lfg.presentation.models.session.*;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDao;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDetailsDao;
import com.revature.p2_lfg.repository.DAO.implementation.UserCredentialsDao;
import com.revature.p2_lfg.repository.entities.*;
import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import com.revature.p2_lfg.service.session.dto.GroupUser;
import com.revature.p2_lfg.utility.JWTInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

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

    private JWTInfo parsedJWT2;
    private CheckWaitingRoomResponse checkWaitingRoomResponse;
    private Session user2Session;
    private WaitingRoomRequest roomRequestSuccess;
    private WaitingRoomRequest roomRequestReject;

    private WaitingRoomResponse successWaitingRoomResponse;
    private WaitingRoomResponse rejectWaitingRoomResponse;
    @MockBean
    private UserCredentialsDao userCredentialsDao;

    private CancelGroupRequest cancelGroupRequest;
    private CancelGroupResponse cancelGroupResponse;
    private LeaveGroupResponse leaveGroupResponse;

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
                "user1", 1, "", true
        );

        GroupUser user2 = new GroupUser(
                "user2", 1, "", false
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
                createdSessionDetails.getGame().getGameID(),
                user2.getUsername());

        GroupSessionId sessionId2 = new GroupSessionId(2, storedSession.getHostId());

        joinGroupSessionResponse = new JoinGroupSessionResponse(
                sessionId2,
                createdSessionDetails.getGame().getGameID(),
                createdSessionDetails.getGroupId()
        );

        parsedJWT2 = new JWTInfo(
                "name", "name", "user2", 2
        );

        user2Session = new Session(
                2, 1, createdSessionDetails, false
        );

        checkWaitingRoomResponse = new CheckWaitingRoomResponse(
                user2Session.isInSession(),
                1,
                mockSessionDetail.getGame().getGameID()
        );

        roomRequestSuccess = new WaitingRoomRequest(
                createdSessionDetails.getGroupId(),
                createdSessionDetails.getGame().getGameID(),
                parsedJWT2.getUsername(),
                true
        );
        roomRequestReject = new WaitingRoomRequest(
                createdSessionDetails.getGroupId(),
                createdSessionDetails.getGame().getGameID(),
                parsedJWT2.getUsername(),
                false
        );

        successWaitingRoomResponse = new WaitingRoomResponse(
                true,
               new GroupUser("user2", 1, "", true)
        );

        rejectWaitingRoomResponse = new WaitingRoomResponse(
                false,
                new GroupUser()
        );

        cancelGroupRequest = new CancelGroupRequest(createdSessionDetails.getGroupId(), createdSessionDetails.getGame().getGameID(), Collections.singletonList(user2));

        cancelGroupResponse = new CancelGroupResponse(true);

        leaveGroupResponse = new LeaveGroupResponse(true);
        Mockito.when(sessionDetailDao.createGroupSession(mockSessionDetail)).thenReturn(createdSessionDetails.getGroupId());
        Mockito.when(sessionDetailDao.getSessionDetails(sessionDetailJustForId)).thenReturn(createdSessionDetails);
        Mockito.when(sessionDao.createUserSessionEntry(mockSession)).thenReturn(sessionId);
        Mockito.when(sessionDao.getGroupMembersByGroupId(createdSessionDetails.getGroupId())).thenReturn(groupMembers);
        Mockito.when(sessionDao.getHostId(joinGroupSessionRequest.getGroupId())).thenReturn(sessionId.getHostId());
        Mockito.when(sessionDao.createUserSessionEntry(new Session(parsedJWT2.getUserId(), sessionId.getHostId(), createdSessionDetails, false))).thenReturn(sessionId2);
        Mockito.when(sessionDao.getUserSession(parsedJWT2.getUserId(), createdSessionDetails.getGroupId())).thenReturn(user2Session);
        Mockito.when(userCredentialsDao.getUserWithUsername("user2")).thenReturn(new UserCredential(2, "user2", "pass2"));
    }

    @Test
    void createGroupSessionTest() {
        assertEquals(createGroupSessionResponse, sessionService.createGroupSession(createGroupSessionRequest, parsedJWT));
    }

    @Test
    void joinGroupSessionTest() {
        assertEquals(joinGroupSessionResponse, sessionService.joinGroupSession(parsedJWT2, 1, 1));
    }

    @Test
    void checkSessionStatusTest() {
        assertEquals(checkWaitingRoomResponse, sessionService.checkSessionStatus(parsedJWT2, 1));
    }

    @Test
    void respondToUserSessionSuccessTest() {
        assertEquals(successWaitingRoomResponse, sessionService.respondToUserSession(parsedJWT, roomRequestSuccess));
    }

    @Test
    void respondToUserSessionRejectTest(){
        assertEquals(rejectWaitingRoomResponse, sessionService.respondToUserSession(parsedJWT, roomRequestReject));
    }

    @Test
    void cancelSessionSuccessTest() {
        assertEquals(cancelGroupResponse, sessionService.cancelSession(parsedJWT, cancelGroupRequest));
    }

    @Test
    void leaveSession() {
        assertEquals(leaveGroupResponse, sessionService.leaveSession(parsedJWT2, 1, 1));
    }
}