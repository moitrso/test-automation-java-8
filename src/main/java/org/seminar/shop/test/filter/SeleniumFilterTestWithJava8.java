package org.seminar.shop.test.filter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeleniumFilterTestWithJava8 {

    public static void main(String args[]) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/desktops");

        List<WebElement> product_items_before_filter = driver.findElements(By.xpath("//div[@class='details']"));

        Map<String, Double> namesWithPriceBeforeFilterMap = new HashMap<>();

        System.out.println("Products with Prices before Applying any Filter: ");

        product_items_before_filter.forEach(i -> namesWithPriceBeforeFilterMap.put(i.findElement(By.tagName("h2")).getText(),
                        Double.parseDouble(i.findElement(By.tagName("span")).getText())));

        namesWithPriceBeforeFilterMap.entrySet().forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        //Apply filter under 1000
        driver.findElement(By.xpath("//ul[@class='price-range-selector']//li[1]/a")).click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.className("remove-price-range-filter")));


        List<WebElement> product_items_after_filter = driver.findElements(By.xpath("//div[@class='details']"));
        Map<String, Double> namesWithPriceAfterFilterMap = new HashMap<>();

        System.out.println("Products with Prices After Applying Filter on UI : ");

        product_items_after_filter.forEach(i -> namesWithPriceAfterFilterMap.put(i.findElement(By.tagName("h2")).getText(),
                        Double.parseDouble(i.findElement(By.tagName("span")).getText())));
        namesWithPriceAfterFilterMap.entrySet().forEach(System.out::println);


        System.out.println("---------------------------------------------------");
        System.out.println("Products with Prices After Applying any Manual Filter in Code : ");

        Map<String, Double> manualFilterBefore = namesWithPriceBeforeFilterMap.entrySet().stream()
                        .filter(e-> e.getValue()<1000.00)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));;
        manualFilterBefore.entrySet().forEach(System.out::println);

        System.out.println("---------------------------------------------------");
        if (manualFilterBefore.equals(namesWithPriceAfterFilterMap)) {
            System.out.println("Filtering works Correctly! ");
        }
        driver.quit();

    }
}
