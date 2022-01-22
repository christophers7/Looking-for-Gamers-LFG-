package repository.DAO.interfaces;

import java.util.List;

import repository.entities.Games;

public interface Playable {
	
	public void addGame(Games game);
	
	List<Games> findGamesByTitle(Games game);
	
	List<Games> findGamesByGenre(Games game);
	
	List<Games> findGamesByCurrentlyPlaying(Games game);

}
