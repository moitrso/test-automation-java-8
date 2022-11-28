package org.seminar.fi.practise;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateDemo {
    public static void main(String args[]){

        Predicate<String> nameFilter = i->i.toLowerCase().startsWith("s");

        System.out.println(nameFilter.test("Sarah"));
        System.out.println(nameFilter.test("David"));
        System.out.println("---------------------------------------------------");

        List<String> names = Arrays.asList("John", "Smith", "Samueal", "Catley", "Sie");
        List<String> namesWithS = names.stream().filter(nameFilter).collect(Collectors.toList());
        namesWithS.forEach(System.out::println);

    }
}
