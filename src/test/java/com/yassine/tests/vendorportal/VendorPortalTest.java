package com.yassine.tests.vendorportal;

import com.yassine.pages.vendorportal.DashboardPage;
import com.yassine.pages.vendorportal.LoginPage;
import com.yassine.tests.AbstractTest;
import com.yassine.tests.vendorportal.model.VendorPortalTestData;
import com.yassine.util.Config;
import com.yassine.util.Constants;
import com.yassine.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage ;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;



    @BeforeTest
    @Parameters("testDataPath")
public void setPageObjects(String testDataPath){

    this.loginPage= new LoginPage(driver);
    this.dashboardPage= new DashboardPage(driver);
    this.testData= JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
}

   @Test
   public void loginTest(){
       loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
       Assert.assertTrue((loginPage.isAt()));
       loginPage.login(testData.username(), testData.password());

    }
    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        //finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());


        //order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(),testData.searchResultsCount());



    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        //logout
        dashboardPage.logout();

        Assert.assertTrue(this.loginPage.isAt());
    }











}
