package com.test.pages;

import com.test.framework.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class RegisterPage extends Page {
    private String nameLocator="//form[1]/div[2]/input";
    private String eMailLocator="//form[1]/div[3]/input";
    private String passwordLocator="//form[1]/div[4]/input";
    private  String  submitButtonLocator="//button[@class='lp-button lp-button_width_full lp-button_theme_blue lp-button_height_50']";

    public RegisterPage(RemoteWebDriver driver) {
        super(driver);
    }

    public RegisterPage setName(String name){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
           driver.findElement(By.xpath(nameLocator)).sendKeys(name);

            return this;
        }

    public RegisterPage setEmail(String email){
        driver.findElement(By.xpath(eMailLocator)).sendKeys(email);
        return this;
    }

    public RegisterPage setPassword(String password){
        driver.findElement(By.xpath(passwordLocator)).sendKeys(password);
        return this;
    }

    public <T extends Page> T clickOnRegisterButton(Class<T> clazz) throws Exception {
        driver.findElement(By.xpath(submitButtonLocator)).click();
        return PageFactory.newPage(driver, clazz);
    }
}
