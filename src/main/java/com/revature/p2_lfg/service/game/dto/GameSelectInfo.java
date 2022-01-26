package service.game.dto;

import java.util.Objects;

public class GameSelectInfo {

    private int gameId;
    private String name;
    private String imgLink;
    private int sessions;

    public GameSelectInfo(int gameId, String name, String imgLink, int sessions) {
        this.gameId = gameId;
        this.name = name;
        this.imgLink = imgLink;
        this.sessions = sessions;
    }

    public GameSelectInfo() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public int getSessions() {
        return sessions;
    }

    public void setSessions(int sessions) {
        this.sessions = sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameSelectInfo)) return false;
        GameSelectInfo that = (GameSelectInfo) o;
        return gameId == that.gameId && sessions == that.sessions && Objects.equals(name, that.name) && Objects.equals(imgLink, that.imgLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, name, imgLink, sessions);
    }

    @Override
    public String toString() {
        return "{\"GameSelectInfo\":{"
                + "\"gameId\":\"" + gameId + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"imgLink\":\"" + imgLink + "\""
                + ", \"sessions\":\"" + sessions + "\""
                + "}}";
    }
}
