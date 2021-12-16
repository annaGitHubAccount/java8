package chapter5_javafx.layout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Beispielprogramm demonstriert die Kombination verschiedener JavaFX-Layoutkomponenten.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class LayoutCombinationExample extends Application {
    @Override
    public void start(final Stage primaryStage) {
        final BorderPane borderPane = new BorderPane();
        borderPane.setTop(createToolbarPane());
        borderPane.setCenter(createInputPane());
        borderPane.setLeft(createNavigationPane());

        primaryStage.setTitle(LayoutCombinationExample.class.getSimpleName());
        primaryStage.setScene(new Scene(borderPane, 350, 250));
        primaryStage.show();
    }

    private Pane createToolbarPane() {
        final HBox hbox = new HBox(5);
        hbox.getChildren().addAll(new Text("TOP"), new Button("HBox1"), new Button("HBox2"));
        return hbox;
    }

    private Pane createNavigationPane() {
        final VBox vbox = new VBox(5);
        vbox.getChildren().addAll(new Text("LEFT"), new Button("VBox1"), new Button("VBox2"));
        return vbox;
    }

    private Pane createInputPane() {
        final GridPane gridPane = new GridPane();
        final Label label1 = new Label("Label1");
        final TextField textfield1 = new TextField();
        final Label label2 = new Label("Label2");
        final TextField textfield2 = new TextField();
        final Button button = new Button("Button");
        gridPane.add(label1, 0, 0);
        gridPane.add(textfield1, 1, 0);
        gridPane.add(label2, 0, 1);
        gridPane.add(textfield2, 1, 1);
        gridPane.add(button, 1, 2);

        return gridPane;
    }

    public static void main(final String[] args) {
        launch(args);
    }
}