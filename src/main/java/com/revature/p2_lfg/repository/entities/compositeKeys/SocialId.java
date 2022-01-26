package repository.entities.compositeKeys;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

public class SocialId implements Serializable {
    private int userID;
    private int gameID;

    public SocialId() {
    }

    public SocialId(int userID, int gameID) {
        this.userID = userID;
        this.gameID = gameID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocialId)) return false;
        SocialId socialId = (SocialId) o;
        return userID == socialId.userID && gameID == socialId.gameID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, gameID);
    }

    @Override
    public String toString() {
        return "{\"SocialId\":{"
                + "\"userID\":\"" + userID + "\""
                + ", \"gameID\":\"" + gameID + "\""
                + "}}";
    }
}
