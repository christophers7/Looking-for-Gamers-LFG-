package repository.dao.implementation;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import repository.DAO.implementation.GamesDao;
import repository.entities.Games;

public class GamesDaoTest {
	
	GamesDao gamesDao;
	
	@BeforeEach
	void setup(){
		gamesDao = new GamesDao();
	}
	
	@Test
	void testFindAllGames() {
		List<Games> retrievedGames = gamesDao.findAllGames();
		
		Assertions.assertNotNull(retrievedGames);
	}
	
	@Test
	void testFindGameByTitle() {
		
	}

}
