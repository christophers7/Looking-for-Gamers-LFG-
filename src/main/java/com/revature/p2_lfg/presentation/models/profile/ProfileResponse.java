package com.revature.p2_lfg.presentation.models.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String JWT;

}
