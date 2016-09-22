package edu.csumb.cst438fa16.webdriving;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by ndavidson on 9/20/16.
 */
public class MyCSUMBEDU {


    public MyCSUMBEDU(){
        super();
    }
    public static void main(String[] args){
        List<Object[]> recorded = new ArrayList<Object[]>();
        String user = System.getProperty("user");
        String pass = System.getProperty("pass");
        Date d = new Date();
        long check = d.getTime();

        recorded.add(new Object[]{ "Login Before", d.getTime()-check });
        WebDriver driver = new ChromeDriver();
        driver.get("http://my.csumb.edu");
        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.presenceOfElementLocated(
                        By.name("username")));
        recorded.add(new Object[]{ "Login After", d.getTime()-check });
        check = d.getTime();
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(user);
        element = driver.findElement(By.name("password"));
        element.sendKeys(pass);
        driver.findElement(By.name("submit")).click();
        recorded.add(new Object[]{ "Time to submit", d.getTime()-check });
        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.titleIs("Dashboard | Cal State Monterey Bay"));

        driver.get("http://ilearn.csumb.edu");

        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.titleIs("CSUMB iLearn"));

        driver.findElement(By.className("loginbutton")).click();

        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.presenceOfElementLocated(By.className("userbutton")));

        driver.findElement(By.id("shortsearchbox")).sendKeys("CST238");
        driver.findElement(By.id("coursesearch")).submit();

        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.titleIs("CSUMB iLearn : Search results"));

        List<WebElement> elements = driver.findElements(By.className("coursebox"));

        if(elements.size()>0){
            elements.get(0).findElements(By.tagName("a")).get(0).click();
        }

        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.titleContains("FA"));

        driver.findElement(By.className("teachers")).findElements(By.tagName("a")).get(0).click();

        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.titleContains(" Public profile"));

        driver.findElement(By.className("breadcrumb")).findElements(By.tagName("a")).get(0).click();

        (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.titleIs("CSUMB iLearn"));

        driver.quit();
    }
}
