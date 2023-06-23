package sample.propofolrechner;

import java.io.Serializable;

public class LinkedList implements Serializable {

    /** Private class for holding the elements of the list and the
     * referenced (links) within the list.
     *
     * Implemented for holding elements of class Patient
     */
    private class LinkElement implements Serializable {
        private Patient patient;
        private Patient next;

        public LinkElement(Patient patient) {
            this.patient = patient;
            next = null;
        }

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        private Patient getNext() {
            return next;
        }

        private void setNext(Patient next) {
            this.next = next;
        }

        public String toString() {
            return patient.toString();
        }
    }

    // Instance variables of the linked list
    private Patient head = null;
    private Patient tail = null;

    /**
     * Adds a new list element holding the new value at the end of the list.
     * @param patient
     */
    private void addLast(Patient patient){
        if (head == null) {
            head = patient;
            tail = head;
        } else {
            tail.setNext(patient);
            tail = patient;
        }
        patient.setNext(null);
    }

    public void add(Patient patient) {
        addLast(patient);
    }

    /**
     * Returns the number of list elements.
     * @return
     */
    public int size() {
        int noOfElements = 0;
        Patient patient = head;
        while (patient != null) {
            noOfElements++;
            patient = patient.getNext();
        }
        return noOfElements;
    }

    /**
     * Returns the value of the element at the specified index.
     * @param index
     * @return
     */
    public Patient get(int index) {
        int currentIndex = 0;
        Patient patient = head;
        while ((patient != null) && (currentIndex < index)) {
            patient = patient.getNext();
            currentIndex++;
        }
        if (patient != null) {
            return patient;
        } else {
            return null;
        }
    }

    /**
     * Converts the list into a string representation.
     * @return
     */
    public String toString() {
        StringBuilder listString = new StringBuilder();
        listString.append("(");
        Patient patient = head;
        while (patient !=null) {
            listString.append(patient);
            patient = patient.getNext();
            if (patient != null) {
                listString.append(" ");
            }
        }
        listString.append(")");
        return listString.toString();
    }
    public Patient durchsuche(String name){
        //int currentIndex = 0;
        Patient patient = head;
        while ((patient != null) && (patient.getName().compareTo(name)!= 0)) {
            patient = patient.getNext();
            //currentIndex++;
        }
        if (patient != null) {
            return patient;
        } else {
            return null;
        }
    }
}
