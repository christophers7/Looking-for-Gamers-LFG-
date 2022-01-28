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
@Table(schema = "project_two", name = "commend_tag_table")
@Entity
public class CommendTagTable {
    @Column
    int commendTagID;
    @Column
    String value;
}
