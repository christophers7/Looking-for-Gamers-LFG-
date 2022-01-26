package presentation.models.games;

import repository.entities.SessionDetails;

import java.util.List;
import java.util.Objects;

public class SelectedGameAvailableGroupsResponse {
    private int gameId;
    private List<SessionDetails> selectedGameAvailableGroups;

    public SelectedGameAvailableGroupsResponse() {
    }

    public SelectedGameAvailableGroupsResponse(int gameId, List<SessionDetails> selectedGameAvailableGroups) {
        this.gameId = gameId;
        this.selectedGameAvailableGroups = selectedGameAvailableGroups;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public List<SessionDetails> getSelectedGameAvailableGroups() {
        return selectedGameAvailableGroups;
    }

    public void setSelectedGameAvailableGroups(List<SessionDetails> selectedGameAvailableGroups) {
        this.selectedGameAvailableGroups = selectedGameAvailableGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectedGameAvailableGroupsResponse)) return false;
        SelectedGameAvailableGroupsResponse that = (SelectedGameAvailableGroupsResponse) o;
        return gameId == that.gameId && Objects.equals(selectedGameAvailableGroups, that.selectedGameAvailableGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, selectedGameAvailableGroups);
    }

    @Override
    public String toString() {
        return "{\"SelectedGameAvailableGroupsResponse\":{"
                + "\"gameId\":\"" + gameId + "\""
                + ", \"selectedGameAvailableGroups\":" + selectedGameAvailableGroups
                + "}}";
    }
}
