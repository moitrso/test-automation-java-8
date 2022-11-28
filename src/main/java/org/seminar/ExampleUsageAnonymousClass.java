package org.seminar;
import static java.time.LocalDateTime.*;
public class ExampleUsageAnonymousClass {
    public static void main(String args[]){
        PrintableLog logger = new PrintableLog() {
            @Override
            public void show(String message) {
                System.out.println(now() + ": "+ message);
            }
        };
        logger.show("I am printing from Anonymous class");
    }
}
