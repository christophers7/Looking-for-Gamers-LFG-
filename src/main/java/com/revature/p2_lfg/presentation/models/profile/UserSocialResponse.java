package com.revature.p2_lfg.presentation.models.profile;

import com.revature.p2_lfg.repository.entities.Socials;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSocialResponse {
    boolean success;
    Socials socials;
}
