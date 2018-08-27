package implementation;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.*;
import utils.testBase;

import static com.thoughtworks.gauge.Gauge.writeMessage;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginPageStepImplementation {

    private LoginPage login = PageFactory.initElements(Driver.webDriver, LoginPage.class);

    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);

    @Step("Open cognitive console logIn page")
    public void implementation1() {
        // Given
        String app_url = System.getenv("APP_URL");

        // When
        Driver.webDriver.get(app_url + "/");

        // Then
       // assertThat(Driver.webDriver.getTitle()).contains("Login | IQ Bot");

        writeMessage("Page title is %s", Driver.webDriver.getTitle());


    }

    @Step("When user login into cognitive console with valid user name as <username> and valid password as <password>")
    public void logIntoCognitiveServices(String username, String password) throws Exception {
        // Username & Password are given
        // Given
        baseClass.explicitWait("//input[@name='username']");

        // When
        login.doLogin(username, password);

        // Then
        baseClass.waitForPageLoad();
    }

    @Step("When user login into cognitive console with user name as <username> and password as <password>")
    public void logIntoCognitiveServices_CheckError(String username, String password) throws Exception {
        // Username & Password are Given
        baseClass.explicitWait("//input[@name='username']");

        // When
        login.doLogin(username, password);

        // Then
        baseClass.explicitWait("//ul[@class='aa-auth-error-message']");

    }

    @Step("Then error message should be displayed for invalid login credentials")
    public void invalidLoginError(){
       // Given

       // When

       // Then
        assertThat(login.getLoginErrorMessage()).isEqualToIgnoringCase("Invalid Username or Password.");
        Driver.webDriver.navigate().refresh();
    }

    @Step("Open cognitive console logIn page with service role user between executing scripts")
    public void implementationLogin() {
        // Given
        String app_url = System.getenv("APP_URL");

        // When
        Driver.webDriver.get(app_url + "/");

        // Then
     //   assertThat(Driver.webDriver.getTitle()).contains("Login | IQ Bot");

        writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Quit browser")
    public void quitBrowser() {
        Driver.webDriver.quit();
    }
}
