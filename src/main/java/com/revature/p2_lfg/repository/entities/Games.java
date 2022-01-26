package repository.entities;

import javax.persistence.*;

@Entity
@Table(schema = "project_two",name = "lfg_games")
public class Games {
    @Id
    @Column(name = "gameID")
    @GeneratedValue(generator = "auto_increment",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1, name = "lfg_games_gameid_seq",sequenceName = "lfg_games_gameid_seq")
    private int gameID;
    @Column
    private String gameTitle;
    @Column
    private String imgLink;

    public Games(int gameID, String gameTitle, String imgLink) {
        this.gameID = gameID;
        this.gameTitle = gameTitle;
        this.imgLink = imgLink;
    }

    public Games() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Games games = (Games) o;

        if (gameID != games.gameID) return false;
        if (gameTitle != null ? !gameTitle.equals(games.gameTitle) : games.gameTitle != null) return false;
        return imgLink != null ? imgLink.equals(games.imgLink) : games.imgLink == null;
    }

    @Override
    public int hashCode() {
        int result = gameID;
        result = 31 * result + (gameTitle != null ? gameTitle.hashCode() : 0);
        result = 31 * result + (imgLink != null ? imgLink.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Games{" +
                "gameID=" + gameID +
                ", gameTitle='" + gameTitle + '\'' +
                ", imgLink='" + imgLink + '\'' +
                '}';
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }


}
