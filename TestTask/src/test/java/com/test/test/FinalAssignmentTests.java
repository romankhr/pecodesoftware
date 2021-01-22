package com.test.test;

import com.test.BaseTest;
import com.test.framework.SearchTermsDataProvider;
import com.test.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FinalAssignmentTests extends BaseTest {

    private HomePage homePage;
    private String searchTerm="snow";
    private SearchResultPage searchResultPage;

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
      homePage = new HomePage(driver);
    }

    @DataProvider(name = "getSearchTerms")
    public Object[][] getSearchTerms() {
        return SearchTermsDataProvider.getAllSearchTerms();
    }

    @Test(groups = "main", dataProvider = "getSearchTerms")
    public void verifySearchPageTest(String searchTerm) throws Exception {

        //Given user navigates to the web page https://www.google.com/

        //when user inserts search word in the "Search field" and click on search button, he navigates to the search result page
        searchResultPage=homePage.setWord(searchTerm).clickOnSearchButton(SearchResultPage.class);

        //Then On the the search result page verify that ALL search titles contains the search word
        Assert.assertTrue(searchResultPage.isWordInTitle(searchTerm), "Some titles of the search results doesn't contains the search word!!!");
    }
}
