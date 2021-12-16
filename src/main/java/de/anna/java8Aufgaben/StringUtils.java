package de.anna.java8Aufgaben;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {


    public static List<String> stringListToUpperCase(List<String> stringList) {

        List<String> stringListGrossGeschrieben = new ArrayList<>();

        for (String string : stringList) {
            String toUpperCase = string.toUpperCase();
            stringListGrossGeschrieben.add(toUpperCase);
        }

        return stringListGrossGeschrieben;
    }

    public static List<String> stringListUmwandlungToUpperCaseJava8(List<String> stringList) {

        return stringList.stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
    }


    public static List<String> textUmwandlung(List<String> stringList, Function<String, String> function){

        return stringList.stream().
                map(function).
                collect(Collectors.toList());
    }


    public static String stringListToStringUmwandlung(List<String> stringList){

        String newString = String.join(", ", stringList);

        return newString;
    }



}
