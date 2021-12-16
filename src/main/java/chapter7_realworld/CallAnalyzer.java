package chapter7_realworld;

import static chapter7_realworld.CallTimeAnalyzeUtils.analyzeCallCounts;
import static chapter7_realworld.CallTimeAnalyzeUtils.analyzeCallTimes;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Beispielprogramm demonstiert die Aufbereitung von Histrogrammen aus Log-Dateien.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CallAnalyzer extends Application {
    private final Button analyzeButton = new Button("Add Log File Data");

    private final CategoryAxis xAxis1 = new CategoryAxis();
    private final CategoryAxis xAxis2 = new CategoryAxis();
    private final NumberAxis yAxisCallCount = new NumberAxis();
    private final NumberAxis yAxisCallTimes = new NumberAxis();

    public BarChart<String, Number> bcCallCount = new BarChart<>(xAxis1,
            yAxisCallCount);
    public BarChart<String, Number> bcCallTimes = new BarChart<>(xAxis2,
            yAxisCallTimes);

    @Override
    public void start(final Stage stage) throws IOException {
        initBarCharts();

        final BorderPane borderPane = buildLayout();
        analyzeButton.setOnAction(event -> onAnalyzeButtonClick(stage));

        stage.setTitle("CallAnalyzer");
        stage.setScene(new Scene(borderPane, 1200, 900));
        stage.show();
    }

    private void initBarCharts() {
        xAxis1.setLabel("method");
        xAxis2.setLabel("method");
        yAxisCallCount.setLabel("no of calls");
        yAxisCallTimes.setLabel("overall call time in ms");
        bcCallCount.setTitle("Call Count");
        bcCallTimes.setTitle("Call Times in ms");
    }

    private BorderPane buildLayout() {
        final HBox hbox = new HBox(5, analyzeButton);
        hbox.setPadding(new Insets(5, 5, 5, 5));

        final TabPane chartTabPane = new TabPane();
        final Tab tabCallCount = new Tab("Call Count", bcCallCount);
        final Tab tabCallTimes = new Tab("Call Times", bcCallTimes);
        chartTabPane.getTabs().addAll(tabCallCount, tabCallTimes);

        final BorderPane borderPane = new BorderPane();
        borderPane.setTop(hbox);
        borderPane.setCenter(chartTabPane);
        return borderPane;
    }

    private void onAnalyzeButtonClick(final Stage stage) {
        final File selectedFile = showFileDialog(stage);
        if (selectedFile != null) {
            // Asynchron die Analyse starten
            final AnalyzeTask task = new AnalyzeTask(selectedFile);
            final Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

            task.setOnSucceeded(evt -> populateCharts(selectedFile, task));
        }
    }

    private File showFileDialog(final Stage stage) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text / Log  Files", "*.txt", "*.log"),
                new ExtensionFilter("All Files", "*.*"));

        // gibt unschönerweise null zurück! 
        final File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
    }

    private void populateCharts(final File file, final AnalyzeTask task) {
        JavaFxUtils.populateBarChart(bcCallCount, file.getName(),
                task.getCallCounts());
        JavaFxUtils.populateBarChart(bcCallTimes, file.getName(),
                task.getCallTimes());
    }

    private static class AnalyzeTask extends Task<Void> {
        final File selectedFile;

        Map<String, Long> callCounts = new TreeMap<>();
        Map<String, Long> callTimes = new TreeMap<>();

        public AnalyzeTask(final File selectedFile) {
            this.selectedFile = selectedFile;
        }

        @Override
        public Void call() throws IOException {
            callCounts = analyzeCallCounts(selectedFile.toPath());
            callTimes = analyzeCallTimes(selectedFile.toPath());

            return null;
        }

        public Map<String, Long> getCallCounts() {
            return callCounts;
        }

        public Map<String, Long> getCallTimes() {
            return callTimes;
        }
    }

    public static void main(final String[] args) throws IOException {
        launch(args);
    }
}
