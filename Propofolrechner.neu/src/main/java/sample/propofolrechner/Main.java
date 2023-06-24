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
        Parent root= mainloader.load();
        Controller controller = mainloader.getController();

        Model linkedModel = new Model();
        PropofolModel model = new PropofolModel();
        controller.setModels(linkedModel, model);

        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("view2.fxml"));
        Parent rootWelcome = welcomeLoader.load();
        WelcomeController welcomeController = welcomeLoader.getController();



        Scene main = new Scene(root);
        Scene welcome = new Scene(rootWelcome);
        welcomeController.setStage(primaryStage);
        welcomeController.setNextScene(main);
        primaryStage.setTitle("Propofol Calculator and Patient Records");
        primaryStage.setScene(welcome);

        main.getStylesheets().add(getClass().getResource("Diagram.css").toExternalForm());
        welcome.getStylesheets().add(getClass().getResource("Diagram.css").toExternalForm());
        primaryStage.show();
    }

    public static void main1(String[] args) {
        launch(args);
    }
}

