package chapter5_javafx8.new_controls;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Modellklasse zum Einsatz in der TreeTableView-Komponente.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class Person {
    private final String name;

    private final Integer age;

    private final SimpleIntegerProperty size = new SimpleIntegerProperty();

    public Person(final String name, final Integer age, final Integer size) {
        this.name = name;
        this.age = age;
        this.size.setValue(size);
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSize() {
        return size.getValue();
    }
}