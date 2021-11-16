package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class verifyLinksTests {
    WebDriver driver;
    String url1 = "https://www.talent-smart.co.uk/";
    String url2 = "https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java";

    @BeforeTest

    public void beforeSuite(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test(priority = 1)
    public void verifyLinks() throws IOException {
        driver.get(url1);

        List list = driver.findElements(By.tagName("a"));               //list out all elements in ui
//        List<WebElement> lnks = driver.findElements(By.tagName("a"));   //listing all elements in ui for iteration

        Iterator<WebElement> it = list.iterator();       //collecting travers the each element on web interface

        SoftAssert sa = new SoftAssert();             //testng keyword for throws exceptions

        while (it.hasNext()) {

            String url = it.next().getAttribute("href");
            System.out.println(url);            //here printing corresponding web elements URL links on console

            HttpURLConnection connect = (HttpURLConnection) (new URL(url).openConnection());
            connect.connect();
            sa.assertNotEquals(connect.getResponseCode(), 404);
            System.out.println(connect.getResponseMessage());     //here printing response message on console
        }
        sa.assertAll();
    }
    @Test(priority = 2)
    public void countRows(){
        driver.get(url2);

        List<WebElement> elements = driver.findElements((By.xpath("//*[@class='grid versions']//tbody[1]")));
                                //pointing to particular row and lists all elements

        int count  = elements.size();       //counting total elements
        System.out.println("Total no of rows : "+count);        //print total size for tbody on console

        for(int i=1; i<=count; i++){
        System.out.println(driver.findElement(By.xpath("//*[@class= 'grid versions']//tbody[1]/tr['+i+']/td[5]")).getText());
                            //print version released date on console
        }

    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }


























    }




