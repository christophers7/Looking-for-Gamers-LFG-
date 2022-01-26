package repository.DAO.implementation;

import repository.DAO.interfaces.Sessionable;
import repository.entities.Session;
import repository.entities.compositeKeys.GroupSessionId;
import service.session.dto.GroupUser;

import java.util.List;

public class SessionDao implements Sessionable {
    @Override
    public GroupSessionId createUserSessionEntry(Session session) {
        return null;
    }

    @Override
    public List<GroupUser> getGroupMembersByGroupId(int groupId) {
        return null;
    }
}
