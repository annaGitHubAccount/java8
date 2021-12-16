package chapter5_javafx.observablecollections;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Beispielprogramm zur Demonstration der ObservableList.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class ObservableListExample {
    public static void main(final String[] args) {
        final String[] content = {"Orig1", "Orig2"};
        final ObservableList<String> observableList = FXCollections.observableArrayList(content);

        observableList.addListener((ListChangeListener<String>) change -> reportChanges(change));

        System.out.println("modifications");
        observableList.addAll("A", "B", "C");
        observableList.removeAll("Orig1", "Orig2");
        observableList.add("1");
        observableList.add("2");

        System.out.println("rotate()");
        FXCollections.rotate(observableList, 2);

        System.out.println("shuffle()");
        FXCollections.shuffle(observableList);
    }

    private static void reportChanges(ListChangeListener.Change<? extends String> change) {
        System.out.println("Changed to: " + change.getList());
    }
}