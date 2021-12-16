package de.anna.java8Aufgaben;

import de.anna.java8Aufgaben.pojo.Person;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class PersonUtils {


    public static List<String> personListPerNachnameSortieren(List<Person> personList) {

        // nachname.sorted((nachname1, nachname2) -> nachname1.compareTo(nachname2));
        return personList.stream()
                .map(person -> person.getNachname())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }


    public static List<Person> personListPerAlterSortieren(List<Person> personList) {

        return personList.stream()
                .sorted(Comparator.comparing(Person::getAlter).reversed())
                .filter(person -> person.getAlter() > 18)
                .collect(Collectors.toList());

    }

    public static List<String> listNachnamenAufBuchstabe(List<Person> personList, String buchstabe) {

        return personList.stream()
                .map(person -> person.getNachname())
                .filter(x -> x.startsWith(buchstabe))
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Person> listNachnamenVonPersonenAufBuchstabe(List<Person> personList, String buchstabe) {

        return personList.stream()
                .filter(p -> p.getNachname().startsWith(buchstabe))
                .sorted(Comparator.comparing(Person::getNachname))
                .collect(Collectors.toList());
    }

    public static long gibAnzahlVonPersonenAufBuchstabeZurueck(List<Person> personList, String buchstabe) {

        return personList.stream()
                .map(person -> person.getNachname())
                .filter(nachname -> nachname.startsWith(buchstabe))
                .count();
    }

    public static int berechneSummeVonPersonenAlter(List<Person> personList){

        return personList.stream()
                .map(person -> person.getAlter())
                .filter(alter -> alter != null)
                .mapToInt(alter -> alter)
                .sum();
    }

    public static OptionalDouble berechneAverageVonPersonAlter(List<Person> personList){

        return personList.stream()
                .filter(person -> person.getNachname().contains("c"))
                .mapToInt(Person::getAlter)
                .average();
    }
}
