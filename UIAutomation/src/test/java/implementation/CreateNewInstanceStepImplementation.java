package implementation;

import com.thoughtworks.gauge.Step;
import driver.Driver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;

import static com.thoughtworks.gauge.Gauge.writeMessage;
import static org.assertj.core.api.Assertions.*;

import com.thoughtworks.gauge.ContinueOnFailure;
import utils.testBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CreateNewInstanceStepImplementation {

    private CreateNewInstancePage newInstancePage = PageFactory.initElements(Driver.webDriver, CreateNewInstancePage.class);
    private AnalyzingDocumentPage analyzingPage = PageFactory.initElements(Driver.webDriver, AnalyzingDocumentPage.class);
    private LearningInstanceListingPage liListingPage = PageFactory.initElements(Driver.webDriver, LearningInstanceListingPage.class);
    private CreateNewInstancePage newInstance = PageFactory.initElements(Driver.webDriver, CreateNewInstancePage.class);
    private LearningInstanceDetailPage liDetailsPage = PageFactory.initElements(Driver.webDriver, LearningInstanceDetailPage.class);
    private BotListingPage botPage = PageFactory.initElements(Driver.webDriver, BotListingPage.class);
    private BotListingPageStepImplmentation botPageStepImplementation = PageFactory.initElements(Driver.webDriver, BotListingPageStepImplmentation.class);
    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);
    private String instanceName, instDesc, totalNumberOfFilesUploaded, uniqueInstanceName;
    private List<String> uniqueList = new ArrayList<String>();
    private String smokeTestDocumentsFolder = System.getenv("test_Documents_FolderPath");
    private String totalFilesUploadedNewInstancePage = "";

    @Step("Click on Learning Instance Tab")
    public void clickLearningInstanceTab() throws InterruptedException {
        // Given

        // When
        newInstancePage.waitForLITab();
        newInstancePage.clickLearningInstanceTab();
        String beforeLearningInstancePageTitle = newInstancePage.getlearningInstancePageTitle();


        // Then
        assertThat(beforeLearningInstancePageTitle).isEqualTo("My Learning Instances");
    }

    @Step("Click on New Instance Button")
    public void clickOnCreateNewLI() throws InterruptedException {
        // Given

        // When
       // Driver.webDriver.navigate().refresh();
        newInstancePage.clickNewInstance();
    }

    @Step("User Should be landed to Create New Learning Instance page")
    public void createNewLearningInstancePage() throws InterruptedException {
        // Given

        // When
        String afterLearningInstancePageTitle = newInstancePage.getLITitle();
        newInstancePage.waitforNewLITitle();

        //Then
        assertThat(afterLearningInstancePageTitle).isEqualTo("Create new learning instance");

        writeMessage("Create New Learning Instance Page Exist");
    }

    // COG-889  By default Domain- Select Input is selected
    @Step("Default value of Domain DropDown is Select Input")
    public void defaultDomainType() throws InterruptedException {
        // Given

        //When
        String actualDomainValue = newInstancePage.domainType();

        //Then
        assertThat(actualDomainValue).isEqualTo("Select Input:");

        writeMessage("Default Domain Type is SelectInput : Completed");
    }

    // COG-889  By default Language- English is selected
    @Step("Verify values of Primary language DropDown")
    public void listPrimaryLanguage() throws InterruptedException {
        // Given
        List<String> expectedPrimaryLanguageDDList = newInstancePage.propertyNameAsList("listLanguage");

        // When
        List<String> actalPrimaryLanguageDDList = newInstancePage.verifyPrimaryLanguageList();

        //Then
        assertThat(expectedPrimaryLanguageDDList).isNotNull();
        assertThat(actalPrimaryLanguageDDList).isNotNull();
        assertThat(actalPrimaryLanguageDDList).isEqualTo(expectedPrimaryLanguageDDList);
        assertThat(actalPrimaryLanguageDDList).containsAll(expectedPrimaryLanguageDDList);
        assertThat(actalPrimaryLanguageDDList.size()).isEqualTo(expectedPrimaryLanguageDDList.size());
    }

    @Step("Default Value of Primary language is English")
    public void defaultPrimaryLanguage() throws InterruptedException {
        // Given

        // When
        String primaryLanguageActualValue = newInstancePage.primaryLanguageDefaultType();

        // Then
        assertThat(primaryLanguageActualValue).isEqualTo("English");

        writeMessage("By Default Primary Language selected is - English");
    }

    // COG-886 On selecting any "Type", all corresponding fields should come by default
    @Step("Verify values of Domain DropDown")
    public void domainDropDownList() throws InterruptedException {
        // Verify Domain DropDown have values.
        // Given
        List<String> expectedDomainDDList = newInstancePage.propertyNameAsList("listDomainType");

        // When
        List<String> actualDomainDDList = newInstancePage.verifyDomainList();

        //Then
        assertThat(expectedDomainDDList).isNotNull();
        assertThat(actualDomainDDList).isNotNull();
        assertThat(actualDomainDDList).containsAll(expectedDomainDDList);
        assertThat(actualDomainDDList.size()).isEqualTo(expectedDomainDDList.size());
    }

    // used to extract data from the returned list when using getFormFields(...) method.
    private static final int FORM_FIELDS = 0;
    private static final int TABLE_FIELDS = 1;
    private static final int FORM_LIST = 2;

    @ContinueOnFailure
    @Step("Verify values displayed for each dropdown item")
    public void selectingTypeOfFile() throws InterruptedException {
        // Given

        // When
        for (String domainName : newInstancePage.propertyNameAsList("listDomainType")) {
            String selectedDomain = newInstancePage.getSelectedDomain(domainName);
            List<List<String>> actualStandardFormFieldList = newInstancePage.getStandardFormFields();
            List<String> actualStandardFormFields, actualStandardTableFields, actualCheckedFormList;
            actualStandardFormFields = actualStandardFormFieldList.get(FORM_FIELDS);
            actualStandardTableFields = actualStandardFormFieldList.get(TABLE_FIELDS);
            actualCheckedFormList = actualStandardFormFieldList.get(FORM_LIST);
            List<String> expectedStandardFormFields = newInstancePage.getFormFields(selectedDomain).get(FORM_FIELDS);
            List<String> expectedStandardTableFields = newInstancePage.getFormFields(selectedDomain).get(TABLE_FIELDS);
            List<String> expectedCheckedFormList = newInstancePage.getFormFields(selectedDomain).get(FORM_LIST);

            // Then
            writeMessage("======================= Running test for " + selectedDomain + " =======================");
            assertThat(actualStandardFormFields).isEqualTo(expectedStandardFormFields);
            assertThat(actualStandardTableFields).isEqualTo(expectedStandardTableFields);
            assertThat(actualCheckedFormList).isEqualTo(expectedCheckedFormList);
            assertThat(actualStandardFormFields).containsAll(expectedStandardFormFields);
            assertThat(actualStandardTableFields).containsAll(expectedStandardTableFields);
            assertThat(actualCheckedFormList).containsAll(expectedCheckedFormList);
            assertThat(actualStandardFormFields.size()).isEqualTo(expectedStandardFormFields.size());
            assertThat(actualStandardTableFields.size()).isEqualTo(expectedStandardTableFields.size());
            assertThat(actualCheckedFormList.size()).isEqualTo(actualCheckedFormList.size());
        }
    }

    @ContinueOnFailure
    @Step("New Learning Instance TextBox Should give message for Blank Warning,Special Characters Warning,length warning")
    public void getInstanceNameText() throws InterruptedException {
        // Given
        String textboxValue, actualInstanceNameValue;
        WebElement textboxInstanceNameRequired;
        textboxValue = "";

        // When
        actualInstanceNameValue = newInstancePage.validateInstanceNameTextBox(textboxValue);
        textboxInstanceNameRequired = newInstancePage.textboxInstanceNameRequired();

        // Then
        assertThat(textboxInstanceNameRequired.isDisplayed()).isTrue();

        // Given

        // When
        textboxInstanceNameRequired.click();

        // Then
        assertThat(newInstancePage.getDuplicateFieldNameErrorMessage()).isEqualTo("Required");


        writeMessage("Value for TextBox Instance Name is always Required" + textboxValue);

        // Given
        Driver.webDriver.navigate().refresh();
        textboxValue = "~@#$%^&*:;<>.,/}{+";

        //  When
        actualInstanceNameValue = newInstancePage.validateInstanceNameTextBox(textboxValue);

        // Then
        assertThat(textboxInstanceNameRequired.isDisplayed()).isTrue();

        // Given

        // When
        textboxInstanceNameRequired.click();

        // Then
        assertThat(newInstancePage.getDuplicateFieldNameErrorMessage()).isEqualTo("Formatting must be numbers, letters, spaces or (_, -)");

        writeMessage("Special Characters not allowed " + actualInstanceNameValue);

        // Given
        Driver.webDriver.navigate().refresh();
        textboxValue = "abcdefghijklmnopqrstuvwxyz-012345678910111213141516";

        // When
        actualInstanceNameValue = newInstancePage.validateInstanceNameTextBox(textboxValue);

        // Then
        assertThat(textboxInstanceNameRequired.isDisplayed()).isTrue();
        assertThat(actualInstanceNameValue.length()).isGreaterThan(50);

        // Given

        // When
        textboxInstanceNameRequired.click();

        // Then
        assertThat(newInstancePage.getDuplicateFieldNameErrorMessage()).isEqualTo("Length should be under 50 characters");


        writeMessage("Length Should be under 50 characters " + actualInstanceNameValue);
        Driver.webDriver.navigate().refresh();
    }

    @Step("Enter all the details to create New Instance with uploading <3> file and Domain <Invoices> and Primary language <English>")
    public void createNewInstance(int filesUploaded, String domainName, String primaryLanguage) throws Exception {
        // Given
        instanceName = Driver.instName;
        uniqueInstanceName = instanceName;
        instDesc = System.getenv("instance_Desc");

        // When
        newInstancePage.insertRequiedFields(instanceName, instDesc, domainName, primaryLanguage);
        if (filesUploaded == 3) {
            if (primaryLanguage.equals("English")) {
                // Given
                String filePath = "C:/IQBot_TestData/Create.cmd";

                // When
                String multipleFilenames = newInstancePage.getallFilenames(filePath);
                baseClass.fileUpload(smokeTestDocumentsFolder + "\\" + "CreateInstanceDoc", multipleFilenames);
                // baseClass.uploadAllFiles(smokeTestDocumentsFolder + "\\" + "CreateInstanceDoc");
            } else {
                // Given
                String filePath = "C:/IQBot_TestData/GermanDocs.cmd";

                // When
                String multipleFilenames = newInstancePage.getallFilenames(filePath);
                baseClass.fileUpload(smokeTestDocumentsFolder + "\\" + "German", multipleFilenames);
              // baseClass.uploadAllFiles(smokeTestDocumentsFolder + "\\" + "German");
            }
        }
        else if(filesUploaded==4){
            if (primaryLanguage.equals("English")) {
                // Given
                String filePath = "C:/IQBot_TestData/CreateFourDoc.cmd";

                // When
                String multipleFilenames = newInstancePage.getallFilenames(filePath);
                baseClass.fileUpload(smokeTestDocumentsFolder + "\\" + "CreateInstanceFourDoc", multipleFilenames);
                // baseClass.uploadAllFiles(smokeTestDocumentsFolder + "\\" + "CreateInstanceDoc");
            } else {
                // Given
                String filePath = "C:/IQBot_TestData/GermanDocs.cmd";

                // When
                String multipleFilenames = newInstancePage.getallFilenames(filePath);
                baseClass.fileUpload(smokeTestDocumentsFolder + "\\" + "German", multipleFilenames);
                // baseClass.uploadAllFiles(smokeTestDocumentsFolder + "\\" + "German");
            }
        }
        else {
            // If uploading 1 file
            // Given
            String filePath = "C:/IQBot_TestData/SinglePage.cmd";

            // When
            String multipleFilenames = newInstancePage.getallFilenames(filePath);
            baseClass.fileUpload(smokeTestDocumentsFolder + "\\" + "Sample", multipleFilenames);
          // baseClass.uploadAllFiles(smokeTestDocumentsFolder + "\\" + "Sample");
        }

    totalNumberOfFilesUploaded = newInstancePage.inputFileTextBox();
        String uniqueDomainDDSelected = domainName;
        List<String> listCheckedItems = newInstancePage.getCheckedListForCreateNewLI();
        uniqueList.add(uniqueDomainDDSelected);
        uniqueList.add(uniqueInstanceName);
        listCheckedItems.forEach(item -> {
            uniqueList.add(item);
        });

        // Then
    }

    @Step("Then click on create instance and analyze button,uploaded documents should be classified")
    public void clickOnCreateNewInstanceButton() throws InterruptedException {
        // Given

        // When
        newInstance.clickOnCreateAndAnalyzeButton();
        baseClass.waitForAnalyzePage();

        // Then
        assertThat(analyzingPage.getProgressbar().isDisplayed()).isTrue();
    }

    @Step("Instance is successfully created and user is landed to Analyzing documents page")
    public void validateNewInstancePage2() throws InterruptedException {
        // Given

        // When

        // Then
        assertThat(analyzingPage.getAnalyzingPageTitle()).isEqualTo("Analyzing documents...");
    }

    @Step("Check total number of files in analyzing are same as uploaded during creating new instance")
    public void checkNumberOfTotalFilesInAnalyzingDocument() throws Exception {
        // Given
        baseClass.waitForElementDisplay(analyzingPage.progressbar());

        // When
        baseClass.waitForAnalyzePage();
        baseClass.explicitWait("//div[@class='aa-analyzing-project-progress-bar']/following-sibling::span[2]");
        String totalFilesDisplayedInAnalyzingPage = analyzingPage.totalFilesInAnalyzingDocuments();

        System.out.println("Actual TotalFiles " + totalFilesDisplayedInAnalyzingPage);

        // Then
        assertThat(totalFilesUploadedNewInstancePage.trim()).isEqualTo(totalFilesDisplayedInAnalyzingPage.trim());
    }

    @Step("Check Instance Details in Analyze Page")
    public void checkInstanceDetails() throws Exception {
        // Given

        // When
        List<String> analyzePageDetailList = analyzingPage.learningInstanceValueDetails();

        // Then
        System.out.println("uniqueList = " + uniqueList);
        System.out.println("analyzePageDetailList = " + analyzePageDetailList);
        assertThat(uniqueList.containsAll(analyzePageDetailList));
        assertThat(uniqueList.equals(analyzePageDetailList));
        assertThat(uniqueList).doesNotContainSequence(analyzePageDetailList);
        assertThat(analyzePageDetailList.size()).isEqualTo(uniqueList.size());
        assertThat(analyzePageDetailList).isNotNull();
        assertThat(uniqueList).isNotNull();
    }


    @Step("Create New Instance With Same Instance Name with uploading <1> file and Domain <Invoices> and primary language <English>")
    public void checkForDuplicateInstanceName(int noOfFiles, String domainName, String primaryLanguage) throws Exception {
        // Given
        instanceName = uniqueInstanceName;
        instDesc = System.getenv("instance_Desc");

        // When
        newInstancePage.insertRequiedFields(instanceName, instDesc, domainName, primaryLanguage);
        baseClass.uploadAllFiles(smokeTestDocumentsFolder + "\\" + "Sample");

        // Then

    }

    @Step("Then click on create instance and analyze button for Duplicate Instance Name")
    public void clickOnCreateNewInstanceButtonForDuplicateInstanceName() throws InterruptedException {
        // Given

        // When
        newInstancePage.clickOnCreateNewInstanceButtonForDuplicateInstanceName();

        // Then
    }

    @Step("Duplicate error message will be displayed")
    public void duplicateError() throws InterruptedException {
        // Given

        // When
        liListingPage.waitForMessageToBeDisplayed();
        String duplicateErrorMessage = newInstancePage.duplicateErrorMessage().getText();

        // Then
        assertThat(duplicateErrorMessage).isEqualTo("Duplicate Instance Name");
    }

    @Step("If none of the value is selected Domain DropDown Should give Warning Message")
    public void domainDDWarningMessage() throws InterruptedException {
        // Given
        WebElement domainDDErrorMessage = newInstancePage.domainDDErrorMessage();

        // When
        Thread.sleep(5000);
        newInstancePage.validateDomainDDErrorMessage();

        // Then
        assertThat(domainDDErrorMessage.isDisplayed()).isTrue();

        writeMessage("Length Should be under 50 characters ");
    }

    @Step("Description textBox should give warning message for special charecters and length warning message if chracters more than 255")
    public void descriptionTextBoxSpecialCharacterErrorMsg() {
        // Given
        String textboxValue;
        textboxValue = "~@#$%^&*:;<>.,/}{+";
        String actualDescriptionTextboxValue;
        WebElement descriptionErrorMessage = newInstancePage.descriptionErrorMessage();

        //  When
        actualDescriptionTextboxValue = newInstancePage.validateDescriptionTextBox(textboxValue);

        // Then
        assertThat(descriptionErrorMessage.isDisplayed()).isTrue();

        // Given

        // When
        descriptionErrorMessage.click();

        // Then
        assertThat(newInstancePage.getDuplicateFieldNameErrorMessage()).isEqualTo("Formatting must be numbers, letters, spaces, periods, commas, or (_, -)");

        writeMessage("Special Characters are not allowed " + actualDescriptionTextboxValue);

        // Given
        Driver.webDriver.navigate().refresh();
        textboxValue = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        // When
        actualDescriptionTextboxValue = newInstancePage.validateDescriptionTextBox(textboxValue);

        // Then
        assertThat(descriptionErrorMessage.isDisplayed());
        assertThat(actualDescriptionTextboxValue.length()).isGreaterThan(255);

        // Given

        // When
        descriptionErrorMessage.click();

        // Then
        assertThat(newInstancePage.getDuplicateFieldNameErrorMessage()).isEqualTo("Length should be under 255 characters");


        writeMessage("Length should be under 255 characters " + actualDescriptionTextboxValue);
    }

    @Step("Click on Cancel Button to cancel the Instance Creation")
    public void clickCancelButton() throws InterruptedException {
        // Given

        // When
        newInstancePage.clickCancelButton();

        // Then
    }

    @Step("Learning Instance Page is displayed")
    public void learningInstancePageDisplayed() throws InterruptedException {
        // Given

        // When
        clickLearningInstanceTab();

        // Then
    }


    @Step("Click on Instance Name")
    public void clickOnSearchedInstanceName() {
        // Given

        // When
         liListingPage.clickOnSearchedInstanceName();

        // Then
    }


    @Step("Get the total number of files uploaded")
    public void getTotalNumberOfFilesUploaded() {
        // Given
        totalFilesUploadedNewInstancePage = totalNumberOfFilesUploaded.substring(15, totalNumberOfFilesUploaded.length() - 18);

        // When

        // Then

        System.out.println("Total files" + totalFilesUploadedNewInstancePage);
    }

    private static final int INSTANCE_NAME = 0;
    @Step("Check the Instance name in analyze page is same as Instance name was given while creating instance")
    public void matchInstanceName() {
        // Given

        // When
        List<String> analyzePageDetailList = analyzingPage.learningInstanceValueDetails();

        // Then
        assertThat(analyzePageDetailList).isNotNull();
        assertThat(uniqueList).isNotNull();
        assertThat(analyzePageDetailList.indexOf(INSTANCE_NAME)).isEqualTo(uniqueList.indexOf(INSTANCE_NAME));
    }

    @Step("Enter all the details to create new instance with uploading <1> file and domain <Invoices> primary language <English> and add form field <Name> and table field <Serial Number>")
    public void createInstanceWithAdditionalFormAndTableFields(int filesUploaded, String domainName, String primaryLanguage, String formFieldName, String tableFieldName) throws Exception {

        // Given
        List<String> addedFormFieldAndTableField = new ArrayList<String>();

        // When
        createNewInstance(filesUploaded, domainName, primaryLanguage);
        addedFormFieldAndTableField = newInstancePage.getAddedFormField(formFieldName, tableFieldName);
        addedFormFieldAndTableField.forEach(addedFormAndTableFields -> {
            uniqueList.add(addedFormAndTableFields);
        });

        // Then
    }

    @Step("After classification click on Create Bot Link")
    public void clickOnCreateBotLink() throws Exception {
        // Given

        // When
        botPageStepImplementation.countNumberOfGroupsCreatedAndClickONCreateBot();

        // Then
    }

    @Step("Ensure the added form field and table field for Domain <Invoices> are displayed in designing window")
    public void getTheAddedFormAndTableFieldsValueFromDesigner(String domainName) throws Exception {
        if (domainName.equals("Other")) {
            // Given
            baseClass.runBatchFile(System.getenv("getAddedFormAndTableFieldFileForDomainOther"));

            // When
            String results = baseClass.validateDesignerLogs(System.getenv("getAddedFormAndTableFieldLogFileForDomainOther"));

            // Then
            assertThat(results).isEqualTo("Designer Launched : Pass :Additional Field Pass :Additional Table Pass :");
        } else {
            // Given
            baseClass.runBatchFile(System.getenv("getAddedFormAndTableFieldFile"));

            // When
            String results = baseClass.validateDesignerLogs(System.getenv("getAddedFormAndTableFieldLogFile"));

            // Then
            assertThat(results).isEqualTo("Designer Launched : Pass :Additional Field Pass :Additional Table Pass :");
        }
    }


    @Step("Click on browse button of create new instance page and upload 151 files,  limit exceed warning message should be displayed")
    public void UploadMoreThen150files() throws Exception {
        // Given

        // When
        newInstancePage.clickOnBrowseButton();
        baseClass.uploadAllFiles(smokeTestDocumentsFolder + "\\" + "MaxDocs");

        // Then
        assertThat(newInstancePage.getBrowseButtonErrorMessage()).isEqualTo("File uploads are limited to 150 files");
    }

    @Step("Refresh the page")
    public void refrechThePage() {
        Driver.webDriver.navigate().refresh();
    }

    @Step("Click on browse button of create new instance page and upload unsupported file, file type error message should be displayed")
    public void uploadUnsupportedFile() throws AWTException {
        // Given

        // When

        newInstancePage.clickOnBrowseButton();
        baseClass.uploadAllFiles(smokeTestDocumentsFolder + "\\" + "BatchFiles");

        // Then
        assertThat(newInstancePage.getBrowseButtonErrorMessage()).isEqualTo("Allowed file types: jpg, jpeg, pdf, tiff, tif, png");
    }

    @Step("Click on browse button of create new instance page and upload above the max file size , limit to under 5 MB per file message should be displayed")
    public void uploadMaxSizeFile() throws AWTException {
        // Given

        // When

        newInstancePage.clickOnBrowseButton();
        baseClass.uploadAllFiles(smokeTestDocumentsFolder + "\\" + "TaskBots");

        // Then
        assertThat(newInstancePage.getBrowseButtonErrorMessage()).isEqualTo("You have selected files above the max file size. Limit to under 5 MB per file.");

    }

    @Step("On Create New Instance Page select <Invoices> From Domain DropDown, add <Invoice No> as <Form> Field")
    public void addFormFieldToCheckDuplicateFieldName(String domainName, String fieldName,String fieldType) {
        // Given

        // When
        newInstancePage.selectDomain(domainName);
        newInstancePage.addField(fieldName,fieldType);

        // Then
        assertThat(newInstancePage.otherFieldsTextBoxErrorBox().isDisplayed()).isTrue();
    }

    @Step("Error <No duplicate field names allowed> message should be displayed")
    public void duplicateFieldNameErrorMessage(String errorMessage) {
        // Given

        // When
        newInstancePage.clickOnFieldNameTextBox();

        // Then
        assertThat(newInstancePage.getDuplicateFieldNameErrorMessage()).isEqualTo(errorMessage);

    }
}



