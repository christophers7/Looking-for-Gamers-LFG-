package repository.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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
    Set<SessionDetails> taggedSession;

    public Tag() {
    }

    public Tag(int tagId, String tagValue) {
        this.tagId = tagId;
        this.tagValue = tagValue;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return tagId == tag.tagId && Objects.equals(tagValue, tag.tagValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tagValue);
    }

    @Override
    public String toString() {
        return "{\"Tag\":{"
                + "\"tagId\":\"" + tagId + "\""
                + ", \"tagValue\":\"" + tagValue + "\""
                + "}}";
    }
}
