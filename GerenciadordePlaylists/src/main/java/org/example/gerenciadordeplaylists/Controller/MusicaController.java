package org.example.gerenciadordeplaylists.Controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.gerenciadordeplaylists.Dao.MusicaDao;
import org.example.gerenciadordeplaylists.Models.Musica;
import org.example.gerenciadordeplaylists.Models.PlayList;

public class MusicaController {
    @FXML
    private ListView<Musica> musicaListView;
    private ObservableList<Musica> listMusica;

    @FXML
    private TextField txTitulo;
    @FXML
    private TextField txArtista;


    private MusicaDao musicaDao;

    public MusicaController(){
        this.musicaDao = new MusicaDao();
    }

    public void initialize(){
        listMusica = FXCollections.observableArrayList();
        musicaListView.setItems(listMusica);
    }

    public void setMusic(ObservableList<Musica> musicaExistente){
        listMusica = musicaExistente;
        musicaListView.setItems(listMusica);
    }

    @FXML
    public void addMusica(){
        String titulo = txTitulo.getText();
        String artista = txArtista.getText();

        Musica m = new Musica(0, titulo, artista, null);
        musicaDao.inserir(m);
        listMusica.add(m);

    }

   @FXML
   public void removerMuisca(){
        Musica selecionado = musicaListView.getSelectionModel().getSelectedItem();

        if(selecionado == null){
            System.out.println("Erro muisca não selecionada");
        }

        int id  = selecionado.getId();

       musicaDao.removerPorId(selecionado.getId());


       listMusica.remove(selecionado);

   }

    public List<Musica> listMusica(int idPlayList){
        return musicaDao.listarPorPlay(idPlayList);
    }

    
}
