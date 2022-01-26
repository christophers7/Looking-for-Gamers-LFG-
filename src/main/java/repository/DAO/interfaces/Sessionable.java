package repository.DAO.interfaces;

import repository.entities.Session;
import repository.entities.compositeKeys.GroupSessionId;
import service.session.dto.GroupUser;

import java.util.List;

public interface Sessionable {
    GroupSessionId createUserSessionEntry(Session session);

    List<GroupUser> getGroupMembersByGroupId(int groupId);
}
