package presentation.models.games;

import service.game.dto.GameSelectInfo;

import java.util.List;
import java.util.Objects;

public class GameSessionInfoResponse {
    private List<GameSelectInfo> gameSessionList;

    public GameSessionInfoResponse(List<GameSelectInfo> gameSessionList) {
        this.gameSessionList = gameSessionList;
    }

    public GameSessionInfoResponse() {
    }

    public List<GameSelectInfo> getGameSessionList() {
        return gameSessionList;
    }

    public void setGameSessionList(List<GameSelectInfo> gameSessionList) {
        this.gameSessionList = gameSessionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameSessionInfoResponse)) return false;
        GameSessionInfoResponse that = (GameSessionInfoResponse) o;
        return Objects.equals(getGameSessionList(), that.getGameSessionList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameSessionList);
    }

    @Override
    public String toString() {
        return "{\"GameSessionInfoResponse\":{"
                + "\"GameSessionList\":" + gameSessionList
                + "}}";
    }
}
