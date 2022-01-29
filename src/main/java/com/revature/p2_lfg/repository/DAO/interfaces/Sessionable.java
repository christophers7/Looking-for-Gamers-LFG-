package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.repository.entities.Session;
import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import com.revature.p2_lfg.service.session.dto.GroupUser;

import java.util.List;

public interface Sessionable {
    GroupSessionId createUserSessionEntry(Session session);

    List<GroupUser> getGroupMembersByGroupId(int groupId);

    int getHostId(int groupId);

    Session getUserSession(int userId, int groupId);

    void save(Session session);

    void delete(Session session);

    void deleteAllWithGroupId(int groupId);
}
