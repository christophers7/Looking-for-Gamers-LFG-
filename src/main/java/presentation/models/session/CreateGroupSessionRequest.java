package presentation.models.session;

import java.util.Objects;

public class CreateGroupSessionRequest {
    private int gameId;
    private int maxUsers;
    private String description;

    public CreateGroupSessionRequest(int gameId, int maxUsers, String description) {
        this.gameId = gameId;
        this.maxUsers = maxUsers;
        this.description = description;
    }

    public CreateGroupSessionRequest() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateGroupSessionRequest)) return false;
        CreateGroupSessionRequest that = (CreateGroupSessionRequest) o;
        return gameId == that.gameId && maxUsers == that.maxUsers && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, maxUsers, description);
    }

    @Override
    public String toString() {
        return "{\"CreateGroupSessionRequest\":{"
                + "\"gameId\":\"" + gameId + "\""
                + ", \"maxUsers\":\"" + maxUsers + "\""
                + ", \"description\":\"" + description + "\""
                + "}}";
    }
}
