package org.seminar.fi.practise;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String args[]){

        Consumer<String> textConsumer = i-> System.out.println(i + " "+ i.length());
        textConsumer.accept("Luxoft");

        System.out.println("---------------------------------------------------");


        List<String> cities = Arrays.asList("Germany","Poland","India","Sweden","Norway");
        cities.forEach(textConsumer);
    }

}
