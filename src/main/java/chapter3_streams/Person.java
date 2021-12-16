package chapter3_streams;

import java.util.Objects;

/**
 * Diese Klasse modelliert eine person auf simple Weise.
 * Dient fuer viele Beispiele als Grundlage.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014, 2015 by Michael Inden
 */
public class Person {
    private final String name;
    private final int age;
    private final Gender gender;
    private String favoriteColor;
    private String city;

    public Person(final String name, final int age) {
        this(name, age, Gender.MALE);
    }

    public Person(final String name, final int age, final String city) {
        this(name, age, Gender.MALE, noFavoriteColor(), city);
    }

    public Person(final String name, final int age, final Gender gender) {
        this(name, age, gender, noFavoriteColor(), noCity());
    }

    public Person(final String name, final int age, final Gender gender, final String city) {
        this(name, age, gender, noFavoriteColor(), city);
    }

    public Person(final String name, final int age, final Gender gender, final String favoriteColor, final String city) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.favoriteColor = favoriteColor;
        this.city = city;
    }

    private static String noFavoriteColor() {
        return "";
    }

    private static String noCity() {
        return "";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public boolean livesIn(final String city) {
        return Objects.equals(this.city, city);
    }

    public void setFavoriteColor(final String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        if (favoriteColor.isEmpty() && city.isEmpty()) {
            return "Person [name=" + name + ", age=" + age + ", gender=" + gender + "]";
        }
        if (favoriteColor.isEmpty() && !city.isEmpty()) {
            return "{" + name + " (" + age + "/" + gender.toString().charAt(0) + ") from " + city + "}";
        }
        return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", favoriteColor=" + favoriteColor
                + ", city=" + city + "]";
    }

    public void prettyPrint() {
        System.out.println("Person [" + name + " (" + age + "/" + gender.toString().charAt(0) + ") from " + city + "]");
    }
}