package com.revature.p2_lfg.service.session.interfaces;

import com.revature.p2_lfg.presentation.models.session.CreateGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.CreatedGroupSessionResponse;
import com.revature.p2_lfg.utility.JWTInfo;

public interface SessionServiceable {

    CreatedGroupSessionResponse createGroupSession(CreateGroupSessionRequest createGroup, JWTInfo parsedJWT);
}
