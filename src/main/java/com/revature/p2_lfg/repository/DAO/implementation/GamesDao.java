package com.revature.p2_lfg.repository.DAO.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import com.revature.p2_lfg.repository.DAO.interfaces.Playable;
import com.revature.p2_lfg.repository.DAO.util.HibernateSessionFactory;
import com.revature.p2_lfg.repository.entities.Games;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.p2_lfg.repository.DAO.interfaces.Playable;
import com.revature.p2_lfg.repository.DAO.util.HibernateSessionFactory;
import com.revature.p2_lfg.repository.entities.Games;

public class GamesDao implements Playable {
	
	private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");


	@Override
	public void addGame(Games game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Games> findGamesByTitle(Games game) {
		List<Games> games = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			dLog.debug("Attempting to Find Game in Database: " + game);
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<Games> cq = cb.createQuery(Games.class);
//			Root<Games> root = cq.from(Games.class);
//			cq.select(root).where(cb.equal(root.get("gameTitle"), game.getGameTitle()));
//			Query<Games> query = session.createQuery(cq);
//			games = query.getResultList();
			games = session.createQuery("FROM Games g WHERE g.gameTitle = :games", Games.class)
					.setParameter("games", game.getGameTitle()).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			if(transaction != null)
                if(!transaction.isActive()) transaction.rollback();
            dLog.error(e.getMessage(), e);
		}
		return games;
	}

	@Override
	public List<Games> findGamesByGenre(Games game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Games> findGamesByCurrentlyPlaying(Games game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Games> findAllGames() {
		List<Games> games = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			dLog.debug("Searching for All Games in Database: ");
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			games = session.createQuery("FROM Games", Games.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			if(transaction != null)
                if(!transaction.isActive()) transaction.rollback();
            dLog.error(e.getMessage(), e);
		
	}
		return games;
	}

}
