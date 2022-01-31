package com.revature.p2_lfg.service.session.validation;

import com.revature.p2_lfg.presentation.models.session.SessionResponse;
import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import com.revature.p2_lfg.service.session.dto.GroupUser;
import com.revature.p2_lfg.service.session.exception.InvalidHostUserException;
import com.revature.p2_lfg.service.session.exception.InvalidSessionDetailsException;

public class SessionResponseValidation {

    public static void validateSessionResponse(SessionResponse response) throws InvalidHostUserException, InvalidSessionDetailsException {
        if(response.getHostUser() == null) throw new InvalidHostUserException();
        if(response.getSessionDetails() == null) throw new InvalidSessionDetailsException();
    }
}
