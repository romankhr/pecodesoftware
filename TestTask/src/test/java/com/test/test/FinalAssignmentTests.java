package com.test.test;

import com.test.BaseTest;
import com.test.pages.CabinetPage;
import com.test.pages.HomePage;
import com.test.pages.RegisterPage;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalAssignmentTests extends BaseTest {

    private HomePage homePage;
    private static final String PASSWORD = "12345678qQ!";
    private static final String NAME = "user";
    private String emailVerificationLink = "";


    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        homePage = new HomePage(driver);
    }

    //   -----------------------------Test 1-----------------------------
    @Test(priority = 1)
    public void fillRegistration() throws Exception {

        //Given user navigates to the web page prom.ua/uk

        //when user  click on register button, he navigates to the registration page and fill fields
        CabinetPage cabinetPage = homePage.clickOnRegisterButton(RegisterPage.class)
                .setName(NAME)
                .setEmail(getEmail())
                .setPassword(PASSWORD)
                .clickOnRegisterButton(CabinetPage.class);

        //Then On the cabinet page verify title
        Assert.assertTrue(cabinetPage.isTitleCorrect(), "Titles is not correct!!!");
    }

    //   -----------------------------Test 2-----------------------------
    @Test(priority = 2)
    public void verifyEmail() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://mailsac.com/api";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Mailsac-Key", getApiKey());

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET, "/addresses/" + getEmail() + "/messages");

        // Now let us print the body of the message to see what response
        // we have recieved from the server
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

        emailVerificationLink = getPromLink(responseBody);
        System.out.println("emailVerificationLink =>  " + emailVerificationLink);

        Assert.assertFalse(emailVerificationLink.isEmpty());
    }

    //   -----------------------------Test 3-----------------------------
    @Test(priority = 3)
    public void confirmEmail() {
        //Given user navigates by registration link
        driver.get(emailVerificationLink);
        //when user
        CabinetPage cabinetPage = new CabinetPage(driver);
        //Then On the cabinet page verify title
        Assert.assertTrue(cabinetPage.isTitleCorrect(), "Titles is not correct!!!");
    }

    private static String getPromLink(String text) {
        String link = "";

        String regex = "\\(?\\b(https://my[.]prom[.]ua/account/activate_email)[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while (m.find()) {
            String urlStr = m.group();
            if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
                urlStr = urlStr.substring(1, urlStr.length() - 1);
            }
            link = urlStr;
        }

        return link;
    }

}
