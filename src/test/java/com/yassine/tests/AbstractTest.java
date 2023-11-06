package com.yassine.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.yassine.listener.TestListener;
import com.yassine.util.Config;
import com.yassine.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.DrbgParameters.Capability;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class AbstractTest {
    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeTest

    public void setDriver(ITestContext ctx) throws MalformedURLException {


        if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ) {

            this.driver = getRemoteDriver();
            ctx.setAttribute(Constants.DRIVER,this.driver);

        } else {
            this.driver = getLocalDriver();
        }


    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities= new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER)))
        {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat,hubHost);

        return new RemoteWebDriver(new URL(url), capabilities);

    }

    private WebDriver getLocalDriver() {
       /** WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Volumes/ADATA SD700/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        return new ChromeDriver(options);**/


            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            // Add any Firefox options you need
            // For example, to run in headless mode:
            // options.addArguments("--headless");
        options.setBinary("/Volumes/ADATA SD700/Applications/Firefox.app/Contents/MacOS/firefox");
            return new FirefoxDriver(options);


    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }

}
