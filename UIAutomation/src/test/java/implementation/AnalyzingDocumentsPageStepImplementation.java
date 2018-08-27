package implementation;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;
import utils.testBase;


import static org.assertj.core.api.Assertions.assertThat;

public class AnalyzingDocumentsPageStepImplementation {

    private AnalyzingDocumentPage analyzingPage = PageFactory.initElements(Driver.webDriver, AnalyzingDocumentPage.class);

    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);

    @Step("And user landed into <pageHeading> page")
    public void validateNewInstancePage2(String pageHeading) throws InterruptedException {
        // Given

        // When

        // Then
        assertThat(analyzingPage.getAnalyzingPageTitle()).isEqualToNormalizingWhitespace(pageHeading);
    }

    @Step("Progress bar exist")
    public void getProgressBarText() {
        // Given

        // When

        // Then
        assertThat(analyzingPage.getProgressbar().isDisplayed()).isTrue();
    }

    @Step("Close and run in background button exist")
    public void closeAndRunButtonExist() {
        // Given

        // When

        // Then
        assertThat(analyzingPage.closeAndRunButton().isDisplayed()).isTrue();
        assertThat(analyzingPage.closeAndRunButton().getText()).isEqualTo("Close and run in background");
    }

    @Step("Ensure uploaded documents should be classified")
    public void waitForClassification() {
        // Given

        // When

        // Then
        assertThat(analyzingPage.getProgressbar().isDisplayed()).isTrue();

        // Given

        // When
        baseClass.waitForDocumentProcessing();
    }

    @Step("Click on Close and Run In Background button")
    public void clickOnCloseAndRunInBackgroungButton() throws InterruptedException {
        // Given

        // When
        baseClass.waitForAnalyzePage();
        analyzingPage.clickOnCloseAndRunInBackgroungButton();

        // Then
    }

    @Step("Ensure Finish and close button exist")
    public void finishAndCloseButtonExist() {
        // Given

        // When

        // Then
        assertThat(analyzingPage.getProgressbar().isDisplayed()).isTrue();

        // Given

        // When
        baseClass.explicitWait("//div[contains(text(),'Finish and close')]");

        // Then
        assertThat(analyzingPage.finishAndCloseButton().getText()).isEqualTo("Finish and close");

    }
}
