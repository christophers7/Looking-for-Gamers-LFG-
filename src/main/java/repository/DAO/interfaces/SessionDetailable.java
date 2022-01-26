package repository.DAO.interfaces;

import repository.entities.SessionDetails;

import java.util.List;

public interface SessionDetailable {
    List<SessionDetails> getSessionByGameId(int gameID);

    Integer createGroupSession(SessionDetails sessionDetails);

    SessionDetails getSessionDetails(SessionDetails sessionDetails);
}
