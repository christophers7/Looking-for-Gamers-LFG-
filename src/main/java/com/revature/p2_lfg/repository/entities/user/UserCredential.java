package com.revature.p2_lfg.repository.entities.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(schema = "project_two", name = "lfg_user_creds")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCredential {
    @Id
    @Column(name = "userId")
    @GeneratedValue(generator = "lfg_user_creds_user_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "lfg_user_creds_user_id_seq", sequenceName = "lfg_user_creds_user_id_seq")
    private int userId;
    @Column(name = "userlogin")
    private String username;
    @Column(name = "userpass")
    private String password;

}
