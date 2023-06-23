package sample.propofolrechner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader mainloader = new FXMLLoader(getClass().getResource("view.fxml"));
        Parent root = mainloader.load();
        Controller controller = mainloader.getController();
        Model m = new Model();
        PropofolModel pm = new PropofolModel();
        controller.setModels(m, pm);

        Scene main = new Scene(root);
        primaryStage.setTitle("Propofol Calculator and Patient Records");
        primaryStage.setScene(main);
        primaryStage.show();
    }

    public static void main1(String[] args) {
        launch(args);
    }
}

