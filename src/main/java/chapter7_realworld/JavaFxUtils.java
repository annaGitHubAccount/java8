package chapter7_realworld;

import static javafx.collections.FXCollections.observableArrayList;

import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 * Utilty-Klasse zur Aufbereitung eines BarCharts.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class JavaFxUtils {
    public static void populateBarChart(final BarChart<String, Number> barChart, final String name,
                                        final Map<String, Long> originalData) {

        barChart.setAnimated(true);

        final ObservableList<Series<String, Number>> dataForMap = toXyChartDataForMap(name, originalData);
        for (final Series<String, Number> currentSeries : dataForMap) {
            barChart.getData().add(currentSeries);
        }

        // so wird der BarChart und die Legende immer korrekt gezeichnet ...
        barChart.setAnimated(false);
    }

    public static ObservableList<XYChart.Series<String, Number>> toXyChartDataForMap(final String name,
                                                                                     final Map<String, Long> map) {
        final Series<String, Number> series = new Series<>();
        series.setName(name);
        for (final Map.Entry<String, Long> entry : map.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        final ObservableList<XYChart.Series<String, Number>> chartData = observableArrayList();
        chartData.add(series);
        return chartData;
    }
}