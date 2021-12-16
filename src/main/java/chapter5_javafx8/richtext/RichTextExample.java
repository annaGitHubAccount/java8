package chapter5_javafx8.richtext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Beispielprogramm demonstriert das mit JDK 8 eingefuehrte Feature TextFlow mit CSS.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class RichTextExample extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("Hello Rich Text FXML");
        stage.setScene((Scene) FXMLLoader.load(getClass().getResource("richtext.fxml")));
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}