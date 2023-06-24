package sample.propofolrechner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController {

    // Member variable, die eine Referenz auf die jeweilige Szene (Fenster) enthält.
    private Stage stage = null;
    // Member variable, die eine Referenz auf die nächste Szene enthält, zu der der Controller wechseln soll.
    private Scene nextScene = null;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }
    @FXML
    private Button clickmeButton;

    /*@FXML
    private void handleClickmeButtonPressEvent(ActionEvent event) {
        stage.setScene(nextScene);
    }
     */

@FXML
   public void handleClickmeButton(ActionEvent actionEvent) {
       stage.setScene(nextScene);
   }
}
