package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.repository.entities.UserCredential;
import com.revature.p2_lfg.repository.entities.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("profileableJPA")
public interface ProfileableJPA extends CrudRepository<UserProfile,Integer> {
    <S extends UserProfile> S save(UserProfile userProfile);
    List<UserProfile> findById(UserProfile userProfile);
    void deleteById(UserProfile userProfile);
    void deleteByemail(UserProfile userProfile);
    List <UserProfile> findAllByusercredential(UserCredential userCredential);

}
