package com.revature.p2_lfg.repository.entities;

import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
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
@IdClass(GroupSessionId.class)
@Table(schema = "project_two", name = "lfg_group_sessions")
public class Session {
    @Id
    @Column(name = "userId")
    private int userId;
    @Id
    @Column(name = "hostId")
    private int hostId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupID")
    private SessionDetails groupSession;
    @Column(name = "inSession")
    private boolean inSession;

}

