package org.seminar.shop.test.sort;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeleniumSortingTestWithoutJava8 {
    public static void main(String args[]) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/jewelry");

        List<WebElement> product_items_before_sort = driver.findElements(By.xpath("//h2[@class='product-title']"));
        List<String> product_names_before_sort = new ArrayList<>();

        System.out.println("Products Before Sorting: ");
        for (WebElement e : product_items_before_sort) {
            product_names_before_sort.add(e.getText());
            System.out.println(e.getText());
        }
        System.out.println("---------------------------------------------------");
        //Selecting the sort option on UI
        Select sortbydrp = new Select(driver.findElement(By.id("products-orderby")));
        sortbydrp.selectByVisibleText("Name: A to Z");

        List<WebElement> product_items_after_sort = driver.findElements(By.xpath("//h2[@class='product-title']"));
        List<String> product_names_after_sort = new ArrayList<>();

        System.out.println("Products After Sorting: ");
        for (WebElement e : product_items_after_sort) {
            product_names_after_sort.add(e.getText());
            System.out.println(e.getText());
        }

        //sort the before list using Collections.sort and compare with after
        Collections.sort(product_names_before_sort);

        System.out.println("---------------------------------------------------");
        if (product_names_before_sort.equals(product_names_after_sort)) {
            System.out.println("Alphabetical sorting works correctly! ");
        }

        driver.quit();

    }
}
