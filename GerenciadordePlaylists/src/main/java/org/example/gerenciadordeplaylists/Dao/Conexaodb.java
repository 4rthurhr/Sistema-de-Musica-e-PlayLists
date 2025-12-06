package org.example.gerenciadordeplaylists.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexaodb {
    private static  final  String URL = "jdbc:sqlite:playlists.db";

    static {
        try (Connection conn = DriverManager.getConnection(URL)){
            Statement st = conn.createStatement();

            st.execute("""
               CREATE TABLE IF NOT EXISTS playlist (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT
                )
        """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS musica (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT,
                artista TEXT,
                playlist_id INTEGER REFERENCES playlist(id)
                )
        """);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public  static Connection get() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
