package org.example.gerenciadordeplaylists.Dao;

import org.example.gerenciadordeplaylists.Models.Musica;
import org.example.gerenciadordeplaylists.Models.PlayList;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaDao {

    public void inserir(Musica m){
        String sql = "INSERT INTO musica(titulo, artista, playlist_id) VALUES (?, ?, ?)";

        try (Connection conn = Conexaodb.get();
             PreparedStatement pst = conn.prepareStatement(sql) ){

            pst.setString(1, m.getTitulo());
            pst.setString(2, m.getArtista());
            if (m.getPlayList() == null) {
                pst.setNull(3, java.sql.Types.INTEGER);
            } else {
                pst.setInt(3, m.getPlayList());
            }

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPorId(int idMusic){
        String sql = "DELETE FROM musica WHERE id = ?";

        try (Connection conn = Conexaodb.get();
             PreparedStatement pst = conn.prepareStatement(sql)){

            pst.setInt(1, idMusic);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Musica> listarPorPlay(int playListId){
        List<Musica> listaMusica = new ArrayList<>();
        String sql = "SELECT * FROM musica WHERE playlist_id = ?";

        try (Connection conn = Conexaodb.get()){
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, playListId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                listaMusica.add(new Musica( rs.getInt("id"),
                        rs.getString("titulo"), rs.getString("artista"), playListId));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaMusica;
    }

    public List<Musica> listar(){
        List<Musica> listaMusic = new ArrayList<>();
        String sql = "SELECT id, titulo, artista, playlist_id FROM musica";


        try (Connection conn = Conexaodb.get();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)){

            while (rs.next()){
                listaMusic.add(new Musica(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("artista"),
                        rs.getObject("playlist_id") == null ? null : rs.getInt("playlist_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaMusic;

    }

}
