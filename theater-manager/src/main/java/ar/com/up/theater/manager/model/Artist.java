package ar.com.up.theater.manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "artistas")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_artista")
    private Integer artistId;

    @Column(name = "nombre")
    private String name;

    @Column(name = "nacionalidad")
    private String nationality;

    @Column(name = "genero")
    private String genre;

    public Artist() {
    }

    public Artist(Integer artistId, String name, String nationality, String genre) {
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.genre = genre;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
