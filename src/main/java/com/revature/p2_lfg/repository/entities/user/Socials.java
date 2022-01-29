package com.revature.p2_lfg.repository.entities.user;

import com.revature.p2_lfg.repository.entities.compositeKeys.SocialId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@IdClass(SocialId.class)
@Table(schema = "project_two", name = "lfg_socials")
public class Socials {
    @Id
    @Column(name = "userid")
    private int userID;
    @Id
    @Column(name = "gameid")
    private int gameID;
    @Column(name = "gamertag")
    private String gamerTag;

}
