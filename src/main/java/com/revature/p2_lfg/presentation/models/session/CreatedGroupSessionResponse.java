package com.revature.p2_lfg.presentation.models.session;

import com.revature.p2_lfg.service.session.dto.GroupUser;

import java.util.List;
import java.util.Objects;

public class CreatedGroupSessionResponse {
    private int groupId;
    private int gameId;
    private int maxUsers;
    private String description;
    private List<GroupUser> groupMembers;

    public CreatedGroupSessionResponse() {
    }

    public CreatedGroupSessionResponse(int groupId, int gameId, int maxUsers, String description, List<GroupUser> groupMembers) {
        this.groupId = groupId;
        this.gameId = gameId;
        this.maxUsers = maxUsers;
        this.description = description;
        this.groupMembers = groupMembers;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GroupUser> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupUser> groupMembers) {
        this.groupMembers = groupMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatedGroupSessionResponse)) return false;
        CreatedGroupSessionResponse that = (CreatedGroupSessionResponse) o;
        return groupId == that.groupId && gameId == that.gameId && maxUsers == that.maxUsers && Objects.equals(description, that.description) && Objects.equals(groupMembers, that.groupMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, gameId, maxUsers, description, groupMembers);
    }

    @Override
    public String toString() {
        return "{\"CreatedGroupSessionResponse\":{"
                + "\"groupId\":\"" + groupId + "\""
                + ", \"gameId\":\"" + gameId + "\""
                + ", \"maxUsers\":\"" + maxUsers + "\""
                + ", \"description\":\"" + description + "\""
                + ", \"groupMembers\":" + groupMembers
                + "}}";
    }
}
