package com.revature.p2_lfg.repository.DAO.implementation;

import com.revature.p2_lfg.repository.DAO.interfaces.SessionDetailable;
import com.revature.p2_lfg.repository.entities.SessionDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

public class SessionDetailsDao implements SessionDetailable {
    @Override
    public List<SessionDetails> getSessionByGameId(int gameID) {
        return null;
    }

    @Override
    public Integer createGroupSession(SessionDetails sessionDetails) {
        return null;
    }

    @Override
    public SessionDetails getSessionDetails(SessionDetails sessionDetails) {
        return null;
    }
}
