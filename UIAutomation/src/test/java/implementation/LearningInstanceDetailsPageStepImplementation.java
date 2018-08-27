package implementation;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;
import utils.testBase;
import java.awt.*;
import static com.thoughtworks.gauge.Gauge.writeMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class LearningInstanceDetailsPageStepImplementation {

    private CreateNewInstancePage newInstancePage = PageFactory.initElements(Driver.webDriver, CreateNewInstancePage.class);
    private LearningInstanceDetailPage liDetailsPage = PageFactory.initElements(Driver.webDriver, LearningInstanceDetailPage.class);
    private LearningInstanceListingPage liListingPage = PageFactory.initElements(Driver.webDriver, LearningInstanceListingPage.class);
    private CreateNewInstancePage newInstance = PageFactory.initElements(Driver.webDriver, CreateNewInstancePage.class);
    private BotListingPage botPage = PageFactory.initElements(Driver.webDriver, BotListingPage.class);
    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);
    private static String filesCount_Before_EditInstance;
    private String filesCount_After_EditInstance;
    private String smokeTestDocumentsFolder = System.getenv("test_Documents_FolderPath");

    @Step("When user edit instance and upload files")
    public void editInstanceAndUploadFiles() throws Exception {
        // Given

        // When
        baseClass.waitForElementClick(liDetailsPage.getStagingLabel());
        filesCount_Before_EditInstance = liDetailsPage.getClassifiedGroupFilesCount(System.getenv("fileCount_GroupName"));
        System.out.println("Before count" + filesCount_Before_EditInstance);
        liDetailsPage.editInstance();
        newInstance.clickOnBrowseButton();

        // Then

        // Given
        String filePath = "C:/IQBot_TestData/Edit.cmd";

        // When
        String multipleFilenames = newInstancePage.getallFilenames(filePath);
        baseClass.fileUpload(smokeTestDocumentsFolder + "\\" + "EditInstanceDocs", multipleFilenames);
    }

    @Step("Then on save button click uploaded files should be classified")
    public void addNewFilesToInstance() {
        // Given

        // When
        liDetailsPage.clickOnSave();

        // Then

    }

    @Step("Ensure that after document classification total files and groups should be updated")
    public void validateGroupFilesCount() throws Exception {
        // Given
        System.out.print("Files Count Before Edit Instance: " + filesCount_Before_EditInstance);
        filesCount_After_EditInstance = liDetailsPage.getClassifiedGroupFilesCount(System.getenv("fileCount_GroupName"));
        filesCount_After_EditInstance = filesCount_After_EditInstance.trim();
        filesCount_Before_EditInstance = filesCount_Before_EditInstance.trim();
        int before, after;
        System.out.println("Files Count After Edit Instance:" + filesCount_After_EditInstance);
        before = Integer.parseInt(filesCount_Before_EditInstance);
        after = Integer.parseInt((filesCount_After_EditInstance));
        System.out.print("Before:" + before);
        System.out.print("After:" + after);

        // When

        // Then
        assertThat(after).isGreaterThan(before);
    }

    @Step("When user click on CreateBot link visionbot designer should be launched")
    public void launchDesignerAndTrainVisionbot() throws Exception {
        // Given

        // When
        baseClass.waitForElementDisplay(liListingPage.getLielement());
        liDetailsPage.launchDesignerForSelectedGroup(System.getenv("training_GroupName"));
        baseClass.closePopUp();

        // Then

    }
    //................................................................................................
    //Sanity

    //.................................................................................................
    @Step("When user click on CreateBot link visionbot designer should be launched <training_group>")
    public void launchDesignerAndTrainVisionbot(String training_group) throws Exception {
        // Given

        // When
        baseClass.waitForElementDisplay(liListingPage.getLielement());
        liDetailsPage.launchDesignerForSelectedGroup(training_group);
        baseClass.closePopUp();

        // Then

    }

    @Step("train the other group")
    public void trainAnotherGroup() throws Exception {
        //Given

        //When

        //Then
        baseClass.runBatchFile(System.getenv("designerTrainingBatchFileTwo"));
    }


    @Step("Ensure that uploaded production files should be classified for selected Instance sanity")
    public void validateProductionFilesUploadAndClassificationSanity() throws Exception {
        // Given

        // When
        baseClass.waitForSpinner();

        // Then
        // liDetailsPage.getAllValueInTable(System.getenv("fileCount_GroupName"), "production", "Production_LIDP_After_Upload_I");

    }

    //................................................................................................
    //SanityEnd
    //................................................................................................

    @Step("Ensure after training data should be extracted in preview & in IQTest")
    public void trainVisionbotAndValidatePreview() throws Exception {
        // Given
        baseClass.runBatchFile(System.getenv("designerTrainingBatchFile"));

        // When
        String results = baseClass.validateDesignerLogs(System.getenv("designerLogFile"));

        // Then
        // assertThat(results).isEqualTo("Designer Launched : Pass :Designer Preview : Pass :Designer IQTest : Pass :");
    }

    @Step("Then uploaded documents should be classified for selected Instance")
    public void validateProductionDocumentsClassification() throws Exception {
        // Given

        // When
        baseClass.waitForElementClick(liListingPage.getLielement());
        liListingPage.searchForInstanceName(Driver.instName);
        liListingPage.clickOnInstanceName(Driver.instName);
        baseClass.waitForSpinnerToAppear();

        // Then
        assertThat(liDetailsPage.checkSpinner()).isTrue();
    }

    @Step("On learning instance details page documents should be classified in groups")
    public void validateLearningInstancePage() throws Exception {
        // Given

        // When

        // Then
        assertThat(liDetailsPage.getTotalClassifiedGroupCount()).isEqualToIgnoringCase("3");
    }

    @Step("Then classified documents count should be match with uploaded documents count")
    public void validateTrainingDocumentsCountGroupWise() throws Exception {
        // Given

        // When

        // Then
        liDetailsPage.getAllValueInTable(System.getenv("fileCount_GroupName"), "staging", "Staging_LIDP_CreateInstance_I");
    }

    @Step("Ensure that Training Summary is also updated based on staging data")
    public void validateTrainingSummaryForStaging() throws Exception {
        // Given

        // When

        // Then
        liDetailsPage.validateTrainingSummary("Staging_LIDP_CreateInstance_I");
    }

    @Step("Ensure that Training Summary is also updated based on staging data after edit Instance")
    public void validateTrainingSummaryAfterEdit() throws Exception {
        // Given

        // When

        // Then
        liDetailsPage.validateTrainingSummary("Staging_BLP_Before_BotRun_I");
    }

    @Step("Ensure that uploaded production files should be classified for selected Instance")
    public void validateProductionFilesUploadAndClassification() throws Exception {
        // Given

        // When
        baseClass.waitForSpinner();

        // Then
        //liDetailsPage.getAllValueInTable(System.getenv("fileCount_GroupName"), "production", "Production_LIDP_After_Upload_I");

    }

    @Step("Ensure that Training Summary is also updated based on production data")
    public void validateTrainingSummaryForProduction() throws Exception {
        // Given

        // When

        // Then
        liDetailsPage.validateTrainingSummary("Production_LIDP_After_Upload_I");
    }

    @Step("Wait for documents processing in Learning Instance Details Page")
    public void waitForDocumentProcessing() {
        // Given

        // When
        baseClass.waitForSpinnerToAppear();
        baseClass.waitForSpinner();
        baseClass.waitForYetToBeClassifiedToDisappear();
        Driver.webDriver.navigate().refresh();
        baseClass.waitForPageLoad();

        // Then

    }

    @Step("Wait for page Load")
    public void waitForPageLoad() {
        baseClass.waitForPageLoad();
    }

    @Step("Instance view Details page exist")
    public void getViewDetailsPage() throws InterruptedException {
        // Given

        // When
        baseClass.explicitWait("//a[@class='aa-link aa-grid-row vertical-center']");

        // Then
        assertThat(liDetailsPage.getviewDetailsPageHeading()).isEqualTo("View details");

        writeMessage("View Details Page Exist");
    }

    // Step Implementation
    @Step("Check Edit button exist")
    public void editButtonExist() {
        // Given

        // When
        String editButtonText = liDetailsPage.getEditbuttonText();

        // Then
        assertThat(editButtonText).isEqualTo("Edit");
    }


    @Step("By clicking on Edit button Delete Instance button should be displayed")
    public void clickOnEditButton() {
        // Given

        // When
        liDetailsPage.editInstance();

        // Then
        assertThat(liDetailsPage.getDeleteButtonText()).isEqualTo("Delete Instance");

    }

    @Step("By clicking on deleteInstance button content modal should appear with enabled Cancel button and disabled I understand,please delete button")
    public void clickOnDeleteInstanceButton() throws InterruptedException {
        // Given

        // When
        liDetailsPage.clickOnDeleteInstanceButton();

        // Then
        assertThat(liDetailsPage.checkForContentModalExist()).isTrue();
        assertThat(liDetailsPage.checkFordeleteConfirmNameTextboxExist()).isTrue();
        assertThat(liDetailsPage.checkForCancelButtonExistAndEnabled()).isTrue();
        assertThat(liDetailsPage.checkForConfirmDeleteButtonExist()).isTrue();
    }

    @Step("Enter the instance name on the Textbox which you want to delete")
    public void enterTheInstanceName() {
        // Given

        // When
        liDetailsPage.enterTheInstanceName(Driver.instName);

        // Then
        assertThat(liDetailsPage.checkForConfirmDeleteButtonIsEnabled()).isTrue();
    }

    @Step("Click on I understand, please delete")
    public void clickOnIUnderstandPleaseDeleteButton() throws InterruptedException {
        // Given

        // When
        Thread.sleep(5000);
        liDetailsPage.clickOnConfirmDeleteButton();

        // Then
        assertThat(newInstancePage.getlearningInstancePageTitle()).isEqualTo("My Learning Instances");

        // Given

        // When
        liListingPage.waitForMessageToBeDisplayed();

        // Then
        assertThat(liListingPage.getMessageInstanceSuccessfullyDeleted()).isEqualTo("Learning instance was successfully removed");
    }

    @Step("Enter the instance name on LearningInstance Search textbox which is currently deleted")
    public void putDeletedInstanceNameOnSearchTextBox() throws AWTException {
        // Given

        // When
        liListingPage.searchForDeteletedInstanceName(Driver.instName);
        liListingPage.hitEnterKey();
    }

    @Step("Message displayed <getMessageDisplayed>")
    public void getMessagedDisplayed(String getMessageDisplayed) throws InterruptedException {
        // Given
        String getMessage;

        // When
        getMessage = liListingPage.getMessagedDisplayedForSearchedInstance();

        // Then
        assertThat(getMessage).isEqualTo(getMessageDisplayed);
    }

    @Step("Enter the instance name in Bot page search text box which is currently deleted")
    public void putDeletedInstanceNameOnSearchTextBox1() throws AWTException {
        // Given

        // When
        botPage.searchForDeteletedInstanceNameOnBotPage(Driver.instName);
        liListingPage.hitEnterKey();

    }

    @Step("Message displayed in bot page for deleted search instance <Bot(s) not found>")
    public void msgInBotPageForDeletedInstance(String getMessageDisplayed) throws InterruptedException {
        // Given
        String getMessage;

        // When
        getMessage = botPage.getMessagedisplayedFromBotPageForSearchInstance();

        // Then
        assertThat(getMessage).isEqualTo(getMessageDisplayed);
    }


    @Step("Move instance from production to staging from learning instance detail page")
    public void moveInstanceFromProductionToStagingFromLearningInstanceDetailPage() {
        // Given

        // When
        liDetailsPage.editInstance();

        // Then

    }

    @Step("Move instance to production on Learning instance detail page")
    public void moveInstanceToProductionFromLearningInstanceDetailPage() {
        // Given

        // When
        liDetailsPage.clickOnToggleToMoveInstanceToProduction();

        // Then

    }

    @Step("Enter the instance name in Bot page search textbox which is currently deleted")
    public void putdeletedInstanceNameOnSearchtextBox() throws AWTException {
        // Given

        // When
        botPage.searchForDeteletedInstanceNameOnBotPage(Driver.instName);
        liListingPage.hitEnterKey();

    }

    @Step("By Clicking on Cancel button to cancel the delete instance user will be on same learning instance detail page")
    public void clickOnCancelButtonToCancelDeleting() {
        // Given

        // When
        liDetailsPage.clickOnCancelButtonToCancelDeleting();

        // Then
        assertThat(liDetailsPage.getviewDetailsPageHeading()).isEqualTo("View details");
    }

    @Step("Instance description on learning instance detail page is same as the instance description entered while creating instance")
    public void instanceDescriptionOnLIDetailPage() {
        // Given

        // when
        String learningInstancePageInstanceDescription = liDetailsPage.getInstanceDescription().getText();

        // Then
        assertThat(learningInstancePageInstanceDescription).isEqualTo(System.getenv("instance_Desc"));
    }

    //...............................Sanity.........................
    @Step("Sorting Validation for the header - <headerName>")
    public void sortingValidationForViewDetailsPage(String headerName) throws Exception {
        //Given
        String[] sortAsc = liDetailsPage.bubblesortASC(liDetailsPage.noOfFileEachGroup(liDetailsPage.columnIndexForHeader(headerName)));
        String[] sortDec = liDetailsPage.bubblesortDSC(liDetailsPage.noOfFileEachGroup(liDetailsPage.columnIndexForHeader(headerName)));

        //When
        liDetailsPage.clickOnColumnHeader(liDetailsPage.columnIndexForHeader(headerName));
        String[] strFirst = liDetailsPage.noOfFileEachGroup(liDetailsPage.columnIndexForHeader(headerName));

        //Then
        assertThat(strFirst).isEqualTo(sortAsc);

        //Given

        //When
        liDetailsPage.clickOnColumnHeader(liDetailsPage.columnIndexForHeader(headerName));
        String[] sortLast = liDetailsPage.noOfFileEachGroup(liDetailsPage.columnIndexForHeader(headerName));

        //Then
        assertThat(sortLast).isEqualTo(sortDec);

    }

    @Step("checking create bot or edit bot link availability for <nameGroup> <expResult>")
    public void checkCreateEditBotLink(String nameGroup, String expResult) throws Exception {
        String str = liDetailsPage.getAllCreateBotLink(nameGroup);
        assertThat(str).isEqualToIgnoringCase(expResult);
    }


    @Step("Wait for production document processing")
    public void waitForProductionDocumentProcessing() {
        baseClass.waitForSpinner();

    }
}

