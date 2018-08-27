package implementation;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.BotListingPage;
import pageobjects.ValidationPage;
import utils.testBase;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationPageStepImplementation {
    private ValidationPage validationPage = PageFactory.initElements(Driver.webDriver, ValidationPage.class);
    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);

    @Step("Check status of Instance, it should be <status>")
    public void implementation1(String status) {
        // Given
        validationPage.searchLI(Driver.instName);
        baseClass.explicitWait("(//td[contains(text(),'AutoInstance')])[1]");

        // When
        String actualStatus = validationPage.statusLi();

        // then
        assertThat(actualStatus).isEqualToIgnoringCase(status);
    }

    @Step({"Validate files state before validating, where validated is <count1> and invalid is <count2>", "Validate files state after validating, where validated is <count1> and invalid is <count2>"})
    public void implementation2(String validatedCount, String invalid) {
        // Given

        validationPage.searchLI(Driver.instName);
        baseClass.explicitWait("(//td[contains(text(),'AutoInstance')])[1]");

        // When
        String processedFile = validationPage.fileProcessed().trim();
        String sentToValidation = validationPage.sentToValidation().trim();
        String validated = validationPage.validated().trim();
        String invalidFiles = validationPage.invalidFiles().trim();

        // then
        assertThat(processedFile).isEqualToIgnoringCase("2");
        assertThat(sentToValidation).isEqualToIgnoringCase("2");
        assertThat(validated).isEqualToIgnoringCase(validatedCount);
        assertThat(invalidFiles).isEqualToIgnoringCase(invalid);
    }

    @Step("Launch validator and validate")
    public void launchValidator() throws Exception {
        // Given

        // When
        validationPage.clickOnValidatorIcon();
        baseClass.closePopUp();

        // Then
        baseClass.runBatchFile(System.getenv("validatorBatchFile"));


    }

    @Step("Close validator and refresh validation page.")
    public void implementation3() {
        // Given
        Driver.webDriver.navigate().refresh();
    }
}
