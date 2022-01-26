package service.session.interfaces;

import presentation.models.session.CreateGroupSessionRequest;
import presentation.models.session.CreatedGroupSessionResponse;
import utility.JWTInfo;

public interface SessionServiceable {

    CreatedGroupSessionResponse createGroupSession(CreateGroupSessionRequest createGroup, JWTInfo parsedJWT);
}
