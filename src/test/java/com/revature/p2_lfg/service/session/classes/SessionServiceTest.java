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
    private SessionDetailsRepository sessionDetailsRepository;

    @MockBean
    private SessionRepository sessionRepository;

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
    private LoginRepository loginRepository;

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
                "user1", 1, true
        );

        GroupUser user2 = new GroupUser(
                "user2", 1, false
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

        GroupSessionId sessionId = new GroupSessionId(storedSession.getUserid(), storedSession.getHostid());

        joinGroupSessionRequest = new JoinGroupSessionRequest(
                createdSessionDetails.getGroupid(),
                createdSessionDetails.getGame().getGameid(),
                user2.getUsername());

        GroupSessionId sessionId2 = new GroupSessionId(2, storedSession.getHostid());

        joinGroupSessionResponse = new JoinGroupSessionResponse(
                sessionId2,
                createdSessionDetails.getGame().getGameid(),
                createdSessionDetails.getGroupid()
        );

        parsedJWT2 = new JWTInfo(
                "name", "name", "user2", 2
        );

        user2Session = new Session(
                2, 1, createdSessionDetails, false
        );

        checkWaitingRoomResponse = new CheckWaitingRoomResponse(
                user2Session.isInsession(),
                1,
                mockSessionDetail.getGame().getGameid()
        );

        roomRequestSuccess = new WaitingRoomRequest(
                createdSessionDetails.getGroupid(),
                createdSessionDetails.getGame().getGameid(),
                parsedJWT2.getUsername(),
                true
        );
        roomRequestReject = new WaitingRoomRequest(
                createdSessionDetails.getGroupid(),
                createdSessionDetails.getGame().getGameid(),
                parsedJWT2.getUsername(),
                false
        );

        successWaitingRoomResponse = new WaitingRoomResponse(
                true,
               new GroupUser("user2", 1, true)
        );

        rejectWaitingRoomResponse = new WaitingRoomResponse(
                false,
                new GroupUser()
        );

        cancelGroupRequest = new CancelGroupRequest(createdSessionDetails.getGroupid(), createdSessionDetails.getGame().getGameid(), Collections.singletonList(user2));

        cancelGroupResponse = new CancelGroupResponse(true);

        leaveGroupResponse = new LeaveGroupResponse(true);

        List<Session> groupMembersSession = new ArrayList<>();
        groupMembersSession.add(storedSession);

        Session session2 = new Session(2, sessionId.getHostid(), createdSessionDetails, false);

        Mockito.when(sessionDetailsRepository.save(mockSessionDetail)).thenReturn(createdSessionDetails);
        Mockito.when(sessionDetailsRepository.findById(sessionDetailJustForId.getGroupid())).thenReturn(Optional.ofNullable(createdSessionDetails));
        Mockito.when(sessionRepository.save(mockSession)).thenReturn(storedSession);
        Mockito.when(sessionRepository.findAllByGroupId(createdSessionDetails.getGroupid())).thenReturn(groupMembersSession);
        Mockito.when(sessionRepository.findFirst1HostidByGroupsession(new SessionDetails(joinGroupSessionRequest.getGroupId(), new Games(), 0, 0, "", new HashSet<>()))).thenReturn(sessionId.getHostid());
        Mockito.when(sessionRepository.save(new Session(parsedJWT2.getUserId(), sessionId.getHostid(), createdSessionDetails, false))).thenReturn(session2);
        Mockito.when(sessionRepository.findByUserIdAndGroupId(parsedJWT2.getUserId(), createdSessionDetails.getGroupid())).thenReturn(user2Session);
        Mockito.when(loginRepository.findByUsername("user2")).thenReturn(new UserCredential(2, "user2", "pass2"));
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