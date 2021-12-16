package chapter5_javafx.basics;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Beispielprogramm zur Einfuehrung der grundlegenden Elemente von JavaFX.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class FirstJavaFxExample extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        final StackPane stackPane = new StackPane();

        // Label erzeugen und hinzufuegen  
        final Node labelNode = new Label("Hello Java FX World!");
        stackPane.getChildren().add(labelNode);

        // Stage und Scene verbinden  
        stage.setScene(new Scene(stackPane, 250, 75));
        // Titel und Resizable-Eigenschaft setzen 
        stage.setTitle("FirstJavaFxExample");
        stage.setResizable(true);
        // Positionierung und Sichtbarkeit  
        stage.centerOnScreen();
        stage.show();
    }

    // ...

    public static void main(final String[] args) {
        launch(args);
    }
}