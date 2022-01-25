package repository.DAO.interfaces;

import repository.entities.SessionDetails;

import java.util.List;

public interface SessionDetailable {
    List<SessionDetails> getSessionByGameId(int gameID);
}
