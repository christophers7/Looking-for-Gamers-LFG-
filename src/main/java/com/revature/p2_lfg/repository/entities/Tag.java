package com.revature.p2_lfg.repository.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(schema = "project_two", name = "lfg_tags")
public class Tag {
    @Id
    @GeneratedValue(generator = "lfg_tags_tagid_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "lfg_tags_tagid_seq", sequenceName = "lfg_tags_tagid_seq")
    private int tagId;
    @Column(name = "value")
    private String tagValue;

    @ManyToMany(mappedBy = "tags")
    private Set<SessionDetails> taggedSession = new HashSet<>();


}
