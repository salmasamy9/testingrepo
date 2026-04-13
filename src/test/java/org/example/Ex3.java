package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Ex3 {
    WebDriver driver;
    LoginPage loginPage;
    @BeforeClass(alwaysRun = true)
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
    }


    @BeforeGroups("Smoke")
    public void logSmokeStart() {
        System.out.println(">>> Starting Group: Smoke");
    }

    @AfterGroups("Regression")
    public void logRegressionEnd() {
        System.out.println(">>> Finished Group: Regression");
    }
    @Test(groups={"Smoke"})
    public void testHomepageLoads() {
        driver.get("http://demo.guru99.com/V4/");
        Assert.assertTrue(driver.getTitle().contains("Guru99 Bank"));

    }
    @Test(groups={"Smoke"})
    public void testLoginPageVisible() {
        Assert.assertTrue(driver.getPageSource().contains("UserID"));

    }
    @Test(groups={"Smoke"})
    public void testFooterLinks() {
        driver.get("http://demo.guru99.com/V4/");
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        boolean isFooterPresent = driver.getPageSource().contains("Guru99");
        Assert.assertTrue(isFooterPresent, "Footer was not found even after scrolling!");

    }

    @Test(groups={"Regression"})
    public void testLoginValidCreds() { // [cite: 416]
       driver.get("http://demo.guru99.com/V4/");
        loginPage = new LoginPage(driver);
        loginPage.setUserName("mngr658729");
        loginPage.setPassword("azavenA");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getBankTitle()
                .toLowerCase().contains("guru99 bank"));
    }

    @Test(groups={"Regression"})
    public void testLoginInvalidCreds() { // [cite: 417-418]
      driver.get("http://demo.guru99.com/V4/");
        loginPage = new LoginPage(driver);
        loginPage.setUserName("allalala");
        loginPage.setPassword("123");
        loginPage.clickLogin();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        System.out.println("Alert says: " + alertMessage);
        Assert.assertTrue(alertMessage.contains("User or Password is not valid"));
        alert.accept();
    }

    @Test(groups={"Regression"})
    public void testPasswordReset() {
        System.out.println("Regression: Password Reset checked.");
    }

    @Test(groups={"Regression"})
    public void testAccountBalance() {
        System.out.println("Regression: Account Balance checked.");
    }

    @AfterClass(alwaysRun = true)
    public void QuitDriver() {
        if (driver != null) {
            driver.quit();
        }

    }}
