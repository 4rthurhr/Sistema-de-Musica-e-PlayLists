package org.example.gerenciadordeplaylists.Controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.gerenciadordeplaylists.Dao.MusicaDao;
import org.example.gerenciadordeplaylists.Dao.PlayListDao;
import org.example.gerenciadordeplaylists.Models.Musica;
import org.example.gerenciadordeplaylists.Models.PlayList;

public class MainController {
    @FXML
    private ListView<Object> listView;
    private ObservableList<PlayList> playLists;
    private ObservableList<Musica> musicas;

    private MusicaDao musicaDao;
    private PlayListDao playListDao;

    @FXML
    public void initialize(){
        playListDao = new PlayListDao();
        musicaDao  = new MusicaDao();

        playLists = FXCollections.observableArrayList(playListDao.listar());
        musicas = FXCollections.observableArrayList(musicaDao.listar());

        listView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else if (item instanceof Musica m) {
                    setText(m.getTitulo() + " - " + m.getArtista());
                } else if (item instanceof PlayList p) {
                    setText(p.getTitulo());
                }
            }
        });

    }


    @FXML
    public void mostrarMusicas() {
       listView.setItems((ObservableList) musicas);
    }

    @FXML
    public void mostrarPlaylists() {
       listView.setItems((ObservableList) playLists);
    }



    @FXML
    private void abrir(ActionEvent v) {
        try {
            FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/org/example/gerenciadordeplaylists/criar-musica-view.fxml")
    );

            Scene scene = new Scene(loader.load());
            MusicaController musicaController = loader.getController();
            musicaController.setMusic(musicas);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Adicionar Música");
            stage.show();

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void abrirPlay(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/gerenciadordeplaylists/criar-play-view.fxml")
            );


            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            PlaylistController controller = loader.getController();
            controller.setPlayLists(playLists);
            stage.setScene(scene);
            stage.setTitle("Adicionar PlayList");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
