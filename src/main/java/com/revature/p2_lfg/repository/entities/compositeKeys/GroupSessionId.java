package com.revature.p2_lfg.repository.entities.compositeKeys;

import java.io.Serializable;
import java.util.Objects;

public class GroupSessionId implements Serializable {
    private int userId;
    private int hostId;

    public GroupSessionId() {
    }

    public GroupSessionId(int userId, int hostId) {
        this.userId = userId;
        this.hostId = hostId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupSessionId)) return false;
        GroupSessionId that = (GroupSessionId) o;
        return getUserId() == that.getUserId() && getHostId() == that.getHostId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getHostId());
    }

    @Override
    public String toString() {
        return "{\"GroupSessionId\":{"
                + "\"userId\":\"" + userId + "\""
                + ", \"hostId\":\"" + hostId + "\""
                + "}}";
    }
}
