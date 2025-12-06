package org.example.gerenciadordeplaylists.Models;

public class Musica {
    private int id;
    private String titulo;
    private String artista;

    private Integer playList_id;

    public Musica(int id, String titulo, String artista, Integer playList_id) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.playList_id = playList_id;
    }

    public Musica(String titulo, String artista) {
        this.titulo = titulo;
        this.artista = artista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Integer getPlayList() {
        return playList_id;
    }

    public void setPlayList(Integer playList) {
        this.playList_id = playList;
    }

    @Override
    public String toString() {
        return "Musica " +
                " titulo " + titulo + '\'' +
                ", artista " + artista + '\'';
    }
}
