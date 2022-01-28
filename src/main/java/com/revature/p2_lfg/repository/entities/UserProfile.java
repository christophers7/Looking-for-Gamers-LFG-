package com.revature.p2_lfg.repository.entities;

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
@Table(schema = "project_two", name = "lfg_user_profile")
public class UserProfile {
    @Id
    @Column(name = "columnID")
    @GeneratedValue(generator = "lfg_user_profile_columnid_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "lfg_user_profile_columnid_seq",
            sequenceName = "lfg_user_profile_columnid_seq")
    private int columnID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private UserCredential userCredential;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;


}
