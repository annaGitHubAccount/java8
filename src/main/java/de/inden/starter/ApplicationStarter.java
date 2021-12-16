package de.inden.starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

// Ideales Beipiel f�r Advanced Java, Process starten, Ausgabe lesen
public class ApplicationStarter extends Application {
    public void start(final Stage stage) throws URISyntaxException, IOException {
        final TreeItem<ApplicationInfo> root = createTreeData();

        final List<TreeTableColumn<ApplicationInfo, ?>> columns = createColumns();

        final TreeTableView<ApplicationInfo> treeTableView = new TreeTableView<ApplicationInfo>(root);
        treeTableView.setPrefSize(1200, 750);

        treeTableView.getColumns().addAll(columns);

        // Spaltenkonfiguration
        treeTableView.setTableMenuButtonVisible(true);

        // Root nicht anzeigen -- weil keine Zusatzinfo
        treeTableView.setShowRoot(false);

        final TreeTableViewSelectionModel<ApplicationInfo> selectionModel = treeTableView.getSelectionModel();
        final Button startButton = new Button("Start");
        startButton.disableProperty().set(true);

        final Label message = new Label("Console output:");
        final TextArea consoleOutput = new TextArea("");
        consoleOutput.setPrefSize(1200, 750);
        consoleOutput.setFont(Font.font("MonoSpaced"));
        startButton.setOnAction(event -> {

            startButton.disableProperty().set(true);
            consoleOutput.setText("");

            startProgram(selectionModel, message, consoleOutput, startButton);
        });

        selectionModel.selectedItemProperty().addListener(property -> {

            final TreeItem<ApplicationInfo> selectedItem = selectionModel.getSelectedItem();
            // Binding nutzen?
            startButton.disableProperty().set(selectedItem == null || !selectedItem.isLeaf());
        });

        final VBox selectionVbox = new VBox(7);
        selectionVbox.setPadding(new Insets(7, 7, 7, 7));
        selectionVbox.getChildren().addAll(treeTableView, startButton);

        final VBox outputVBox = new VBox(7);
        outputVBox.setPadding(new Insets(7, 7, 7, 7));
        outputVBox.getChildren().addAll(message, consoleOutput);

        final SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.getItems().addAll(selectionVbox, outputVBox);

        stage.setScene(new Scene(splitPane, 1200, 800));
        stage.setTitle("Java 8 Demo-Programm-Starter");
        stage.show();
    }

    private void startProgram(TreeTableViewSelectionModel<ApplicationInfo> selectionModel, final Label message,
                              final TextArea consoleOutput, final Button startButton) {
        final TreeItem<ApplicationInfo> selectedItem = selectionModel.getSelectedItem();

        if (selectedItem != null && selectedItem.isLeaf()) {

            Task<String> task = new Task<String>() {
                @Override
                public String call() throws InterruptedException {

                    final ApplicationInfo appInfo = selectedItem.getValue();
                    final String packageAndClass = appInfo.getPackageName() + "." + appInfo.getName();

                    updateMessage("Starting " + packageAndClass);
                    // Thread.sleep(500);

                    // more time consuming actions
                    try {
                        final Runtime rt = Runtime.getRuntime();
                        final Process proc = rt.exec("java -cp project.jar " + packageAndClass);

                        proc.waitFor();

                        // read output
                        String content = readContentFromStream(proc.getInputStream());
                        String contentErr = readContentFromStream(proc.getErrorStream());

                        updateMessage("Console output:");

                        final String completeContent = content + (contentErr.isEmpty() ? "" : "\nErr:\n" + contentErr);
                        Platform.runLater(() -> {
                            consoleOutput.setText(completeContent);
                            startButton.disableProperty().set(false);
                        });

                        return completeContent;
                    } catch (final Exception e) {
                        updateMessage("Error occured:");

                        Platform.runLater(() -> {
                            consoleOutput.setText(e.toString());
                            startButton.disableProperty().set(false);
                        });
                        return "";
                    }
                }

                private String readContentFromStream(final InputStream is) throws IOException {
                    final InputStreamReader isr = new InputStreamReader(is);
                    final BufferedReader br = new BufferedReader(isr);

                    String content = "";
                    while (br.ready()) {
                        content += br.readLine();
                        content += "\n";
                    }

                    return content;
                }
            };

            message.textProperty().bind(task.messageProperty());

            final Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        }
    }

