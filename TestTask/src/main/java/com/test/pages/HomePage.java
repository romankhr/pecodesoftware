package com.test.pages;


import com.test.framework.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class HomePage extends Page {

    protected RemoteWebDriver driver;
    private String registerLocator="//a[contains(text(),'зареєструватися')]";
    private String searchClickLocator="//div[@class='tfB0Bf']//input[1]";

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver=driver;
    }

//        public HomePage setWord(String searchTerm){
//            driver.findElement(By.xpath(searchLocator)).sendKeys(searchTerm);
//            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//            return this;
//        }

         public <T extends Page> T clickOnRegisterButton(Class<T> clazz) throws Exception {
             driver.findElement(By.xpath(registerLocator)).click();
             return PageFactory.newPage(driver, clazz);
    }


}
