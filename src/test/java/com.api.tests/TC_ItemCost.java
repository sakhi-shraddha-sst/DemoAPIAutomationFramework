package com.api.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;

public class TC_ItemCost {

   @Test
    public void getItemCost(){


       ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
       options.addArguments("-disable-notifications");

       WebDriver driver = new ChromeDriver(options);


       driver.get("https://www.saucedemo.com/");

       WebElement username =  driver.findElement(By.xpath("//input[@data-test='username']"));
       WebElement password =  driver.findElement(By.xpath("//input[@data-test='password']"));

       username.sendKeys("standard_user");
       password.sendKeys("secret_sauce");

       WebElement loginBTN =  driver.findElement(By.xpath("//input[@data-test='login-button']"));

        loginBTN.click();

       List<WebElement>ItemDescCard = driver.findElements(By.xpath("//div[@data-test='inventory-item-description']"));


        for(WebElement e : ItemDescCard){

            WebElement ItemName = e.findElement(By.xpath(".//div[@data-test='inventory-item-name']"));

            WebElement ItemCost = e.findElement(By.xpath(".//div[@data-test='inventory-item-price']"));

            String item_name = ItemName.getText();
            String item_cost = ItemCost.getText();

           System.out.println("Item Name :" + item_name + " and " + "Item Cost : " + item_cost);
        }




       //div[@data-test='inventory-item-description']//child::div[2]


        driver.quit();


    }


}
