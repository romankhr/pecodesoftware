package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchResultPage extends Page {

    private String searchResultsLocator="//h3[@class='LC20lb DKV0Md']";

    public SearchResultPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isWordInTitle(String searchTerm) {

        for (WebElement element : getElements(By.xpath(searchResultsLocator))) {
            System.out.println(element.getText());
            if (!element.getText().toLowerCase().contains(searchTerm.toLowerCase()))
                return false;
        }
        return true;
    }

}
