package chapter5_javafx.canvas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
 * Beispielprogramm zur Demonstration einfacher SVG-Zeichenbefehle in JavaFX.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014, 2015 by Michael Inden
 */
public class FirstSvgExample extends Application {
    @Override
    public void start(final Stage primaryStage) {
        // Tropfen  
        final SVGPath svgDrop = new SVGPath();
        svgDrop.setContent("M30,100 c45,55 60,100 120,95");

        // Mond  
        final SVGPath svgMoon = new SVGPath();
        svgMoon.setContent("M40,20 A30,30 0 0,0 70,70 M40,20 A30,30 0 1,0 70,70");
        svgMoon.fillProperty().set(Color.YELLOW);
        svgMoon.strokeProperty().set(Color.DARKORANGE);
        // sonst wird das Aeussere auch gefuellt
        svgMoon.fillRuleProperty().set(FillRule.EVEN_ODD);
        svgMoon.strokeWidthProperty().set(5.0);

        //  Mithilfe der StackPane kann man Figuren ¸bereinander zeichnen 
        final StackPane root = new StackPane();
        root.getChildren().addAll(svgDrop, svgMoon);

        primaryStage.setScene(new Scene(root, 250, 150));
        primaryStage.setTitle("FirstSvgExample");
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}