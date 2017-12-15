
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
        primaryStage.setTitle("iTunes");
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(840);
        primaryStage.setHeight(640);
        primaryStage.setMinWidth(840);
        primaryStage.setMinHeight(640);
        primaryStage.show();
//        String bip = "music.mp3";
//        Media hit = new Media(new File(bip).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(hit);
//        mediaPlayer.play();
//        MainController controller = new FXMLLoader().getController();

    }


    public static void main(String[] args) {
        launch();
    }
}

