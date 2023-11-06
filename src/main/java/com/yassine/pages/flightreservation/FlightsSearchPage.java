package com.yassine.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.yassine.pages.AbstractPage;

public class FlightsSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengersSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public FlightsSearchPage(WebDriver driver) {
        super(driver);

    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(passengersSelect));
        return this.passengersSelect.isDisplayed();

    }

    public void selectPassengers(String noOfPassengers) {

        Select passengers = new Select(this.passengersSelect);
        passengers.selectByValue(noOfPassengers);

    }

    public void searchFlights() {
        this.searchFlightsButton.click();
    }
}
