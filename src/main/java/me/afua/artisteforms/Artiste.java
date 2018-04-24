package me.afua.artisteforms;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artiste {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String fullName;
    private String stageName;

    //One Artiste HAS many songs. And in each Song, the artiste's ID is represented by 'leadArtiste'.
    //Have a look at the database after adding a song to understand this relationship.
    @OneToMany(mappedBy = "leadArtiste")
    Set <Song> mySongs;

    public Artiste() {
        mySongs = new HashSet<>();
    }

    public Artiste(String fullName) {
        this.fullName = fullName;
        mySongs = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    @Override
    public String toString() {
        return "Artiste{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public Set<Song> getMySongs() {
        return mySongs;
    }

    public void setMySongs(Set<Song> mySongs) {
        this.mySongs = mySongs;
    }
}
