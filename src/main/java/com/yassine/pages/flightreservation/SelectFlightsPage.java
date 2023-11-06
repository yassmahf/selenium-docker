package com.yassine.pages.flightreservation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yassine.pages.AbstractPage;

import java.util.concurrent.ThreadLocalRandom;

public class SelectFlightsPage extends AbstractPage {

    public SelectFlightsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "select-flights-section")
    private WebElement pageTitle;

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightsOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightsOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightsButton;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.pageTitle));

        return this.confirmFlightsButton.isDisplayed();
    }

    public void selectFlights() {

        int random = ThreadLocalRandom.current().nextInt(0, departureFlightsOptions.size());
        this.departureFlightsOptions.get(random).click();
        this.arrivalFlightsOptions.get(random).click();
        this.confirmFlightsButton.click();

    }

}