package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.repository.DAO.implementation.SessionDetailsDao;
import com.revature.p2_lfg.repository.entities.SessionDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SessionDetailableJPA extends CrudRepository<SessionDetailsDao,Integer> {
    List<SessionDetailsDao> findBygameid(SessionDetailsDao sessionDetailsDao);
    <S extends SessionDetails> S save(SessionDetails sessionDetails);
}
