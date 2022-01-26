package service.game.interfaces;

import presentation.models.games.GameSessionInfoResponse;
import presentation.models.games.SelectedGameAvailableGroupsResponse;

public interface GameServiceable {

    GameSessionInfoResponse getCurrentGameSessionList();

    SelectedGameAvailableGroupsResponse getSelectedGameGroups(int gameId);
}
