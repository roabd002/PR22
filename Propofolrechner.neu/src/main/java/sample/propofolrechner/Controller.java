package sample.propofolrechner;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.time.LocalDate;

public class Controller {
    // Member variable holding a reference to the respective scene (window).
    private Stage stage = null;
    // Member variable holding a reference to the next scene the controller should switch to.
    private Scene nextScene = null;
    // Member variable for implementing a simple color switch.
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }
    // private String image = "C:\\Users\\Rebar\\Downloads\\src (2)\\src\\main\\java\\sample\\propofolrechner\\Hintergrund.png";
    private PropofolModel model = new PropofolModel();
    private Model linkedModel = new Model();
    private XYChart.Series<Number, Number> series;
    @FXML
    private TextField weightEingabe;
    @FXML
    private TextField opEingabe;
    @FXML
    private Label dose;
    @FXML
    private Label infusionRate;
    @FXML
    private Button saveButton;
    @FXML
    private DatePicker dateEingabe;
    @FXML
    private Button loadButton;
    @FXML
    private CheckBox alcoholAddictEingabe;
    @FXML
    private CheckBox readHeadEingabe;
    @FXML
    private CheckBox drugAddictEingabe;
    @FXML
    private TextField nameEingabe;
    @FXML
    private TextArea medicalHistoryEingabe;
    @FXML
    private Button rechnenButton;
    @FXML
    private Button propofolButton;
    @FXML
    private AnchorPane hinterGrund;
    @FXML
    private Button infusionButton;
    @FXML
    private Button diagramButton;

    public TextField getNameEingabe() {
        return nameEingabe;
    }

    public DatePicker getDateEingabe() {
        return dateEingabe;
    }

    public TextArea getMedicalHistoryEingabe() {
        return medicalHistoryEingabe;
    }

    public CheckBox getDrugAddictEingabe() {
        return drugAddictEingabe;
    }

    public CheckBox getReadHeadEingabe() {
        return readHeadEingabe;
    }

    public CheckBox getAlcoholAddictEingabe() {
        return alcoholAddictEingabe;
    }

    public void setNameEingabe(TextField nameEingabe) {
        this.nameEingabe = nameEingabe;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public Controller() {

    }

    private Model m;
    private PropofolModel pm;
    public void setModels(Model x, PropofolModel y){
      m = x;
      pm = y;
    }

    public void checkBoxHandle(){
      // m.get(m.) readHeadEingabe.isSelected()
    }
    private Patient patient;

    boolean rothaarig = true;// readHeadEingabe.isSelected(patient.isReadHead());
    boolean drogenabhaengig = false;//drugAddictEingabe.isSelected(patient.isDrugAddict());
    boolean alkoholabhaenig = false; //alcoholAddictEingabe.isSelected(patient.isAlcoholAddict());

    @FXML
    private void handleRechnenButton() {
        model.setWeight(weightEingabe.getText());
        model.setOp(opEingabe.getText());
        dose.setText("" + model.calculateDose(rothaarig, drogenabhaengig, alkoholabhaenig));
        infusionRate.setText("" + model.calculateInfusionRate(rothaarig, drogenabhaengig, alkoholabhaenig));
    }

    @FXML
    private void handleSaveButton() {
        Patient patient = new Patient(nameEingabe.getText());
        patient.setDate(dateEingabe);
        patient.setMedicalHistory(medicalHistoryEingabe.getText());
        patient.setReadHead(readHeadEingabe.isSelected());
        patient.setDrugAddict(drugAddictEingabe.isSelected());
        patient.setAlcoholAddict(alcoholAddictEingabe.isSelected());
        linkedModel.add(patient);
        linkedModel.save("testFile.txt");
    }

    @FXML
    private void handleLoadButton() {
        m.load("testFile.txt");
        if (m.getLinkedList().size() > 0) {
            Patient patient = m.getLinkedList().get(0);
            updateFormData(patient);
        }
    }
    @FXML Button anzeigeEingabe;
    @FXML
    private void handleAnzeigeButton(){
       // m.getLinkedList().durchsuche(nameEingabe.getText());
        updateFormData(m.getLinkedList().durchsuche(nameEingabe.getText()));
    }

    private void updateFormData(Patient patient) {
     //   Patient patient1 = m.getLinkedList().get(nameEingabe.setText(patient.getName()));
      //  nameEingabe.setText(m.getLinkedList().get(m.getLinkedList().durchsuche(nameEingabe.getText()).getName(0)));
        dateEingabe.setValue(LocalDate.parse(patient.getDate()));
        medicalHistoryEingabe.setText(patient.getMedicalHistory());
        alcoholAddictEingabe.setSelected(patient.isAlcoholAddict());
        readHeadEingabe.setSelected(patient.isReadHead());
        drugAddictEingabe.setSelected(patient.isDrugAddict());
    }


    @FXML
    private void handleDiagramButton() {
        Stage chartStage = new Stage();
        chartStage.setTitle("Propofol dose over time");

        double opdauerMinutes = Double.parseDouble(opEingabe.getText());
        double opdauerHours = opdauerMinutes / 60.0;

        NumberAxis xAxis = new NumberAxis(0, opdauerHours + 2, 0.5);
        NumberAxis yAxis = new NumberAxis(0, 1000, 100);

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setPrefWidth(600);
        chart.setPrefHeight(500);
        xAxis.setLabel("Time (hours)");
        yAxis.setLabel("Infusion rate (ml/h)");

        series = new XYChart.Series<>();
        series.setName(" Infusion ");




        double xValue = 0.0;
        double yValue = 0;

        double gesamtzeit = 0;
        double maxGesamtzeit = opdauerHours + 1;

        opdauerHours = opdauerHours + 0.5;

        yValue = model.calculateInfusionRate(rothaarig, drogenabhaengig, alkoholabhaenig);

        series.getData().add(new XYChart.Data<Number, Number>(xValue, 0));

        while (xValue < maxGesamtzeit) {
            double dose;
            if (gesamtzeit < opdauerHours) {
                dose = yValue;
            } else {
                dose = 0;
            }


            series.getData().add(new XYChart.Data<>(xValue + 0.1, dose));


            xValue += 0.1;
            gesamtzeit += 0.1;
        }

        chart.getData().add(series);
        series.getNode().setStyle("-fx-stroke: green;"+ "");
        VBox chartLayout = new VBox(chart);
        chartLayout.setPadding(new Insets(10));
        Scene chartScene = new Scene(chartLayout);
        chartStage.setScene(chartScene);
        chartStage.show();
    }

    @FXML
    private void handleInfusionButton() {
        String videoFile = "Infusion.mp4";
        String videoPath = getClass().getResource(videoFile).toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(400);
        mediaView.setFitHeight(300);
        Stage videoStage = new Stage();
        videoStage.setTitle("Prepare Infusions");
        videoStage.setScene(new Scene(new StackPane(mediaView), 400, 300));
        videoStage.show();
    }

    @FXML
    public void handlePropofolButton() {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://de.wikipedia.org/wiki/Propofol");
        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    /*public void initialize() {
        hinterGrund.setBackground(new Background(new BackgroundImage(new Image(image), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
    }
     */
}
