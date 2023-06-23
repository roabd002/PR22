module sample.propofolrechner {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires java.desktop;


    opens sample.propofolrechner to javafx.fxml;
    exports sample.propofolrechner;
}