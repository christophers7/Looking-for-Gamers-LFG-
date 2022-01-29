package com.revature.p2_lfg.service.session.interfaces;

import com.revature.p2_lfg.presentation.models.session.*;
import com.revature.p2_lfg.service.session.MaxUsersException;
import com.revature.p2_lfg.utility.JWTInfo;

public interface SessionServiceable {

    CreatedGroupSessionResponse createGroupSession(CreateGroupSessionRequest createGroup, JWTInfo parsedJWT);

    JoinGroupSessionResponse joinGroupSession(JWTInfo parsedJWT, int groupId, int gameId) throws MaxUsersException;

    CheckWaitingRoomResponse checkSessionStatus(JWTInfo parsedJWT, int groupId);

    WaitingRoomResponse respondToUserSession(JWTInfo parsedJWT, WaitingRoomRequest roomRequest);

    CancelGroupResponse cancelSession(JWTInfo parsedJWT, CancelGroupRequest cancelGroup);

    LeaveGroupResponse leaveSession(JWTInfo parsedJWT, int groupId, int gameId);
}
