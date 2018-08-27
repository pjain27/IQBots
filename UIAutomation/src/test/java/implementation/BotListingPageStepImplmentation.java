package implementation;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import pageobjects.*;
import org.openqa.selenium.support.PageFactory;
import utils.testBase;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BotListingPageStepImplmentation {
    private CreateNewInstancePage newInstancePage = PageFactory.initElements(Driver.webDriver, CreateNewInstancePage.class);
    private LearningInstanceDetailPage liDetailsPage = PageFactory.initElements(Driver.webDriver, LearningInstanceDetailPage.class);
    private LearningInstanceListingPage liListingPage = PageFactory.initElements(Driver.webDriver, LearningInstanceListingPage.class);
    private BotListingPage botPage = PageFactory.initElements(Driver.webDriver, BotListingPage.class);
    private BotListingSummaryPage botSummaryPage = PageFactory.initElements(Driver.webDriver, BotListingSummaryPage.class);
    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);
    private String groupName;


    @Step("Check for number of groups created and click on Create Bot with highest priority")
    public void countNumberOfGroupsCreatedAndClickONCreateBot() throws Exception {
        // Given
        int totalRows = liDetailsPage.totalRows();
        if (totalRows > 0) {
            groupName = liDetailsPage.columnData(1, 1);
            String priority = liDetailsPage.columnData(1, 8);

            // When
            liDetailsPage.clickOnBotLink(1);
            baseClass.closePopUp();

        }
    }

    @Step("Close the Designer window")
    public void closeTheDesignerWindow() throws Exception {
        botPage.closeDesigner();

    }

    @Step("Instance name on learning instance detail page is same as the instance name entered while creating instance")
    public void getInstancenameForWhichBotIsCreated() {
        // Given

        // when
        String learningInstancePageInstanceName = liDetailsPage.getInstanceName().getText();

        // Then
        assertThat(learningInstancePageInstanceName).isEqualTo(Driver.instName);
    }

    @Step("Click on Bot Tab")
    public void clickOnBotTab() throws Exception {
        // Given

        // When
        liListingPage.clickonTabs("Bots");

        // Then

    }

    @Step("User Landed to Bot Page")
    public void checkBotPageTitle() throws InterruptedException {
        // Given

        // When
        botPage.waitUntilPageHeaderIsDisplayed();
        String actualTitle = botPage.getBotPageTitle().getText();

        // Then
        // assertThat(actualTitle).isEqualTo("IQ Bots");
        System.out.println(actualTitle);
    }

    @Step("Verify values of search field DropDown")
    public void valuesOfSearchDropDown() {
        // Verify Domain DropDown have values.
        // Given
        List<String> expectedDomainDDList = newInstancePage.propertyNameAsList("botPageSearchDropDownValues");

        // When
        List<String> actualDomainDDList = botPage.getBotPageSearchDropDownValue();

        //Then
        assertThat(expectedDomainDDList).isNotNull();
        assertThat(actualDomainDDList).isNotNull();
        assertThat(actualDomainDDList).containsAll(expectedDomainDDList);
        assertThat(actualDomainDDList.size()).isEqualTo(expectedDomainDDList.size());
    }

    @Step("Select <Instance Name> from search DropDown in Bot Page")
    public void searchDDBotPage(String InstanceName) throws Exception {
        // Given

        // When
        botPage.selectInstanceName(InstanceName);
        String actualSelectedValue = botPage.getSearchFieldDropDown().getText();

        System.out.print("Actual selected " + actualSelectedValue);

        // Then
    }

    @Step("Enter the instance name in search textbox")
    public void enterInstanceName() {
        // Given

        // When
        String actualName = botPage.searchForInstanceName(Driver.instName);

        // Then

    }

    @Step("Group name should be same as the group name for which bot was created for that particular instance")
    public void verifyInstanceAndGroupName() throws Exception {
        // Given

        // When
        int totalRows = liDetailsPage.totalRows();

        // Then
        assertThat(totalRows).isNotZero();

        // Given
        if (totalRows > 0) {
            baseClass.explicitWait("(//td[contains(text(),'AutoInstance')])[1]");

            // When
            System.out.println("Group Name---" + liDetailsPage.columnData(1, 1));
            String botPageGroupName = liDetailsPage.columnData(1, 1);

            // Then
            // assertThat(System.getenv("training_GroupName")).isEqualTo(botPageGroupName);
            assertThat(groupName).isEqualTo(botPageGroupName);
        }

    }

    @Step("Bot should be in <training> status")
    public void statusOfBotJustCreated(String statusTraining) throws Exception {
        // Given

        // When
        int totalRows = liDetailsPage.totalRows();

        // Then
        assertThat(totalRows).isNotZero();

        // Given
        if (totalRows > 0) {

            // When
            String botPageGroupName = liDetailsPage.columnData(1, 3);

            // Then
            assertThat(botPageGroupName).isEqualTo(statusTraining);
        }
    }

    @Step("Move bot from staging to production")
    public void moveBotFromStagingtoProduction() throws Exception {
        // Given

        // When
        int totalRows = liDetailsPage.totalRows();
        System.out.print("Total Rows" + totalRows);

        // Then
        assertThat(totalRows).isNotZero();

        // Given
        if (totalRows > 0) {
            int rowNum = 1;

            // When
            botPage.moveFromStagingToProduction(rowNum);
            Driver.webDriver.navigate().refresh();
            botPage.waitForBotTableToBeDisplayed();
            botPage.searchForInstanceName(Driver.instName);

            // Then
        }
    }

    /*@Step("Wait for Bot page table to be displayed")
    public void waitFortableToBeDisplayed() {
        botPage.waitForBotTableToBeDisplayed();
    }

    /*@Step("Search for Instance Name")
    public void searchForInstanceName() {
        botPage.searchForInstanceName(Driver.instName);
    }*/


    @Step("Move bot from production to staging")
    public void moveBotFromProductionToStaging() throws Exception {
        // Given

        // When
        int totalRows = liDetailsPage.totalRows();
        System.out.print("Total Rows" + totalRows);

        // Then
        assertThat(totalRows).isNotZero();
        // Given

        if (totalRows > 0) {
            int rowNum = 1;

            // When
            botPage.moveFromStagingToProduction(rowNum);
            Driver.webDriver.navigate().refresh();
            baseClass.waitForPageLoad();
        }
    }

    @Step("When user go to bot listing page and validate bot run summary then all details should be available")
    public void checkAllDetailsBeforeRun() throws Exception {
        // Given

        // When
        liListingPage.clickonTabs("Bots");
        botPage.searchBots(Driver.instName);
        baseClass.explicitWait("(//td[contains(text(),'AutoInstance')])[1]");

        // Then
        botPage.getAllDetailsOfBotSummary("Staging_BLP_Before_BotRun_B");

        // Given

        // When
        botPage.clickOnRunButton();
    }

    @Step("And when user click on bot run, details should be updated after bot processing")
    public void checkAllDetailsAfteRun() throws Exception {
        // Given

        // When
        Thread.sleep(10000); //need to provide sleep as bot is in processing and we don't have any locator for that.
        Driver.webDriver.navigate().refresh();
        baseClass.waitForPageLoad();
        baseClass.waitForElementClick(botPage.getSearchBox());
        botPage.searchBots(Driver.instName);
        baseClass.explicitWait("//span[text()='Files Processed']/following-sibling::span[1]");

        // Then
        botPage.getAllDetailsOfBotSummary("Staging_BLP_After_BotRun_B");
    }

    @Step("Validate all data after group move from Staging to production")
    public void validateAllDataAfterGroupMoveToStaging() throws Exception {
        // Given

        // When
        liListingPage.clickonTabs("Bots");
        botPage.searchBots(Driver.instName);
        botPage.moveGroupToProduction(System.getenv("training_GroupName"));
        Driver.webDriver.navigate().refresh();
        baseClass.waitForPageLoad();
        botPage.searchBots(Driver.instName);
        baseClass.explicitWait("(//td[contains(text(),'AutoInstance')])[1]");
        botPage.clickOnGroupName(System.getenv("training_GroupName"));

        // Then
        botPage.getAllDetailsAfterMoveProduction("Production_BLP_B");
    }

    @Step("On Learning Instance page click on instance name")
    public void clickOnInstanceNameLinkOnLisingPage() throws Exception {
        // Given

        // When
        int totalRows = liDetailsPage.totalRows();

        // Then
        assertThat(totalRows).isNotZero();

        // Given
        if (totalRows > 0) {
            int rowNum = 1;

            // When
            liListingPage.clickOnInstanceNameLink(rowNum);
        }
    }

    @Step("Instance name should be listed on Bot page list")
    public void botPageInstanceNameList() {
        // Given

        // When
        List<String> getListOfInstanceFromTable = botPage.getListOfInstanceNameonBotPage();

        // Then
        assertThat(getListOfInstanceFromTable).contains(Driver.instName);
    }

    @Step("Search result should include the Group name too")
    public void tableListDisplyGroupName() {
        // Given

        // When

        // Then
        assertThat(botPage.getGroupNameFromBotTableList()).contains(groupName);

    }

    @Step("Run the bot once")
    public void runBotOnce() {
        // Given

        // When
        botPage.clickOnRunButton();

        // Then

    }

    @Step("Error message should be displayed <Staging document for this visionbot is already in running state.>")
    public void errorMessage(String errorMessage) {
        // Given

        // When
        liListingPage.waitForMessageToBeDisplayed();

        // Then
        assertThat(botPage.getErrorMessageAfterBotRunMoreTheOnce()).isEqualTo(errorMessage);

    }

    @Step("Run the bot once and move bot from staging to production")
    public void runBotAndMoveBotFromStagingToProduction() {
        // Given

        // When
        botPage.clickOnRunButton();
        int totalRows = liDetailsPage.totalRows();
        System.out.print("Total Rows" + totalRows);

        // Then
        assertThat(totalRows).isNotZero();

        // Given
        if (totalRows > 0) {
            int rowNum = 1;

            // When
            botPage.moveFromStagingToProduction(rowNum);

            // Then
        }

    }

    @Step("Ensure after training data should be extracted in preview & in IQTest for all pass fields")
    public void trainDocsWithAllFieldPass() throws Exception {
        // Given
        baseClass.runBatchFile(System.getenv("designerTrainingBatchFileWithAllfieldPass"));

        // When
        String results = baseClass.validateDesignerLogs(System.getenv("designerTrainingBatchFileWithAllfieldPassLogFile"));
        System.out.print("Output result: " + results);
        // Then
        assertThat(results).isEqualTo("Designer Launched : Pass :Designer Preview : Pass :");
    }

    @Step("Count of file successfully processed should be <1>")
    public void totalFileSuccessfullyProcesssed(String fileSuccessfullyProcessed) {
        // Given

        // When

        // Then
        assertThat(botPage.getTotalFileSuccessfullyProcessed()).isEqualTo(fileSuccessfullyProcessed);

    }

    @Step("Click on edit button of bot page")
    public void clickOnEditButtonOfBotPage() {
        // Given

        // When

        // Then
        botPage.clickOnEditButton(1);

    }

    @Step("Wait for bot run process to be completed")
    public void waitBotRunProcessToBeCompleted() throws InterruptedException {
        // Given

        // When
        Thread.sleep(5000);

    }

    @Step("Upload documents from IQBot Lite command")
    public void uploadDocsFromIQBotLiteCommand() throws Exception {
        baseClass.runBatchFile(System.getenv("iqBotLiteForAllFieldPassBatchFile"));

    }
    //..........................................................................................
    //Sanity
    //..........................................................................................


    @Step("Validate all data after group move from Staging to production <Group_Name>")
    public void validateAllDataAfterGroupMoveToStaging(String Group_Name) throws Exception {
        // Given

        // When

        //Then
        liListingPage.clickonTabs("Bots");
        botPage.searchBots(Driver.instName);
        botPage.moveGroupToProduction(Group_Name);
        Driver.webDriver.navigate().refresh();

    }
    @Step("validate files after bot and instance both are in production")
    public void validatedValidInvalidData() {
        //Given

        //When

        //Then
        botSummaryPage.fileValidateForBotAfterInstanceMoveProduction();

        //Given

        //When

        //Then
        botSummaryPage.invalidFileValidateForBotAfterInstanceMoveProduction();

        //Given

        //When

        //Then
        botSummaryPage.timeSpendToValidate();


    }
    //...........................................................................................
    //...........................................................................................


}
