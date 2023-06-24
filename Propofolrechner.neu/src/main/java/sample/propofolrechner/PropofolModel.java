package sample.propofolrechner;

import javafx.beans.property.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

//import static javax.script.ScriptEngine.FILENAME;
// von propofolController
// private PropofolView view;
// private Controller controller;
// private Map<String, String> savedRecords;
//private Map<String, String> savedDates = new HashMap<>();
public class PropofolModel {

    private DoubleProperty weight = new SimpleDoubleProperty();

    public boolean setWeight(String weight) {
        double weightValue;
        if (weight == null) {
            return false;
        }
        try {
            weightValue = Double.parseDouble(weight);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        this.weight.set(weightValue);
        return true;
    }

    private DoubleProperty opdauer = new SimpleDoubleProperty();

    public boolean setOp(String op) {
        double opValue;
        if (op == null) {
            return false;
        }
        try {
            opValue = Double.parseDouble(op);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        this.opdauer.set(opValue);
        return true;
    }


    private DoubleProperty dose = new SimpleDoubleProperty();
    private DoubleProperty infusionRate = new SimpleDoubleProperty();
    private BooleanProperty rothaarig = new SimpleBooleanProperty();
    private BooleanProperty drogenabhaengig = new SimpleBooleanProperty();
    private SimpleBooleanProperty alkoholabhaengig = new SimpleBooleanProperty();



    private static final double MAX_WEIGHT = 600.0;

    /*public DoubleProperty weightProperty() {
        return weight;
    }*/

   /* public DoubleProperty opdauerProperty() {
        return opdauer;
    }*/

    public DoubleProperty doseProperty() {
        return dose;
    }

    public DoubleProperty infusionRateProperty() {
        return infusionRate;
    }

    public BooleanProperty rothaarigProperty() {
        return rothaarig;
    }

    public BooleanProperty drogenabhaengigProperty() {
        return drogenabhaengig;
    }

    public BooleanProperty alkoholabhaengigProperty() {
        return alkoholabhaengig;
    }

    private StringProperty name = new SimpleStringProperty();
    private ObjectProperty<LocalDate> datePicker = new SimpleObjectProperty<>();
    private String medicalHistory = String.valueOf(new SimpleStringProperty());

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public StringProperty nameProperty() {
        return name;
    }

    public ObjectProperty<LocalDate> datePickerProperty() {
        return datePicker;
    }

    public String medicalHistoryProperty() {
        return medicalHistory;
    }


    public double calculateDose() {
        double weightValue = weight.get();
        double doseValue;


        if (weightValue > MAX_WEIGHT) {
            dose.set(0);
            infusionRate.set(0);
            return 0;
        }

        doseValue = weightValue*1.5;

        if (weightValue < 20) {
            doseValue = weightValue * 1;
        }

        if (rothaarigProperty().get()) {
            doseValue = weightValue * 2;
        }

        if (alkoholabhaengig.get()) {
            doseValue = weightValue * 2;
        }

        if (drogenabhaengig.get()) {
            doseValue = weightValue * 2;
        }


        if (drogenabhaengig.get() & alkoholabhaengig.get() || drogenabhaengig.get() & rothaarig.get() || alkoholabhaengig.get() & rothaarig.get()) {
            doseValue = weightValue * 2.5;
        }

        if (drogenabhaengig.get() & alkoholabhaengig.get() & rothaarig.get()) {
            doseValue = weightValue * 3;
        }

        // Ergebnis auf 2 Nachkommastellen abrunden
        doseValue = Math.round(doseValue * 100.0) / 100.0;
        dose.set(doseValue);
        return doseValue;
    }

    public double calculateInfusionRate() {
        double opdauerValue = opdauer.get();
        double doseValue = calculateDose();
        double infusionRateValue = doseValue * (opdauerValue / 60);
        infusionRateValue = Math.round(infusionRateValue * 100.0) / 100.0;
        infusionRate.set(infusionRateValue);
        return infusionRateValue;
    }


}







