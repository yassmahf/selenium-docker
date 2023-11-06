package com.yassine.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yassine.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement gotToFlightsSearchButton;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(gotToFlightsSearchButton));
        return this.gotToFlightsSearchButton.isDisplayed();

    }

    public void goToFlightSearch() {
        this.gotToFlightsSearchButton.click();
    }

}