    private TreeItem<ApplicationInfo> createTreeData() throws URISyntaxException, IOException {

        final List<ApplicationInfo> appInfos = retriveAppInfos();

        // Dummy Root Node
        final TreeItem<ApplicationInfo> root = createGroupTrreItem("", "Alle Kapitel");

        // Fill in content
        Integer currentChapter = -1;
        TreeItem<ApplicationInfo> currentGroup = null;
        for (final ApplicationInfo appInfo : appInfos) {
            final Integer chapter = appInfo.getChapter();
            if (!chapter.equals(currentChapter)) {
                currentChapter = chapter;
                currentGroup = createGroupTrreItem("" + chapter, "Kapitel " + chapter);
                root.getChildren().add(currentGroup);
            }

            final TreeItem<ApplicationInfo> childNode = new TreeItem<>(appInfo);
            currentGroup.getChildren().add(childNode);
        }

        return root;
    }

    private List<ApplicationInfo> retriveAppInfos() throws URISyntaxException, IOException {
        final InputStream is = this.getClass().getResourceAsStream("Applications.txt");
        final List<ApplicationInfo> appInfos = ApplicationInfoImporter.importAppicationInfos(is);
        return appInfos;
    }

    private TreeItem<ApplicationInfo> createGroupTrreItem(final String chapter, final String groupName) {
        return new TreeItem<>(new ApplicationInfo(chapter, "", groupName, "", ""));
    }

    private List<TreeTableColumn<ApplicationInfo, ?>> createColumns() {
        return Arrays.asList(createColumn("Name", "name", 250), createColumn("Kapitel", "chapterAndSection", 75),
                createColumn("Seite(n)", "pages", 75),
                withToolTip(createColumn("Kommentar", "comment", 750)));
    }

    private <V> TreeTableColumn<ApplicationInfo, V> createColumn(final String columnTitle, final String attributeName,
                                                                 final int prefWidth) {
        final TreeTableColumn<ApplicationInfo, V> column = new TreeTableColumn<>(columnTitle);
        column.setPrefWidth(prefWidth);

        column.setCellValueFactory(new TreeItemPropertyValueFactory<ApplicationInfo, V>(attributeName));

        return column;
    }

    private <V> TreeTableColumn<ApplicationInfo, V> withToolTip(final TreeTableColumn<ApplicationInfo, V> column) {
        // spezieller Tooltip, wenn Zelleninhalt zu breit f�r tats�cliche derzeitige Breite
        // Standardverhalten ist Ellipsis und somit "..."
        // ziemlich bl�d, dass man f�r innere Klassen keinen Diamon Operator nutzen kann ... 
        // Ganz besonders st�rend ist dies eben bei JavaFX und den Callbacks
        final Callback<TreeTableColumn<ApplicationInfo, V>, TreeTableCell<ApplicationInfo, V>> cellFactory = new Callback<TreeTableColumn<ApplicationInfo, V>, TreeTableCell<ApplicationInfo, V>>() {
            public TreeTableCell call(TreeTableColumn p) {
                return new CustomToolTipCell();
            }
        };

        column.setCellFactory(cellFactory);

        return column;
    }

    public static void main(final String[] args) {
        launch(args);
    }

    class CustomToolTipCell extends TreeTableCell<ApplicationInfo, String> {
        public CustomToolTipCell() {
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                final String textToDisplay = getItem() == null ? "" : getItem().toString();
                setText(textToDisplay);
                setGraphic(null);

                // Fehler in JavaFX, Text wird immer kleiner
                if (!textToDisplay.isEmpty() && getTooltip() == null) {
                    final Tooltip tip = new Tooltip(textToDisplay);
                    tip.setFont(Font.font(16));
                    tip.setMaxWidth(800);
                    tip.setWrapText(true);
                    Tooltip.install(this, tip);

                    tip.setStyle("-fx-font-size: 16pt; -fx-background-radius: 0 0 0 0;"
                            + "-fx-background-color:	linear-gradient(dodgerblue, lightblue  );");
                } else {
                    if (getTooltip() != null) {
                        getTooltip().setStyle("-fx-font-size: 16pt; -fx-background-radius: 0 0 0 0;"
                                + "-fx-background-color:	linear-gradient(dodgerblue, red  );");
                    }
                }
            }
        }
    }
}