package com.test.framework;

public enum SearchTerms {
    SELENIUM("Selenium"),
    WINTER("Winter"),
    SNOW("Snow"),
    TREE("Tree"),
    EUROPE("Europe");
    private final String searchTerm;

    SearchTerms(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }
}
