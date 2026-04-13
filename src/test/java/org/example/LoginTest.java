package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.openqa.selenium.By.id;

public class LoginTest {
    WebDriver driver;
LoginPage loginpage;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }}

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][] {
                {"test@mail.com", "123", "invalid"},
                {"test@mail.com", "152", "valid"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testingLogin(String email, String password, String type){
        driver.get("https://demo.guru99.com/test/login.html");
        loginpage = new LoginPage(driver);
        loginpage.setUserName(email);
        loginpage.setPassword(password);
        loginpage.clickLogin();
        if (type.equals("valid")) {
            Assert.assertTrue(driver.getCurrentUrl().contains("success.html"));
        } else {
            Assert.assertTrue(driver.getPageSource().contains("error"), "Error message not found!");
        }
    }
    }

