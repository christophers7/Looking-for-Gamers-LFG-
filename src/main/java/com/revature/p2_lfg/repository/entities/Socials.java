package com.revature.p2_lfg.repository.entities;

import com.revature.p2_lfg.repository.entities.compositeKeys.SocialId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(SocialId.class)
@Table(schema = "project_two",name = "lfg_socials")
public class Socials {
    @Id
    @Column (name = "userid")
    private int userID;
    @Id
    @Column (name = "gameid")
    private int gameID;
    @Column (name = "gamertag")
    private String gamerTag;

    public Socials(int userID, int gameID, String gamerTag) {
        this.userID = userID;
        this.gameID = gameID;
        this.gamerTag = gamerTag;
    }

    public Socials() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    @Override
    public String toString() {
        return "{\"Socials\":{"
                + "\"userID\":\"" + userID + "\""
                + ", \"gameID\":\"" + gameID + "\""
                + ", \"gamerTag\":\"" + gamerTag + "\""
                + "}}";
    }
}
