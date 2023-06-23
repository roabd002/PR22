package sample.propofolrechner;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Model encapsulating a list implementation.
 * @author Karsten Lehn
 * @version 22.5.2022
 */
public class Model {

    private LinkedList linkedList;
    // Variable for implementing the observer pattern. The Model object acts as an Observable.
    private PropertyChangeSupport modelChanges = new PropertyChangeSupport(this);

    public Model() {
        linkedList = new LinkedList();
    }

    public LinkedList getLinkedList() {
        return linkedList;
    }

    public void add(Patient patient) {
        linkedList.add(patient);
        // If a value was changed all registered observers are informed, that a change occurred.
        modelChanges.fireIndexedPropertyChange("modelChange", 0, false, true);
    }

    public Patient get(int index) {
        return linkedList.get(index);
    }

    /**
     * Save the list content using standard serialization
     * @param fileName
     * @return true if saving was successful
     */
    public boolean save(String fileName) {
        boolean success = false;

        // Save linked list using standard serialisation
        try (OutputStream fileOutputStream =
                     Files.newOutputStream(Paths.get(fileName));
             ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(linkedList);
            success = true;
        }
        catch (IOException exception) {
            success = false;
        }
        return success;
    }

    /**
     * Load the list content using standard serialization
     * @param fileName
     * @return true if loading was successful
     */
    public boolean load(String fileName) {
        boolean success = false;

        // load list from file
        try (InputStream fileInputStream =
                     Files.newInputStream(Paths.get(fileName));
             ObjectInputStream objectInputStream =
                     new ObjectInputStream(fileInputStream)) {
            linkedList = (LinkedList) objectInputStream.readObject();
            success = true;
        }
        catch ( IOException | ClassNotFoundException exception ) {
            success = false;
        }
        // If a value was changed all registered observers are informed, that a change occurred.
        modelChanges.fireIndexedPropertyChange("modelChange", 0, false, true);
        return success;


    }

    /**
     * For adding a new listener to changes of the model.
     * @param listener
     */
    public void addPropertyChangeListener (PropertyChangeListener listener) {
        modelChanges.addPropertyChangeListener(listener);
    }

    /**
     * For removing a listener from being informed of changes of the model.
     * @param listener
     */
    public void removePropertyChangeListener (PropertyChangeListener listener) {
        modelChanges.removePropertyChangeListener(listener);
    }

}
