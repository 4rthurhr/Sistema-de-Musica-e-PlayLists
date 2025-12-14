package org.example.gerenciadordeplaylists.Dao;

import org.example.gerenciadordeplaylists.Models.PlayList;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class PlayListDao {

    public void inserir(PlayList p){
        String sql = "INSERT INTO playlist(titulo) VALUES (?)";

        try (Connection conn = Conexaodb.get();
             PreparedStatement pst = conn.prepareStatement(sql)){

            pst.setString(1, p.getTitulo());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()){
                p.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remover(int p) throws SQLException {
        String sql = "DELETE FROM playlist WHERE id = ?";

        try (Connection conn = Conexaodb.get();
             PreparedStatement pst = conn.prepareStatement(sql)){

            pst.setInt(1, p);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PlayList> listar(){
        List<PlayList> lista = new ArrayList<>();
        String sql = "SELECT id, titulo FROM playlist";

        try (Connection conn = Conexaodb.get();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)){

            while (rs.next()){
                lista.add(new PlayList(rs.getInt("id"), rs.getString("titulo")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;

    }
}
