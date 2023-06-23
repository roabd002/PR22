package sample.propofolrechner;

import javafx.scene.control.DatePicker;

import java.io.Serializable;

public class Patient implements Serializable {

    private String name;
    private  String medicalHistory;
    private String date;
    private boolean readHead;
    private boolean drugAddict;
    private boolean alcoholAddict;
    private Patient next;

    public Patient(String name){
        this.name =name;
        next = null;
    }
    /*public Patient(String name, Patient next){
        this.name =name;
        this.next = next;
    }

     */

    public String getName() {
        return name;
    }

    public void setName(int value) {
        this.name = name;
    }
    Patient getNext() {
        return next;
    }
    void setNext(Patient next) {
        this.next = next;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(DatePicker date) {
        this.date = String.valueOf(date.getValue());
    }

    public boolean isReadHead() {
        return readHead;
    }

    public void setReadHead(boolean readHead) {
        this.readHead = readHead;
    }

    public boolean isDrugAddict() {
        return drugAddict;
    }

    public void setDrugAddict(boolean drugAddict) {
        this.drugAddict = drugAddict;
    }

    public boolean isAlcoholAddict() {
        return alcoholAddict;
    }

    public void setAlcoholAddict(boolean alcoholAddict) {
        this.alcoholAddict = alcoholAddict;
    }
   /* public void patientendaten(String name, DatePicker date, String medicalHistory, boolean rothaarig, boolean drogenabhaengig, boolean alkoholabhaengig) {
        this.name = name;
        this.date = String.valueOf(date);
        this.medicalHistory = medicalHistory;
        this.readHead = rothaarig;
        this.drugAddict=drogenabhaengig;
        this.alcoholAddict =alkoholabhaengig; }
    */

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", date='" + date + '\'' +
                ", readHead=" + readHead +
                ", drugAddict=" + drugAddict +
                ", alcoholAddict=" + alcoholAddict +
                ", next=" + next +
                '}';
    }
}




