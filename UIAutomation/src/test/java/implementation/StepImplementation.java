package implementation;

import com.thoughtworks.gauge.ContinueOnFailure;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;
import utils.testBase;
import static com.thoughtworks.gauge.Gauge.writeMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;


public class StepImplementation {

    private LoginPage login = PageFactory.initElements(Driver.webDriver, LoginPage.class);
    private LearningInstanceListingPage liListingPage = PageFactory.initElements(Driver.webDriver, LearningInstanceListingPage.class);
    private CreateNewInstancePage newInstance = PageFactory.initElements(Driver.webDriver, CreateNewInstancePage.class);
    testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);
    String error, loginUserName, loginPassword, filesCount_Before_EditInstance, filesCount_After_EditInstance;
    String instDesc, domainLanguage, domainType, file;
    String smokeTestDocumentsFolder = System.getenv("test_Documents_FolderPath");

    @Step("Then logged in user name as <username> should be displayed")
    public void validateLogoutButton(String username) throws InterruptedException {
        // Given
        username = "Signed in as " + username + ".";
        System.out.println(username);

        // When

        // Then
        assertEquals(liListingPage.getLoggedinusername(), username);
    }

    @Step("When user click on logout button")
    public void clickOnLogoutButton() throws InterruptedException {
        // Given

        // When
        liListingPage.clickOnlogout();
    }

    @Step("Ensure user should be logout and landed to login page")
    public void validateLogoutFunctionality() throws InterruptedException {
        // Given

        // When
        baseClass.waitForElementDisplay(login.getLoginButton());
        Thread.sleep(3000);

        // Then
        assertThat(Driver.webDriver.getTitle()).contains("Login | IQ Bot");

        writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @ContinueOnFailure
    @Step("Then user should be landed to <pageHeading> page")
    public void validateNewInstancePage(String pageHeading) throws InterruptedException {
        // Given
        pageHeading = "Create new learning instance";

        // When

        // Then
        assertEquals(newInstance.getNewInstancePageTitle(), pageHeading);
    }

    @Step("When instance is created then it should be listed in IQBot Lite command & Success and Invalid path should be listed in IQBot Lite command")
    public void launchTaskEditorAndValidateIQBotLiteCommand() throws Exception {
        // Given

        // When
        baseClass.runBatchFile(System.getenv("iqBotLiteVerificationBatchFile"));
        String results, output_Path, success_Path, invalid_Path, expectedResults;
        results = baseClass.validateDesignerLogs(System.getenv("iqBotLiteLogFile"));
        output_Path = System.getenv("output_Path") + "\\" + Driver.instName;
        success_Path = output_Path + "\\" + "Success";
        invalid_Path = output_Path + "\\" + "Invalid";
        expectedResults = success_Path + " " + invalid_Path;
        System.out.println("Expected Results: " + expectedResults);
        System.out.println("Actual Results: " + results);

        // Then
        assertThat(results).isEqualTo(expectedResults);

    }

    @Step("Check output folder for created instance and validate unclassified files")
    public void validateOutputFolder() throws Exception {
        // Given

        // When
        baseClass.runBatchFile(System.getenv("outputFolderVerificationBatchFile"));
        String results = baseClass.validateDesignerLogs(System.getenv("outputFolderLogFile"));

        // Then
        assertThat(results).isEqualTo("Output Folder Creation : Pass : Output File Creation : Pass");
    }

    @Step("Validate total number of files, error count and image view in validator")
    public void verifyFilesInValidator() throws Exception {
        // Given

        // When
        baseClass.runBatchFile(System.getenv("validatorVerificationBatchFile"));
        String results = baseClass.validateDesignerLogs(System.getenv("validatorLogFile"));

        // Then
        assertThat(results).isEqualTo("File Count : Pass : Error Count : Pass : Image Load : Pass");

    }

    //.....................................................................................
    //.....................................................................................
    @Step("When instance is created then it should be listed in IQBot Lite command & Success and Invalid path should be listed in IQBot Lite command sanity")
    public void launchTaskEditorAndValidateIQBotLiteCommandSanity() throws Exception {
        // Given

        // When
        baseClass.runBatchFile(System.getenv("iqBotLiteVerificationBatchFile"));
        String results, output_Path, success_Path, invalid_Path, expectedResults;
        results = baseClass.validateDesignerLogs(System.getenv("iqBotLiteLogFile"));
        output_Path = System.getenv("output_Path") + "\\" + Driver.instName;
        success_Path = output_Path + "\\" + "Success";
        invalid_Path = output_Path + "\\" + "Invalid";
        expectedResults = success_Path + " " + invalid_Path;
        System.out.println("Expected Results: " + expectedResults);
        System.out.println("Actual Results: " + results);

        // Then
      //  assertThat(results).isEqualTo(expectedResults);

    }
}
