package com.test.framework;

public class SearchTermsDataProvider {

    public static Object[][] getAllSearchTerms() {

        Object[][] searchTerms = new Object[5][1];
        searchTerms[0][0] = SearchTerms.SELENIUM.getSearchTerm();
        searchTerms[1][0] = SearchTerms.WINTER.getSearchTerm();
        searchTerms[2][0] = SearchTerms.SNOW.getSearchTerm();
        searchTerms[3][0] = SearchTerms.TREE.getSearchTerm();
        searchTerms[4][0] = SearchTerms.EUROPE.getSearchTerm();
        return searchTerms;
    }

}
