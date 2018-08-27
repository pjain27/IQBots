package pageobjects;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class LoginPage {

    public LoginPage() {
        super();
    }

    /*Log In Page WebElements */
    //Username Text Box
    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    private WebElement userName;
    // Password Text box
    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    private WebElement userPassword;
    // Login Button
    @FindBy(how = How.XPATH, using = "//button[@class='commandbutton-button commandbutton-button--clickable']")
    private WebElement loginBtn;
    // Page Title
    @FindBy(how = How.XPATH, using = "//DIV[@class='title'][text()='IQ Bots']/self::DIV'")
    private WebElement pageTitle;
    // Login Error Message
    @FindBy(how = How.XPATH, using = "//ul[@class='aa-auth-error-message']")
    private WebElement loginErrorMessage;
    // Login Button
    private By loginButton = By.xpath("//button[@type='submit' and @class='commandbutton-button commandbutton-button--disabled']");


    /* UserName & Password Insertion at Login Page */
    public void doLogin(String login_username, String login_password) throws InterruptedException {
        ExpectedConditions.visibilityOf(userName);
        userName.sendKeys(login_username);
        userPassword.sendKeys(login_password);
        ExpectedConditions.elementToBeClickable(loginBtn);
        loginBtn.click();
    }

    // Login Button Click
    public void clickLoginButton() {
        loginBtn.click();
    }

    // Return Login Button WebElement
    public By getLoginButton() {
        return loginButton;
    }

    // Return Error Message
    public String getLoginErrorMessage() {
        return loginErrorMessage.getText();
    }

}
