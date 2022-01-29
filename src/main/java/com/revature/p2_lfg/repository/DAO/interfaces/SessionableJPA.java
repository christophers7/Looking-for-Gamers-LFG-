package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("sessionJPA")
public interface SessionableJPA extends CrudRepository<Sessionable,Integer> {

}
