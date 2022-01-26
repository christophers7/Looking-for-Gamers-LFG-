package repository.DAO.implementation;

import repository.DAO.interfaces.SessionDetailable;
import repository.entities.SessionDetails;

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
