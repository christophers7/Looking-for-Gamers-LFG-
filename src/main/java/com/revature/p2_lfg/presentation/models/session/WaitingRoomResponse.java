package com.revature.p2_lfg.presentation.models.session;

import com.revature.p2_lfg.service.session.dto.GroupUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaitingRoomResponse {
    boolean success;
    GroupUser user;
}
