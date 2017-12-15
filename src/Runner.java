
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Runner extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("templates\\main.fxml"));
        primaryStage.setTitle("Music center");
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(840);
        primaryStage.setHeight(640);
        primaryStage.setMinWidth(840);
        primaryStage.setMinHeight(640);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

