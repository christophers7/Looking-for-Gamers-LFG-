package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.repository.DAO.implementation.UserProfileDao;
import com.revature.p2_lfg.repository.entities.UserCredential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("loginableJPA")
public interface LoginableJPA extends CrudRepository<UserProfileDao, Integer> {
   <S extends UserCredential> S save(UserCredential daoRepo);
   List<UserCredential> findAllByID(UserCredential daoRepo);
   <S extends UserCredential> void deleteById(UserCredential repo);
   <S extends UserCredential> void findAllByUsername(UserCredential repo);
}
