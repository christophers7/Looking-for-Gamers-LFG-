package repository.dao.implementation;

import java.util.List;

import com.revature.p2_lfg.repository.DAO.implementation.GamesDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.revature.p2_lfg.repository.DAO.implementation.GamesDao;
import com.revature.p2_lfg.repository.entities.Games;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GamesDaoTest {
	
	private GamesDao gamesDao;
	
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
