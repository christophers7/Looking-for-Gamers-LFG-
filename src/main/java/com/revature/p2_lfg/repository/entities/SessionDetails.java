package com.revature.p2_lfg.repository.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(schema = "project_two", name = "lfg_group_information")
public class SessionDetails {
    @Id
    @GeneratedValue(generator = "lfg_group_information_groupid_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "lfg_group_information_groupid_seq", sequenceName = "lfg_group_information_groupid_seq")
    private int groupId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gameID")
    private Games game;
    @Column(name = "maxusers")
    private int maxUsers;
    @Column(name = "currentusers")
    private int currentUsers;
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tag_bridge_table",
            joinColumns = @JoinColumn(name = "groupID"),
            inverseJoinColumns = @JoinColumn(name = "tagID"))
    Set<Tag> tags = new HashSet<>();


}
