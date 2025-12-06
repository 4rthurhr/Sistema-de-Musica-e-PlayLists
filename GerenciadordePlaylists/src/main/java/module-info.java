module org.example.gerenciadordeplaylists {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.gerenciadordeplaylists to javafx.fxml;
    opens org.example.gerenciadordeplaylists.Controller to javafx.fxml;

    exports org.example.gerenciadordeplaylists;
}
