package com.revature.p2_lfg.service.game.classes;

import com.revature.p2_lfg.presentation.models.games.GameSessionInfoResponse;
import com.revature.p2_lfg.presentation.models.games.SelectedGameAvailableGroupsResponse;
import com.revature.p2_lfg.repository.DAO.implementation.GamesDao;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDetailsDao;
import com.revature.p2_lfg.repository.entities.Games;
import com.revature.p2_lfg.repository.entities.SessionDetails;
import com.revature.p2_lfg.service.game.dto.GameSelectInfo;
import com.revature.p2_lfg.service.game.interfaces.GameServiceable;

import java.util.ArrayList;
import java.util.List;

public class GameService implements GameServiceable {

    private GamesDao gameDao;
    private SessionDetailsDao sessionDetailsDao;

    public GameService(GamesDao gameDao, SessionDetailsDao sessionDetailsDao) {
        this.gameDao = gameDao;
        this.sessionDetailsDao = sessionDetailsDao;
    }

    public GameSessionInfoResponse getCurrentGameSessionList() {
        return new GameSessionInfoResponse(getGameSelectInfoList(getGames()));
    }

    @Override
    public SelectedGameAvailableGroupsResponse getSelectedGameGroups(int gameId) {
        return new SelectedGameAvailableGroupsResponse(gameId, sessionDetailsDao.getSessionByGameId(gameId));
    }

    public List<Games> getGames(){
        return gameDao.findAllGames();
    }

    public List<GameSelectInfo> getGameSelectInfoList(List<Games> gamesList){
        List<GameSelectInfo> gameSelectInfoList = new ArrayList<>(gamesList.size());
        for (Games games : gamesList) {
            List<SessionDetails> sessionForGame = sessionDetailsDao.getSessionByGameId(games.getGameID());
            gameSelectInfoList.add(new GameSelectInfo(games.getGameID(), games.getGameTitle(), games.getImgLink(), sessionForGame.size()));
        }
        return gameSelectInfoList;
    }
}
