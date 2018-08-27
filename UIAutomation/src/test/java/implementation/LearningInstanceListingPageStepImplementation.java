package implementation;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;
import utils.testBase;

import java.awt.*;
import java.util.List;

import static com.thoughtworks.gauge.Gauge.writeMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class LearningInstanceListingPageStepImplementation {

    private CreateNewInstancePage newInstancePage = PageFactory.initElements(Driver.webDriver, CreateNewInstancePage.class);
    private LearningInstanceDetailPage liDetailsPage = PageFactory.initElements(Driver.webDriver, LearningInstanceDetailPage.class);
    private LearningInstanceListingPage liListingPage = PageFactory.initElements(Driver.webDriver, LearningInstanceListingPage.class);
    private CreateNewInstancePage newInstance = PageFactory.initElements(Driver.webDriver, CreateNewInstancePage.class);
    private BotListingPage botPage = PageFactory.initElements(Driver.webDriver, BotListingPage.class);
    private ValidationPage validatorPage = PageFactory.initElements(Driver.webDriver, ValidationPage.class);

    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);

    @Step("Ensure user with service role get all tabs access")
    public void ensureServiceUserHaveAllAccess() throws Exception {
        // Given

        // When
        liListingPage.clickonTabs("Dashboards");

        // Then
        assertThat(liListingPage.getMytotal()).contains("My Totals");

        writeMessage("%s are displayed", liListingPage.getMytotal());

        // Given

        // When
        liListingPage.clickonTabs("Learning Instance");

        // Then
        assertThat(liListingPage.getMyLearningInstancePageTitle()).contains("My Learning Instances");

        writeMessage("Web Page Title is %s", liListingPage.getMyLearningInstancePageTitle());

        // Given

        // When
        liListingPage.clickonTabs("Bots");

        // Then
        assertThat(liListingPage.getBotsPageTitle()).contains("IQ Bots");

        writeMessage("Web Page Title is %s", liListingPage.getBotsPageTitle());
    }

    @Step("Then user with Validator role get only Learning Instance tab access")
    public void ensureValidatorUserAccess() throws Exception {
        // Given

        // When
        validatorPage.learningInstanceTabClick();

        // Then
        assertThat(validatorPage.getMyLearningInstanceLabel()).contains("Validation");

        writeMessage("Web Page Title is %s", validatorPage.getMyLearningInstanceLabel());
    }

    @Step("When user click on profile button in home page")
    public void clickOnProfileIconAndValidateUser() throws InterruptedException {
        // Given

        // When
        baseClass.waitForElementClick(liListingPage.getuUserIconelement());

        // Then
    }

    @Step("When user click on Learning Instance Tab")
    public void clickOnLearningInstanceTab() throws InterruptedException {
        // Given

        // When
        baseClass.waitForElementClick(liListingPage.getLielement());

        // Then
    }

    @Step("Then on learning instance tab selection user should be landed to <pageHeading> page")
    public void validatePageHeading(String pageHeading) throws InterruptedException {
        // Given
        pageHeading = "My Learning Instances";

        // When
        baseClass.waitForElementClick(liListingPage.getLielement());

        // Then
        assertThat(liListingPage.getMyLearningInstancePageTitle()).contains(pageHeading);
    }

    @Step("When user click on New Instance button")
    public void clickOnNewInstance() throws InterruptedException {
        // Given

        // When
        newInstance.clickOnNewInstance();

        // Then
    }

    @Step("Select <Instance Name> from search DropDown in LearningInstance Page")
    public void searchDDLIPage(String InstanceName) throws InterruptedException {
        // Given

        // When
        liListingPage.selectInstanceName(InstanceName);
        String actualSelectedValue = liListingPage.getsearchDDValue().getText();

        // Then

    }

    @Step("Enter the instance name in LearningInstance Search textbox")
    public void enterInstanceNameLIPage() {
        // Given

        // When
        //   baseClass.explicitWait("(//a[contains(text(),'AutoInstance')])[1]");
        String actualName = liListingPage.searchForInstanceName(Driver.instName);
        actualName = actualName.substring(13);

        // liListingPage.clickOnNumberOfBotColumn();

        // Then
        assertThat(Driver.instName.toLowerCase()).contains(actualName);
        System.out.println("InstanceName LI Page searchBox" + actualName);
        // System.out.println("expected instance nameLI page" + learningInstancePageInstanceName);
    }

    @Step("Move instance from staging to production")
    public void moveInstanceFormStagingToProduction() throws Exception {
        // Given

        // When
        int totalRows = liDetailsPage.totalRows();

        // Then
        assertThat(totalRows).isNotZero();

        // Given
        if (totalRows > 0) {

            // When
            botPage.waitForBotTableToBeDisplayed();
            int rowNum = 1;
            botPage.moveFromStagingToProduction(rowNum);

            // Then

        }
    }

    @Step("Move instance from production to staging")
    public void moveInstanceFormProductionToStaging() throws Exception {
        // Given

        // When
        int totalRows = liDetailsPage.totalRows();

        // Then
        assertThat(totalRows).isNotZero();

        // Given
        if (totalRows > 0) {
            int rowNum = 1;

            // When
            botPage.moveFromStagingToProduction(rowNum);
            Driver.webDriver.navigate().refresh();
            baseClass.waitForPageLoad();

            // Then
        }
    }

    @Step("Validate all data in Learning Instance Listing Page after Bot run ")
    public void validateAllDataAfterBotRunLearningInstancePage() throws Exception {
        System.out.println("Validate all data in Learning Instance Listing Page after Bot run");
        // Given

        // When
        baseClass.waitForElementClick(liListingPage.getLielement());
        liListingPage.clickOnNumberOfBotColumn();
        liListingPage.searchBots(Driver.instName);
        baseClass.explicitWait("(//a[contains(text(),'AutoInstance')])[1]");

        // Then
        liListingPage.validateInstanceDataInLearningInstanceListing("Staging_LILP_After_BotRun_I");
    }

    @Step("Validate Learning Instance Listing data after move instance from staging to production")
    public void validateAllDataAfterGroupMoveToProduction() throws Exception {
        // Given
        System.out.println("validateAllDataAfterGroupMoveToProduction");
        baseClass.waitForElementClick(liListingPage.getLielement());

        // When
        liListingPage.searchBots(Driver.instName);
        baseClass.explicitWait("(//a[contains(text(),'AutoInstance')])[1]");
        botPage.moveFromStagingToProduction(1);
        Driver.webDriver.navigate().refresh();
        baseClass.waitForPageLoad();
        liListingPage.searchBots(Driver.instName);
        baseClass.explicitWait("(//a[contains(text(),'AutoInstance')])[1]");
        liListingPage.clickOnNumberOfBotColumn();

        // Then
        liListingPage.validateInstanceDataInLearningInstanceListing("Production_LILP_After_BotRun_I");
    }

    @Step("Go to Learning Instance Details page and wait for production file upload")
    public void goToLearningInstanceDetails() throws Exception {
        // Given

        // When
        baseClass.waitForElementClick(liListingPage.getLielement());
        liListingPage.searchBots(Driver.instName);
       // baseClass.explicitWait("(//a[contains(text(),'AutoInstance')])[1]");
       liListingPage.clickOnInstanceName(Driver.instName);
        baseClass.waitForSpinnerToAppear();

        // Then
    }

    @Step("Launch Validator from Learning Instance Listing Page")
    public void launchValidator() throws Exception {
        // Given

        // When
        baseClass.waitForElementClick(liListingPage.getLielement());
        validatorPage.clickOnValidatorIcon();
        baseClass.closePopUp();

        // Then

    }

    @Step("Verify values of search field DropDown Instance Page")
    public void valuesOfSearchDropDownInstancePage() {

        // Verify Domain DropDown have values.

        // Given
        List<String> expectedDomainDDList = newInstancePage.propertyNameAsList("learningInstanceSearchDropDownValues");

        // When
        List<String> actualDomainDDList = liListingPage.verifySearchDropDownValue();

        //Then
        assertThat(expectedDomainDDList).isNotNull();
        assertThat(actualDomainDDList).isNotNull();
        assertThat(actualDomainDDList).containsAll(expectedDomainDDList);
        assertThat(actualDomainDDList.size()).isEqualTo(expectedDomainDDList.size());
    }

    int countNumberOfInstanceCreated;

    @Step("Get the total number of instance created")
    public void getNumberOfInstanceCreated() {
        countNumberOfInstanceCreated = liListingPage.getTotalNumberOfInstanceCreated();
        System.out.println("Total Rows =" + countNumberOfInstanceCreated);

    }

    @Step("If no more instance created before <No current learning instances.> message will be displayed else search the instance name on search textbox and validate the result")
    public void noCurrentInstancesMessage(String noCurrentInstancemessage) throws Exception {
        // Given
        countNumberOfInstanceCreated = countNumberOfInstanceCreated - 1;

        // When
        System.out.println("Total Rows after Deletion of Instance  =" + countNumberOfInstanceCreated);
        if (countNumberOfInstanceCreated == 0) {
            String message = liListingPage.getNoInstanceMessage();

            // Then
            assertThat(message).isEqualTo(noCurrentInstancemessage);

            // Given

            // When
            liListingPage.clickonTabs("Bots");

            // Then
            Driver.webDriver.navigate().refresh();

            // Given

            // When
            message = botPage.getNoCurrentBotsMessage();

            // Then
            assertThat(message).isEqualTo("No current bots.");

        } else if (countNumberOfInstanceCreated >= 1) {

         /* * Select "Instance Name" from search DropDown in LearningInstance Page
            * Enter the instance name on LearningInstance Search textbox which is currently deleted
            * Message displayed "Project(s) not found"
            * Click on Bot Tab
            * Select "Instance Name" from search DropDown
            * Enter the instance name in Bot page search textbox which is currently deleted
            * Message displayed in bot page for deleted search instance "Bot(s) not found" */

            // Given
            searchDDLIPage("Instance Name");

            // When
            liListingPage.searchForDeteletedInstanceName(Driver.instName);
            liListingPage.hitEnterKey();

            // Then

            // Given
            String getMessage;

            // When
            getMessage = liListingPage.getMessagedDisplayedForSearchedInstance();

            // Then
            assertThat(getMessage).isEqualTo("Project(s) not found");

            // Given

            // When
            liListingPage.clickonTabs("Bots");

            // Then

            // Given

            // When
            botPage.selectInstanceName("Instance Name");
            String actualSelectedValue = botPage.getSearchFieldDropDown().getText();

            // Then

            // Given

            // When
            botPage.searchForDeteletedInstanceNameOnBotPage(Driver.instName);
            liListingPage.hitEnterKey();

            // Given

            // When
            getMessage = botPage.getMessagedisplayedFromBotPageForSearchInstance();

            // Then
            assertThat(getMessage).isEqualTo("Bot(s) not found");

        }
    }

    @Step("Select Instance which are in <Staging>")
    public void selectStagingEnvironment(String environment) {
        // When
        String environmentName = liListingPage.searchForInstanceName(environment);


        // Then
        if(environment.equals("training") ||  environment.equals("ready") || environment.equals("active")){
            assertThat(environmentName).isEqualToIgnoringCase("Status"+environment);
        }
        else {
            assertThat(environmentName).isEqualToIgnoringCase("Environment"+environment);
        }
    }

    @Step("Instance name should be listed on list")
    public void instanceNameOnTheTableList() {
        // Given

        // When
        List<String> getListOfInstanceFromTable = liListingPage.getListOfInstanceName();

        // Then
        assertThat(getListOfInstanceFromTable).contains(Driver.instName);
    }

    @Step("Search for Instance Created")
    public void searchForInstanceCreated() {
        // Given

        // When
        String getInstanceName = liListingPage.searchForInstanceName(Driver.instName);

        // Then
        assertThat(getInstanceName).isEqualToIgnoringCase(Driver.instName);
    }

    //........................................................................................
                            //Sanity
    //........................................................................................
    @Step("Validate Learning Instance Listing data after move instance from staging to production sanity")
    public void validateAllDataAfterGroupMoveToProductionSanity() throws Exception {
        // Given
        System.out.println("validateAllDataAfterGroupMoveToProduction");
        baseClass.waitForElementClick(liListingPage.getLielement());

        // When
        liListingPage.searchBots(Driver.instName);
        baseClass.explicitWait("(//a[contains(text(),'AutoInstance')])[1]");
        botPage.moveFromStagingToProduction(1);
        Driver.webDriver.navigate().refresh();
        baseClass.waitForPageLoad();
        liListingPage.searchBots(Driver.instName);
        baseClass.explicitWait("(//a[contains(text(),'AutoInstance')])[1]");
        liListingPage.clickOnNumberOfBotColumn();
        // Then
        //liListingPage.validateInstanceDataInLearningInstanceListing("Production_LILP_After_BotRun_I");
    }



    //.........................................................................................
}