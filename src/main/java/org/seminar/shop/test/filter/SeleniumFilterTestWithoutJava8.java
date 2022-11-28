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


public class SeleniumFilterTestWithoutJava8 {

    public static void main(String args[]) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/desktops");

        List<WebElement> product_items_before_filter = driver.findElements(By.xpath("//div[@class='details']"));

        Map<String,Double> namesWithPriceBeforeFilterMap = new HashMap<>();

        System.out.println("Products with Prices before Applying any Filter: ");

        for(WebElement e:product_items_before_filter){
            String name = e.findElement(By.tagName("h2")).getText();
            Double price = Double.parseDouble(e.findElement(By.tagName("span")).getText());
            namesWithPriceBeforeFilterMap.put( name, price );
            System.out.println(name + " " + price);
        }
        System.out.println("---------------------------------------------------");

        //Apply filter under 1000
        driver.findElement(By.xpath("//ul[@class='price-range-selector']//li[1]/a")).click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.className("remove-price-range-filter")));


        List<WebElement> product_items_after_filter = driver.findElements(By.xpath("//div[@class='details']"));
        Map<String,Double> namesWithPriceAfterFilterMap = new HashMap<>();

        System.out.println("Products with Prices After Applying Filter on UI : ");

        for(WebElement e:product_items_after_filter){
            String name = e.findElement(By.tagName("h2")).getText();
            Double price = Double.parseDouble(e.findElement(By.tagName("span")).getText());
            namesWithPriceAfterFilterMap.put( name, price );
            System.out.println(name + " " + price);
        }

        System.out.println("---------------------------------------------------");
        System.out.println("Products with Prices After Applying any Manual Filter in Code : ");

        Map<String, Double> manualFilterBefore = new HashMap<>();
        for(Map.Entry<String, Double> e: namesWithPriceBeforeFilterMap.entrySet()){
            if(e.getValue()<1000.00){
                manualFilterBefore.put(e.getKey(),e.getValue());
                System.out.println(e.getKey() + " " + e.getValue());
            }
        }

        System.out.println("---------------------------------------------------");
        if (manualFilterBefore.equals(namesWithPriceAfterFilterMap)) {
            System.out.println("Filtering works Correctly! ");
        }

        driver.quit();



    }
}
