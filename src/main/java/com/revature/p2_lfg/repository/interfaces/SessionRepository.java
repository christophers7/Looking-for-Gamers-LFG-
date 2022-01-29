package com.revature.p2_lfg.repository.interfaces;

import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import com.revature.p2_lfg.repository.entities.session.Session;
import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sessionRepository")
public interface SessionRepository extends JpaRepository<Session, GroupSessionId> {

    int findFirst1HostIdByGroupSession(SessionDetails groupSession);

    @Query("select s from Session s where s.groupSession.groupId = ?1")
    List<Session> findAllByGroupId(int groupId);

    @Override
    <S extends Session> S save(S entity);

    @Query("select s from Session s where s.userId = ?1 and s.groupSession.groupId = ?2")
    Session findByUserIdAndGroupId(int userId, int groupId);

    @Modifying
    @Query("delete from Session s where s.groupSession.groupId = ?1")
    void deleteAllByGroupId(int groupId);

    @Modifying
    @Query("delete from Session s where s.userId = ?1 and s.groupSession.groupId = ?2")
    void deleteByUserIdAndGroupId(int userId, int groupId);
}
