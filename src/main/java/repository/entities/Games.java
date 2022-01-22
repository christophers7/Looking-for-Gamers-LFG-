package repository.entities;

import java.util.Objects;

public class Games {
    private int gameID;
    private String gameTitle;
    private String imgLink;

    public Games() {
    }

    public Games(int gameID, String gameTitle, String imgLink) {
        this.gameID = gameID;
        this.gameTitle = gameTitle;
        this.imgLink = imgLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Games)) return false;
        Games games = (Games) o;
        return getGameID() == games.getGameID() && Objects.equals(getGameTitle(), games.getGameTitle()) && Objects.equals(getImgLink(), games.getImgLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameID(), getGameTitle(), getImgLink());
    }

    @Override
    public String toString() {
        return "{\"Games\":{"
                + "\"gameID\":\"" + gameID + "\""
                + ", \"gameTitle\":\"" + gameTitle + "\""
                + ", \"imgLink\":\"" + imgLink + "\""
                + "}}";
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
