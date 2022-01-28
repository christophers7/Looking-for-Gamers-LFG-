package com.revature.p2_lfg.service.session.dto;

import com.revature.p2_lfg.repository.entities.Socials;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class GroupUser {
    String username;
    int groupId;
    Socials social;
    String stats;
    boolean insideSession;
}
