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
    private SimpleBooleanProperty rothaarig = new SimpleBooleanProperty();
    private SimpleBooleanProperty drogenabhaengig = new SimpleBooleanProperty();
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

    public SimpleBooleanProperty rothaarigProperty() {
        return rothaarig;
    }

    public SimpleBooleanProperty drogenabhaengigProperty() {
        return drogenabhaengig;
    }

    public SimpleBooleanProperty alkoholabhaengigProperty() {
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
    public double calculateDose(boolean rothaarig, boolean drogenabhaengig, boolean alkoholabhaenig) {
        double weightValue = weight.get();
        double opdauerValue = opdauer.get();


        if (weightValue > MAX_WEIGHT) {
            dose.set(0);
            infusionRate.set(0);
            return 0;
        }

        double doseValue = weightValue * 1.5;

        if (weightValue < 20) {
            doseValue = weightValue * 1;
        }

        if (rothaarig || drogenabhaengig || alkoholabhaenig) {
            doseValue = weightValue * 2;
        }

        if ((drogenabhaengig && alkoholabhaenig) || (drogenabhaengig && rothaarig) || (alkoholabhaenig && rothaarig)) {
            doseValue = weightValue * 2.5;
        }

        if (drogenabhaengig && alkoholabhaenig && rothaarig) {
            doseValue = weightValue * 3;
        }

        double infusionRateValue = doseValue * (opdauerValue / 60);

        // Ergebnis auf 2 Nachkommastellen abrunden
        doseValue = Math.round(doseValue * 100.0) / 100.0;
        infusionRateValue = Math.round(infusionRateValue * 100.0) / 100.0;

        dose.set(doseValue);
        infusionRate.set(infusionRateValue);

        return doseValue;
    }

    public double calculateInfusionRate(boolean rothaarig, boolean drogenabhaengig, boolean alkoholabhaenig) {
        double opdauerValue = opdauer.get();
        double doseValue = calculateDose(rothaarig, drogenabhaengig, alkoholabhaenig);
        double infusionRateValue = doseValue * (opdauerValue / 60);
        infusionRateValue = Math.round(infusionRateValue * 100.0) / 100.0;
        infusionRate.set(infusionRateValue);
        return infusionRateValue;
    }


   /* public double calculateDose() {
        double weightValue = weight.get();
        double doseValue;
        if (weightValue > MAX_WEIGHT) {
            dose.set(0);
            infusionRate.set(0);
            return 0;
        } else if (drogenabhaengig.get() || alkoholabhaengig.get() || rothaarig.get()) {
            doseValue = weightValue * 2;
        } else {
            doseValue = weightValue * 1.5;
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

    */

}






