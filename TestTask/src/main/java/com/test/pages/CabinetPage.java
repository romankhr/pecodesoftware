package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
public class CabinetPage extends Page {
    private String cabinetTitleLocator="//div[@class='b-content__header']";

    public CabinetPage(RemoteWebDriver driver) {
        super(driver);
    }
    public boolean isTitleCorrect(){
        return driver.findElement(By.xpath(cabinetTitleLocator)).getText().equalsIgnoreCase("Налаштування профілю");
    }

}
