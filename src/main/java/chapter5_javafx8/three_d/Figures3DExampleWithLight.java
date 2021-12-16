package chapter5_javafx8.three_d;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * Beispielprogramm zeigt zwei einfache Figuren in 3D mit Beleuchtung.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class Figures3DExampleWithLight extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setSpecularColor(Color.FIREBRICK);
        redMaterial.setDiffuseColor(Color.RED);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setSpecularColor(Color.DODGERBLUE);
        blueMaterial.setDiffuseColor(Color.BLUE);

        final Box redBox = new Box(400, 400, 200);
        redBox.setMaterial(redMaterial);
        redBox.setTranslateX(100);
        redBox.setTranslateY(150);
        redBox.setTranslateZ(500);
        redBox.setRotationAxis(Rotate.Y_AXIS);
        redBox.setRotate(750);

        final Cylinder blueCylinder = new Cylinder(200, 100);
        blueCylinder.setMaterial(blueMaterial);
        blueCylinder.setTranslateX(350);
        blueCylinder.setTranslateY(350);
        blueCylinder.setTranslateZ(150);

        final PointLight pointLight = new PointLight(Color.ANTIQUEWHITE);
        pointLight.setTranslateX(300);
        pointLight.setTranslateY(100);
        pointLight.setTranslateZ(0);

        // Gruppieren und etwas nach hinten versetzen
        final Group root = new Group(redBox, blueCylinder, pointLight);
        root.setTranslateZ(100);
        root.setRotationAxis(Rotate.X_AXIS);
        root.setRotate(25);

        final Scene scene = new Scene(root, 500, 500);
        final PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
        scene.setCamera(perspectiveCamera);

        primaryStage.setTitle("Figures3DExampleWithLight");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}