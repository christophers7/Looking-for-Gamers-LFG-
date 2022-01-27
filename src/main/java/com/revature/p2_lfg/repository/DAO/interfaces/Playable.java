package com.revature.p2_lfg.repository.DAO.interfaces;

import java.util.List;

import com.revature.p2_lfg.repository.entities.Games;
import com.revature.p2_lfg.repository.entities.Games;

public interface Playable {
	
	public void addGame(Games game);
	
	List<Games> findGamesByTitle(Games game);
	
	List<Games> findGamesByGenre(Games game);
	
	List<Games> findGamesByCurrentlyPlaying(Games game);
	
	List<Games> findAllGames();

}
