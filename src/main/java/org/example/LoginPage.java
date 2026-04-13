package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(name = "uid")
    WebElement useremail;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(name = "btnLogin")
    WebElement loginBtn;
    @FindBy(className = "barone")
    WebElement bankTitleElement;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);

    }
    public void   setUserName(String email) { useremail.sendKeys(email); }
    public void   setPassword(String pass) { password.sendKeys(pass); }
    public void   clickLogin()          { loginBtn.click(); }
    public String getBankTitle()        { return bankTitleElement.getText(); }


}
