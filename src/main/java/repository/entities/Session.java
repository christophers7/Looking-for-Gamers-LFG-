package repository.entities;

import repository.entities.compositeKeys.GroupSessionId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(GroupSessionId.class)
@Table(schema = "project_two", name = "lfg_group_sessions")
public class Session {
    @Id
    @Column(name = "userId")
    private int userId;
    @Id
    @Column(name = "hostId")
    private int hostId;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="groupID")
    private SessionDetails groupSession;
    @Column(name = "inSession")
    private boolean inSession;



    public Session() {
    }

    public Session(int userId, int hostId, SessionDetails groupSession, boolean inSession) {
        this.userId = userId;
        this.hostId = hostId;
        this.groupSession = groupSession;
        this.inSession = inSession;
    }

    public int getUserID() {
        return userId;
    }

    public void setUserID(int userId) {
        this.userId = userId;
    }

    public int getHostID() {
        return hostId;
    }

    public void setHostID(int hostId) {
        this.hostId = hostId;
    }

    public SessionDetails getGroupSession() {
        return groupSession;
    }

    public void setGroupSession(SessionDetails groupSession) {
        this.groupSession = groupSession;
    }

    public boolean isInSession() {
        return inSession;
    }

    public void setInSession(boolean inSession) {
        this.inSession = inSession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return userId == session.userId && hostId == session.hostId && inSession == session.inSession && Objects.equals(groupSession, session.groupSession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, hostId, groupSession, inSession);
    }

    @Override
    public String toString() {
        return "{\"Session\":{"
                + "\"userId\":\"" + userId + "\""
                + ", \"hostId\":\"" + hostId + "\""
                + ", \"groupSession\":" + groupSession
                + ", \"inSession\":\"" + inSession + "\""
                + "}}";
    }
}
