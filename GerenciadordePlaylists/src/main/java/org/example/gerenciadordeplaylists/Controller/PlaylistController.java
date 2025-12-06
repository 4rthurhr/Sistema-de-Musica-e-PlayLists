package org.example.gerenciadordeplaylists.Controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.gerenciadordeplaylists.Dao.PlayListDao;
import org.example.gerenciadordeplaylists.Models.PlayList;

public class PlaylistController {
    @FXML
    private ListView<PlayList> listView;
    private ObservableList<PlayList> playLists;
    @FXML
    private TextField txtitulo;

    private PlayListDao playListDao;

    public PlaylistController(){
        this.playListDao = new PlayListDao();
    }
    public void initialize(){
        playLists = FXCollections.observableArrayList();
        listView.setItems(playLists);
    }

    public void setPlayLists(ObservableList<PlayList> playExistentes){
        playLists = playExistentes;
        listView.setItems(playLists);
    }

    @FXML
    public void addPlaylist( ){
        String titutlo = txtitulo.getText();
        PlayList p = new PlayList(0, titutlo);
        playListDao.inserir(p);
        playLists.add(p);
    }




}
