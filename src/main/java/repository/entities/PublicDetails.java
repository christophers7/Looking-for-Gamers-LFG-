package repository.entities;

import java.util.Objects;

public class PublicDetails {

    private int columnID;
    private UserCredential userID;
    private Games gameID;
    private String gamerTag;

    public PublicDetails() {
    }

    public PublicDetails(int columnID, UserCredential userID, Games gameID, String gamerTag) {
        this.columnID = columnID;
        this.userID = userID;
        this.gameID = gameID;
        this.gamerTag = gamerTag;
    }

    public int getColumnID() {
        return columnID;
    }

    public void setColumnID(int columnID) {
        this.columnID = columnID;
    }

    public UserCredential getUserID() {
        return userID;
    }

    public void setUserID(UserCredential userID) {
        this.userID = userID;
    }

    public Games getGameID() {
        return gameID;
    }

    public void setGameID(Games gameID) {
        this.gameID = gameID;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicDetails)) return false;
        PublicDetails that = (PublicDetails) o;
        return columnID == that.columnID && Objects.equals(userID, that.userID) && Objects.equals(gameID, that.gameID) && Objects.equals(gamerTag, that.gamerTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnID, userID, gameID, gamerTag);
    }

    @Override
    public String toString() {
        return "{\"PublicDetails\":{"
                + "\"columnID\":\"" + columnID + "\""
                + ", \"userID\":" + userID
                + ", \"gameID\":" + gameID
                + ", \"gamerTag\":\"" + gamerTag + "\""
                + "}}";
    }
}
