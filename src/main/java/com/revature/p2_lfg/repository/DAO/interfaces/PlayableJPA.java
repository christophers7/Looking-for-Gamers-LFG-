package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.repository.entities.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
    @Repository("playableJPA")
    public interface PlayableJPA extends JpaRepository<Games,Integer> {

        <S extends Games> S save(Games games);

        List<Games> findAllBygameid(Games games);

        List<Games> findAll();


}
