package repository.entities;

import javax.persistence.*;

@Entity
@Table(schema = "project_two",name = "lfg_public_details")
public class PublicDetails {
    @Id
    @Column(name ="columnID")
    @GeneratedValue(generator = "auto_increment",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1,name = "lfg_public_details_columnid_seq",
            sequenceName = "lfg_public_details_columnid_seq")
    private int columnID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publicID",referencedColumnName = "userID")
    private UserCredential userID;
    @OneToOne
    @JoinColumn(name = "gameID", referencedColumnName = "gameID")
    private Games gameID;
    @Column
    private String gamerTag;

    public PublicDetails() {
    }

    public PublicDetails(int columnID, UserCredential userID, Games gameID, String gamerTag) {
        this.columnID = columnID;
        this.userID = userID;
        this.gameID = gameID;
        this.gamerTag = gamerTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicDetails that = (PublicDetails) o;

        if (columnID != that.columnID) return false;
        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        if (gameID != null ? !gameID.equals(that.gameID) : that.gameID != null) return false;
        return gamerTag != null ? gamerTag.equals(that.gamerTag) : that.gamerTag == null;
    }

    @Override
    public int hashCode() {
        int result = columnID;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (gameID != null ? gameID.hashCode() : 0);
        result = 31 * result + (gamerTag != null ? gamerTag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PublicDetails{" +
                "columnID=" + columnID +
                ", userID=" + userID +
                ", gameID=" + gameID +
                ", gamerTag='" + gamerTag + '\'' +
                '}';
    }
}