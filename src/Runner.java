import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Runner extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("templates\\main.fxml");
        Parent root = FXMLLoader.load(url);
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

