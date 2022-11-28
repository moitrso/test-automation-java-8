package org.seminar.fi.practise;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionDemo {
    public static void main(String args[]){
        Function<String,String> function = i-> i + "_TestData";
        System.out.println(function.apply("Laura"));

        System.out.println("---------------------------------------------------");

        List<String> names = Arrays.asList("Leon","Filip","Adam","John","Peter");
        List<String> modifiedNames = names.stream().map(function).collect(Collectors.toList());
        modifiedNames.forEach(System.out::println);



    }
}
