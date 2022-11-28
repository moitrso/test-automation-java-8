package org.seminar;

import static java.time.LocalDateTime.*;

public class ExampleUsageLambda {
    public static void main(String args[]) {

        PrintableLog printer = message -> System.out.println(now() + ": " + message);
        printer.show("I am printing from Lambda Expressions");

    }
}
