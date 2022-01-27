package com.revature.p2_lfg.service.session.dto;

import com.revature.p2_lfg.repository.entities.Socials;

import java.util.Objects;

public class GroupUser {
    private String username;
    private int groupId;
    private Socials social;
    private String stats;
    private boolean insideSession;

    public GroupUser() {
    }

    public GroupUser(String username, int groupId, Socials social, String stats, boolean insideSession) {
        this.username = username;
        this.groupId = groupId;
        this.social = social;
        this.stats = stats;
        this.insideSession = insideSession;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Socials getSocial() {
        return social;
    }

    public void setSocial(Socials social) {
        this.social = social;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public boolean isInsideSession() {
        return insideSession;
    }

    public void setInsideSession(boolean insideSession) {
        this.insideSession = insideSession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupUser)) return false;
        GroupUser groupUser = (GroupUser) o;
        return groupId == groupUser.groupId && insideSession == groupUser.insideSession && Objects.equals(username, groupUser.username) && Objects.equals(social, groupUser.social) && Objects.equals(stats, groupUser.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, groupId, social, stats, insideSession);
    }

    @Override
    public String toString() {
        return "{\"GroupUser\":{"
                + "\"username\":\"" + username + "\""
                + ", \"groupId\":\"" + groupId + "\""
                + ", \"social\":" + social
                + ", \"stats\":\"" + stats + "\""
                + ", \"insideSession\":\"" + insideSession + "\""
                + "}}";
    }
}
