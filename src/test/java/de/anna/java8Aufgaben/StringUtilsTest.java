package de.anna.java8Aufgaben;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StringUtilsTest {


/*
    4 sposoby uzycia Function @FunctionalInterface :

    @Test
    public void shouldTextUmwandelnTest() {

        List<String> stringList = new ArrayList<>();
        stringList.add("ania");
        stringList.add("zosia");

        List<String> expectedStringList = new ArrayList<>();
        expectedStringList.add("ANIA");
        expectedStringList.add("ZOSIA");

        //GrossGeschriebenUmwandlung grossGeschriebenFunction = new GrossGeschriebenUmwandlung();
        Function grossGeschriebenFunction = new GrossGeschriebenUmwandlung();

        List<String> changedList = StringUtils.textUmwandlung(stringList, grossGeschriebenFunction);

        Assert.assertEquals(expectedStringList.size(), changedList.size());
        Assert.assertEquals(expectedStringList, changedList);
    }

    @Test
    public void shouldTextUmwandelnTest2() {

        List<String> stringList = new ArrayList<>();
        stringList.add("ania");
        stringList.add("zosia");

        List<String> expectedStringList = new ArrayList<>();
        expectedStringList.add("ANIA");
        expectedStringList.add("ZOSIA");

        Function grossGeschriebenFunction = new Function<String, String>() {

            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };

        List<String> changedList = StringUtils.textUmwandlung(stringList, grossGeschriebenFunction);

        Assert.assertEquals(expectedStringList.size(), changedList.size());
        Assert.assertEquals(expectedStringList, changedList);

    }

    @Test
    public void shouldTextUmwandelnTest3() {

        List<String> stringList = new ArrayList<>();
        stringList.add("ania");
        stringList.add("zosia");

        List<String> expectedStringList = new ArrayList<>();
        expectedStringList.add("ANIA");
        expectedStringList.add("ZOSIA");

        List<String> changedList = StringUtils.textUmwandlung(stringList,
                new Function<String, String>() {

                    @Override
                    public String apply(String s) {
                        return s.toUpperCase();
                    }
                });

        Assert.assertEquals(expectedStringList.size(), changedList.size());
    }


    @Test
    public void shouldTextUmwandelnTest4() {

        List<String> stringList = new ArrayList<>();
        stringList.add("ania");
        stringList.add("zosia");

        List<String> expectedStringList = new ArrayList<>();
        expectedStringList.add("ANIA");
        expectedStringList.add("ZOSIA");

        List<String> changedList = StringUtils.textUmwandlung(stringList, s -> s.toUpperCase());

        Assert.assertEquals(expectedStringList.size(), changedList.size());
        Assert.assertEquals(expectedStringList, changedList);
    }*/


    @Test
    public void shouldTextUmwandelnTest5() {

        List<String> stringList = new ArrayList<>();
        stringList.add("ania");
        stringList.add("zosia");

        List<String> expectedStringList = new ArrayList<>();
        expectedStringList.add("ANIA");
        expectedStringList.add("ZOSIA");

        List<String> changedList = StringUtils.textUmwandlung(stringList, StringUtilsTest::umwandleGrossGeschriebenReferenceMethod);

        Assert.assertEquals(expectedStringList.size(), changedList.size());
        Assert.assertEquals(expectedStringList, changedList);
    }

    @Test
    public void shouldListVonStringToStringUmwandelnTest(){

        List<String> cities = Arrays.asList("Milan", "London", "New York", "San Francisco");
        String expectedString = "Milan, London, New York, San Francisco";

        String newString = StringUtils.stringListToStringUmwandlung(cities);

        Assert.assertEquals(expectedString, newString);
    }





    private static String umwandleGrossGeschriebenReferenceMethod(String string){
        return string.toUpperCase();
    }

    private static class GrossGeschriebenUmwandlung implements Function<String, String> {
        @Override
        public String apply(String s) {
            return s.toUpperCase();
        }
    }

}
