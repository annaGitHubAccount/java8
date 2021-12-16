package de.anna.java8Aufgaben;

import de.anna.java8Aufgaben.pojo.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class PersonUtilsTest {

    List<Person> personList;

    @Before
    public void setUp(){

        personList = new ArrayList<>();
        personList.add(new Person("Anna", "Polanska", 17));
        personList.add(new Person("Michael", "Jackson", 30));
        personList.add(new Person("Freddie", "Mercury", 40));
        personList.add(new Person("Merlin", "Monrou", 40));
        personList.add(new Person("Bobby", "Brown", null));
    }


    @Test
    public void shouldPersonListNachNachnameSortieren(){

        List<String> expectedNachnamenList = Arrays.asList("Brown", "Jackson", "Mercury", "Monrou", "Polanska");
        List<String> nachnamenList = PersonUtils.personListPerNachnameSortieren(personList);

        Assert.assertEquals(expectedNachnamenList, nachnamenList);
    }

    @Test
    public void shouldPersonListNachAlterReversedSortieren(){

        List<Person> expectedPersonList = new ArrayList<>();
        expectedPersonList.add(new Person("Freddie", "Mercury", 40));
        expectedPersonList.add(new Person("Michael", "Jackson", 30));

        List<Person> personListMitAlter = PersonUtils.personListPerAlterSortieren(personList);

        Assert.assertEquals(expectedPersonList, personListMitAlter);
    }

    @Test
    public void shouldListeVonNachnamenAufBuchstabeLiefern(){

        List<String> expectedListVonNachnamen = new ArrayList<>();
        expectedListVonNachnamen.add("Mercury");

        List<String> listVonNachnamen = PersonUtils.listNachnamenAufBuchstabe(personList, "M");

        Assert.assertEquals(expectedListVonNachnamen, listVonNachnamen);
    }

    @Test
    public void shouldReturnPersonSurnameWithLetter(){

        List<Person> expectedPersonList = new ArrayList<>();
        expectedPersonList.add(new Person("Freddie", "Mercury", 40));

        List<Person> personListWithBuchstabe = PersonUtils.listNachnamenVonPersonenAufBuchstabe(personList, "M");

        Assert.assertEquals(expectedPersonList, personListWithBuchstabe);
    }

    @Test
    public void shouldReturnAnzahlVonPersonenAufBuchstabe(){

        long anzahlVonPersonen = PersonUtils.gibAnzahlVonPersonenAufBuchstabeZurueck(personList, "M");

        Assert.assertEquals(2, anzahlVonPersonen);
    }

    @Test
    public void berechneSummeVonPersonenAlterTest(){

        int summeVonPersonenAlter = PersonUtils.berechneSummeVonPersonenAlter(personList);

        Assert.assertEquals(127, summeVonPersonenAlter);
    }

    @Test
    public void berechneAverageVonPersonAlterTest(){

        OptionalDouble optionalDouble = PersonUtils.berechneAverageVonPersonAlter(personList);

        if(optionalDouble.isPresent()) {
            Assert.assertEquals(35.0, optionalDouble.getAsDouble(), 0);
        } else {
            Assert.fail();
        }
    }

}
