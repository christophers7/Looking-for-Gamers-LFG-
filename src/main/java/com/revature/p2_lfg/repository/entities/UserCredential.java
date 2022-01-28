package com.revature.p2_lfg.repository.entities;

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
    @Column(name = "userID")
    @GeneratedValue(generator = "lfg_user_creds_user_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "lfg_user_creds_user_id_seq", sequenceName = "lfg_user_creds_user_id_seq")
    private int userID;
    @Column
    private String userLogin;
    @Column
    private String userPass;

}
