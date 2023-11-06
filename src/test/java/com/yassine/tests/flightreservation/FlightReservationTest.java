package com.yassine.tests.flightreservation;

import com.yassine.tests.AbstractTest;
import com.yassine.tests.flightreservation.model.FlightReservationTestData;
import com.yassine.util.Config;
import com.yassine.util.Constants;
import com.yassine.util.JsonUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yassine.pages.flightreservation.*;


public class FlightReservationTest extends AbstractTest {
    private String noOfPassengers;
    private String expectedPrice;
    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) {
       this.testData= JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);

        this.noOfPassengers = this.testData.noOfPassengers();
        this.expectedPrice = this.testData.expectedPrice();


    }

    @Test
    public void userRegistrationTest() {
        System.out.println("Driver in test method: " + driver);

        RegistrationPage registrationPage = new RegistrationPage(this.driver);

        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();

    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());

        registrationConfirmationPage.goToFlightSearch();

    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest() {
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers(testData.noOfPassengers());
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void selectFlightsTest() {
        SelectFlightsPage selectFlightsPage = new SelectFlightsPage(driver);
        Assert.assertTrue(selectFlightsPage.isAt());
        selectFlightsPage.selectFlights();
    }

    @Test(dependsOnMethods = "selectFlightsTest")
    public void flightConfirmationTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());

        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());

    }


}