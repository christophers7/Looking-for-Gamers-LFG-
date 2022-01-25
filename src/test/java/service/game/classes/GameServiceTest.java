package service.game.classes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import presentation.models.games.GameSessionInfoResponse;
import presentation.models.games.SelectedGameAvailableGroupsResponse;
import repository.DAO.implementation.GamesDao;
import repository.DAO.implementation.SessionDetailsDao;
import repository.entities.Games;
import repository.entities.SessionDetails;
import repository.entities.Tag;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameServiceTest {

    @Mock
    private GamesDao gameDao;

    @Mock
    private SessionDetailsDao sessionDetailsDao;

    private GameService gameService;
    private GameSessionInfoResponse gameSessionInfoResponse;

    private SelectedGameAvailableGroupsResponse selectedGameAvailableGroupsResponse;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Games game1 = new Games(1, "gameName", "link");
        Games game2 = new Games(2, "gameName2", "link");

        GameSelectInfo game1Info = new GameSelectInfo(game1.getGameID(), game1.getGameTitle(), game1.getImgLink(), 2);
        GameSelectInfo game2Info = new GameSelectInfo(game2.getGameID(),game2.getGameTitle(), game2.getImgLink(), 2);
        Set<Tag> tags1 = new HashSet<>();
        Set<Tag> tags2 = new HashSet<>();

        SessionDetails session1 = new SessionDetails(1, game1, 4, 2, "hello", tags1);
        SessionDetails session2 = new SessionDetails(2, game2, 4, 2, "hi", tags2);
        SessionDetails session3 = new SessionDetails(3, game1, 4, 2, "hi", tags1);
        SessionDetails session4 = new SessionDetails(4, game2, 4, 2, "hi", tags2);

        List<SessionDetails> game1Sessions = new ArrayList<>();
        List<SessionDetails> game2Sessions = new ArrayList<>();
        game1Sessions.add(session1);
        game1Sessions.add(session3);
        game2Sessions.add(session2);
        game2Sessions.add(session4);

        List<GameSelectInfo> gameSessionList = new ArrayList<>();
        gameSessionList.add(game1Info);
        gameSessionList.add(game2Info);
        gameSessionInfoResponse = new GameSessionInfoResponse(
                gameSessionList
        );

        selectedGameAvailableGroupsResponse = new SelectedGameAvailableGroupsResponse(1, game1Sessions);
        Mockito.when(gameDao.findAllGames()).thenReturn(Arrays.asList(game1, game2));
        Mockito.when(sessionDetailsDao.getSessionByGameId(1)).thenReturn(game1Sessions);
        Mockito.when(sessionDetailsDao.getSessionByGameId(2)).thenReturn(game2Sessions);


        gameService = new GameService(gameDao, sessionDetailsDao);
    }

    @Test
    void getCurrentGameSessionList() {
        assertEquals(gameSessionInfoResponse, gameService.getCurrentGameSessionList());
    }

    @Test
    void getSelectedGameGroups() {
        assertEquals(selectedGameAvailableGroupsResponse, gameService.getSelectedGameGroups(1));
    }
}