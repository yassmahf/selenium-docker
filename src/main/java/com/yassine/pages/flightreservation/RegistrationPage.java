package com.yassine.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yassine.pages.AbstractPage;

public class RegistrationPage extends AbstractPage {

    // WebDriver driver;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "street")
    private WebElement streetInput;

    @FindBy(name = "zip")
    private WebElement cityInput;

    @FindBy(name = "zip")
    private WebElement zipInput;

    @FindBy(id = "register-btn")
    private WebElement registerButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);

    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void enterUserDetails(String firstName, String lastName) {
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);

    }

    public void enterUserCredentials(String email, String password) {
        this.emailInput.sendKeys(email);
        this.passwordInput.sendKeys(password);
    }

    public void enterAddress(String street, String city, String zip) {
        this.streetInput.sendKeys(street);
        this.cityInput.sendKeys(city);
        this.zipInput.sendKeys(zip);
    }

    public void register() {
        this.registerButton.click();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(registerButton));
        return this.registerButton.isDisplayed();

    }

}
