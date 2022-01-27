package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.repository.entities.SessionDetails;

import java.util.List;

public interface SessionDetailable {
    List<SessionDetails> getSessionByGameId(int gameID);

    Integer createGroupSession(SessionDetails sessionDetails);

    SessionDetails getSessionDetails(SessionDetails sessionDetails);
}
