package chapter5_javafx8.new_controls;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

/**
 * Beispielprogramm zeigt die mit JDK 8 eingefuehrte TreeTableView-Komponente.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014, 2015 by Michael Inden
 */
public class TreeTableViewExample extends Application {
    public void start(final Stage stage) {
        final TreeItem<Person> root = createTreeData();

        final List<TreeTableColumn<Person, ?>> columns = createColumns();

        final TreeTableView<Person> treeTableView = new TreeTableView<Person>(root);
        treeTableView.getColumns().addAll(columns);

        stage.setScene(new Scene(treeTableView, 300, 250));
        stage.setTitle("TreeTableViewExample");
        stage.show();
    }

    private TreeItem<Person> createTreeData() {
        final Person micha = new Person("Micha", 43, 184);
        final Person tom = new Person("Tom", 22, 177);
        final Person lili = new Person("Lili", 34, 170);

        final Person tim = new Person("Tim", 43, 181);
        final Person jens = new Person("Jens", 47, 175);
        final Person andy = new Person("Andy", 31, 178);

        final TreeItem<Person> root = createGroupTrreItem("All Persons");

        final TreeItem<Person> group1 = createGroupTrreItem("Group 1");
        final TreeItem<Person> childNode1_1 = new TreeItem<>(micha);
        final TreeItem<Person> childNode1_2 = new TreeItem<>(tom);
        final TreeItem<Person> childNode1_3 = new TreeItem<>(lili);

        final TreeItem<Person> group2 = createGroupTrreItem("Group 2");
        final TreeItem<Person> childNode2_1 = new TreeItem<>(tim);
        final TreeItem<Person> childNode2_2 = new TreeItem<>(jens);
        final TreeItem<Person> childNode2_3 = new TreeItem<>(andy);

        group1.getChildren().setAll(childNode1_1, childNode1_2, childNode1_3);
        group2.getChildren().setAll(childNode2_1, childNode2_2, childNode2_3);
        root.getChildren().setAll(group1, group2);

        root.setExpanded(true);
        group1.setExpanded(true);

        return root;
    }

    private TreeItem<Person> createGroupTrreItem(final String groupName) {
        return new TreeItem<>(new Person(groupName, null, -1));
    }

    private List<TreeTableColumn<Person, ?>> createColumns() {
        return Arrays.asList(createColumn("Name", "name", 125),
                createColumn("Age", "age", 50),
                createColumn("Size in cm", "size", 70)
        );
    }

    private <V> TreeTableColumn<Person, V> createColumn(final String columnTitle,
                                                        final String attributeName,
                                                        final int prefWidth) {
        final TreeTableColumn<Person, V> column = new TreeTableColumn<>(columnTitle);
        column.setPrefWidth(prefWidth);

        column.setCellValueFactory(
                new TreeItemPropertyValueFactory<Person, V>(attributeName));

        return column;
    }

    public static void main(final String[] args) {
        launch(args);
    }
}