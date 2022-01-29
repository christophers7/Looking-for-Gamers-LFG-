package com.revature.p2_lfg.presentation.models.session;

import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JoinGroupSessionResponse {
    GroupSessionId sessionId;
    int gameId;
    int groupId;
}
