package repository.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "project_two", name = "lfg_group_information")
public class SessionDetails {
    @Id
    @GeneratedValue(generator = "lfg_group_information_groupid_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "lfg_group_information_groupid_seq", sequenceName = "lfg_group_information_groupid_seq")
    private int groupId;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "gameID")
    private Games game;
    @Column(name = "maxusers")
    private int maxUsers;
    @Column(name = "currentusers")
    private int currentUsers;
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tag_bridge_table",
            joinColumns = @JoinColumn(name = "groupID"),
            inverseJoinColumns = @JoinColumn(name = "tagID"))
    Set<Tag> tags;

    public SessionDetails() {
    }

    public SessionDetails(int groupId, Games game, int maxUsers, int currentUsers, String description) {
        this.groupId = groupId;
        this.game = game;
        this.maxUsers = maxUsers;
        this.currentUsers = currentUsers;
        this.description = description;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Games getGame() {
        return game;
    }

    public void setGame(Games game) {
        this.game = game;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public int getCurrentUsers() {
        return currentUsers;
    }

    public void setCurrentUsers(int currentUsers) {
        this.currentUsers = currentUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionDetails)) return false;
        SessionDetails that = (SessionDetails) o;
        return groupId == that.groupId && maxUsers == that.maxUsers && currentUsers == that.currentUsers && Objects.equals(game, that.game) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, game, maxUsers, currentUsers, description);
    }

    @Override
    public String toString() {
        return "{\"SessionDetails\":{"
                + "\"groupId\":\"" + groupId + "\""
                + ", \"game\":" + game
                + ", \"maxUsers\":\"" + maxUsers + "\""
                + ", \"currentUsers\":\"" + currentUsers + "\""
                + ", \"description\":\"" + description + "\""
                + "}}";
    }
}
