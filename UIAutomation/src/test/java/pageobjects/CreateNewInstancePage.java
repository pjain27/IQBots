package pageobjects;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.*;
import utils.testBase;

import static driver.Driver.webDriver;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class CreateNewInstancePage extends testBase {
    /*New Learning Instance Page WebElements */
    // Page Header
    @FindBy(how = How.XPATH, using = "//div[@class='pagetitle-label']")
    private WebElement newInstanceheader;
    // New Instance Link
    @FindBy(how = How.XPATH, using = "//a[@href='/learning-instances/new']")
    private WebElement newInstance;
    // Instance Name Text Box
    @FindBy(how = How.XPATH, using = "//input[@name='project.name']")
    private WebElement instanceName;
    // English Language Selection
    @FindBy(how = How.XPATH, using = "//option[text()='English']")
    private WebElement language;
    // Invoice Domain Selection
    @FindBy(how = How.XPATH, using = "//option[text()='Invoices']")
    private WebElement invoiceDomain;
    @FindBy(how = How.XPATH, using = "//input[@type='text'][@name='project.customProjectType']")
    private WebElement domainNameTextBox;
    // Create Instance Button
    @FindBy(how = How.XPATH, using = "//button[@type='submit' and @class='commandbutton-button commandbutton-button--clickable']")
    private WebElement createInstanceandanalyze;
    // Cancel Button
    @FindBy(how = How.XPATH, using = ".//*[@id='aa-main-container']/form/div[2]/div/a/div/button")
    private WebElement cancel;
    // Upload File
    @FindBy(how = How.XPATH, using = "//input[@type='file']")
    private WebElement inputFile;
    // All checked Fields
    @FindBy(how = How.XPATH, using = "//*[@class='checkboxinput checkboxinput--interactive checkboxinput--checked checkboxinput--theme-light']")
    List<WebElement> checkedFields;
    // Browse Button
    @FindBy(how = How.XPATH, using = "//div[@class='browsefield']")
    private WebElement browseButton;
    @FindBy(how = How.XPATH, using = " //*[@class='popup popup--theme-error popup--position-top popup--hover']")
    private WebElement errorMessageOfBrowseButton;

    // Learning Instance Page Title
    @FindBy(how = How.XPATH, using = "//DIV[@class='pagetitle-label'][text()='My Learning Instances']")
    private WebElement getlearinstPageTitle;
    // Create New Learning Instance Page Title
    @FindBy(how = How.XPATH, using = "//DIV[@class='pagetitle-label'][text()='Create new learning instance']")
    private WebElement getTitle;
    // Domain DropDown
    @FindBy(how = How.XPATH, using = ".//*[@id='aa-main-container']/form/div[1]/div[5]/label/div[2]/div/select")
    private WebElement defaultDomain;
    // Learning Instance Tab
    @FindBy(how = How.XPATH, using = "//SPAN[@class='navigation-primaryoption-button-title'][text()='Learning Instances']")
    private WebElement liTab;
    //--COG-901 Primary Language
    @FindBy(how = How.XPATH, using = ".//*[@id='aa-main-container']/form/div[1]/div[3]/label/div[2]/div/select")
    private WebElement primaryLanguageDropDown;
    //-- COG- 886 Elements after Clicking Domain DropDown ----
    @FindBy(how = How.XPATH, using = "//DIV[@class='aa-well']")
    private WebElement correspondingFieldsBox;
    @FindBy(how = How.XPATH, using = "//DIV[@class='aa-well-header'][text()='Standard form fields']")
    private WebElement standardFormFieldsLabel;
    @FindBy(how = How.XPATH, using = "(//DIV[@class='aa-project-fields'])[1]")
    private WebElement invoiceAndPurchasordereStandardFormFields;
    @FindBy(how = How.XPATH, using = "//label[@class=\"checkboxinput checkboxinput--interactive checkboxinput--checked checkboxinput--theme-light\"]")
    private List<WebElement> checkedStandardFormFields;
    @FindBy(how = How.XPATH, using = "(//DIV[@class='aa-project-fields'])[2]")
    private WebElement invoiceAndPurchasordereStandardTableFields;
    @FindBy(how = How.XPATH, using = "//DIV[@class='aa-project-fields']")
    private WebElement billingAndAllStandardFormFields;
    @FindBy(how = How.XPATH, using = "//DIV[@class='aa-well']")
    private WebElement otherStandardFormFields;
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Add as form')]")
    private WebElement addFormButton;
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Add as table')]")
    private WebElement addTableButton;
    @FindBy(how = How.XPATH, using = "//DIV[@class='aa-well-header'][text()='Standard table fields']")
    private WebElement standardTableFieldsLabel;
    @FindBy(how = How.XPATH, using = "((//DIV[@class='aa-project-fields'])[2]")
    private WebElement standardTableFieldsBox;
    @FindBy(how = How.XPATH, using = "//input[@type='text'][@name='newCustomFieldName']")
    private WebElement otherFieldsTextBox;
    @FindBy(how = How.XPATH, using = "//label[@class='textinput textinput--invalid']")
    private WebElement otherFieldsTextBoxErrorBox;
    @FindBy(how = How.XPATH, using = "//*[@class='popup popup--theme-error popup--position-top popup--focus popup--hover']")
    private WebElement dupliacteFieldnameErrorMessage;

    @FindBy(how = How.XPATH, using = "//input[(@placeholder='Name')]")
    private WebElement otherFormFieldsAddedValue;
    @FindBy(how = How.XPATH, using = "//input[(@placeholder='Serial Number')]")
    private WebElement otherTableFieldsAddedValue;
    @FindBy(how = How.XPATH, using = "//DIV[@class='aa-well-header'][text()='Other fields (Optional)']")
    private WebElement otherFieldsLabel;
    @FindBy(how = How.XPATH, using = "//DIV[@class='aa-well-header'][text()='Instance Fields']")
    private WebElement selectedDomainOtherLabel;
    // COG-897 Instance Name TextBox
    @FindBy(how = How.XPATH, using = "//input[@name='project.name']")
    private WebElement textboxInstanceName;
    @FindBy(how = How.XPATH, using = "//LABEL[@class='textinput textinput--invalid']")
    private WebElement textboxInstanceNameRequired;
    @FindBy(how = How.XPATH, using = "//BUTTON[@type='submit']")
    private WebElement createNewInstanceButton;
    @FindBy(how = How.XPATH, using = "(//a[@class='aa-link underline'])")
    private List<WebElement> listOfInstanceName;
    // Create Instance Button
    @FindBy(how = How.XPATH, using = "//button[@type='submit' and @class='commandbutton-button commandbutton-button--clickable']")
    private WebElement createInstanceandAnalyzeButton;
    @FindBy(how = How.XPATH, using = "//DIV[@class='browsefield-input']")
    private WebElement inputFileTextBox;
    // Description Text box
    @FindBy(how = How.XPATH, using = "//input[@name='project.description']")
    private WebElement description;
    @FindBy(how = How.XPATH, using = "//LABEL[@class='textinput textinput--invalid']")
    private WebElement descriptionErrorMessage;
    @FindBy(how = How.XPATH, using = "//DIV[text()='Duplicate Instance Name']")
    private WebElement duplicteErrorMessage;
    @FindBy(how = How.XPATH, using = "//DIV[@class='app-notification-container danger']")
    private WebElement duplicteErrorMessageContainer;
    @FindBy(how = How.XPATH, using = "//*[@class='textinput textinput--invalid']")
    private WebElement domainDDErrorMessage;

    // Driver Wait initialization
    private WebDriverWait wait = new WebDriverWait(webDriver, 15);

    // Get Page Title for New Instance Page
    public String getNewInstancePageTitle() {
        wait.until(ExpectedConditions.textToBePresentInElement(newInstanceheader, "Create new learning instance"));
        System.out.println(newInstanceheader.getText());
        return newInstanceheader.getText();
    }

    // Get Learning Instance Page Title
    public String getlearningInstancePageTitle() {
        return getlearinstPageTitle.getText();
    }

    // Get Create Instance Button
    public WebElement getCreateInstanceButton() {
        return createInstanceandanalyze;
    }

    // Get Cancel Button
    public WebElement getcancel() {
        return cancel;
    }

    // Get New Instance Button
    public WebElement getNewInstanceButton() {
        return newInstance;
    }

    // Get File Upload WebElement
    public WebElement uploadFile() {
        return inputFile;
    }

    // Get Invoice Domain fields [default selection]
    public void getSelectedFields() {
        for (int i = 0; i < 6; i++) {
            System.out.println(checkedFields.get(i).getText());
        }
    }

    // Get Inserted Text
    public String inputFileTextBox() {
        return this.inputFileTextBox.getText();
    }

    // Get Duplicate Instance Error Message
    public WebElement duplicateErrorMessage() {
        return duplicteErrorMessage;
    }

    // Get Domain Selection Error Message
    public WebElement domainDDErrorMessage() {
        return domainDDErrorMessage;
    }

    // Get Default Domain
    public WebElement defaultDomain() {
        return defaultDomain;
    }

    // Get Description Error Message
    public WebElement descriptionErrorMessage() {
        return descriptionErrorMessage;
    }

    //  Get Instance Name Error Message
    public WebElement textboxInstanceNameRequired() {
        return textboxInstanceNameRequired;
    }

    // Get New Instance Page Title
    public String getLITitle() {
        ExpectedConditions.visibilityOf(getTitle);
        return getTitle.getText();
    }

    // Get Default Domain
    public String domainType() {
        ExpectedConditions.visibilityOf(defaultDomain);
        Select domainWebElement = new Select(defaultDomain);
        return domainWebElement.getFirstSelectedOption().getText();
    }

    // Get Default Primary Language
    public String primaryLanguageDefaultType() {
        ExpectedConditions.visibilityOf(primaryLanguageDropDown);
        Select primaryLanguageWebElement = new Select(primaryLanguageDropDown);
        return primaryLanguageWebElement.getFirstSelectedOption().getText();
    }

    // Get List of Primary Language
    public List<String> verifyPrimaryLanguageList() {
        ExpectedConditions.visibilityOf(primaryLanguageDropDown);
        List<String> uiPrimaryLanguageDDList = new ArrayList<String>();
        Select selectDomainType = new Select(primaryLanguageDropDown);
        for (int i = 1; i < selectDomainType.getOptions().size(); i++)
            uiPrimaryLanguageDDList.add(selectDomainType.getOptions().get(i).getAttribute("text"));
        return uiPrimaryLanguageDDList; //return of Primary DropDown List
    }

    // Get list of Domain DropDown
    public List<String> verifyDomainList() {
        ExpectedConditions.visibilityOf(defaultDomain);
        List<String> uiDomainDDList = new ArrayList<String>();
        Select selectDomainType = new Select(defaultDomain);
        for (int i = 1; i < selectDomainType.getOptions().size(); i++)
            uiDomainDDList.add(selectDomainType.getOptions().get(i).getAttribute("value"));
        return uiDomainDDList; //return of Domain DropDown List
    }

    // Get Selected Domain Name
    public String getSelectedDomain(String domainName) {
        ExpectedConditions.visibilityOf(defaultDomain);
        ExpectedConditions.elementToBeClickable(defaultDomain);
        Select objSel = new Select(defaultDomain);
        objSel.selectByValue(domainName);
        return defaultDomain.getAttribute("value");
    }

    // Get List of Standard Fields
    public List<String> getCheckedListForCreateNewLI() {
        return this.getStandardFormFields().get(2);
    }

    // Perform Click Action on New Instance Button
    public void clickOnNewInstance() throws InterruptedException {
        ExpectedConditions.elementToBeClickable(newInstance);
        newInstance.click();
    }

    // Perform Click Action on Create & Analyze Page
    public void clickOnCreateAndAnalyzeButton() {
        ExpectedConditions.elementToBeClickable(createInstanceandanalyze);
        createInstanceandanalyze.click();
        waitForAnalyzePage();
    }

    // Perform Click Action on Browse Button
    public void clickOnBrowseButton() {
        browseButton.click();
    }

    // Perform Click action on Learning Instance Tab
    public void clickLearningInstanceTab() {
        ExpectedConditions.visibilityOf(liTab);
        ExpectedConditions.elementToBeClickable(liTab);
        liTab.click();
    }

    // Perform Click action on New Instance Button
    public void clickNewInstance() {
        ExpectedConditions.visibilityOf(newInstance);
        ExpectedConditions.elementToBeClickable(newInstance);
        newInstance.click();
    }

    // Method to get list of Standard Form Fields and Standard Table Fields based on selected value from Domain DropDown
    public List<List<String>> getStandardFormFields() {
        ExpectedConditions.visibilityOf(defaultDomain);
        ExpectedConditions.visibilityOf(correspondingFieldsBox);
        String selectedValue = defaultDomain.getAttribute("value");
        WebElement formList, tableList;
        List<WebElement> formListCheckedElements;
        if (selectedValue.equals("Invoices") | selectedValue.equals("Purchase Orders")) {
            ExpectedConditions.visibilityOf(standardFormFieldsLabel);
            ExpectedConditions.visibilityOf(standardTableFieldsLabel);
            ExpectedConditions.visibilityOf(addFormButton);
            ExpectedConditions.visibilityOf(addTableButton);
            formList = invoiceAndPurchasordereStandardFormFields;
            tableList = invoiceAndPurchasordereStandardTableFields;
            formListCheckedElements = checkedStandardFormFields;
        } else if (selectedValue.equals("other")) {
            ExpectedConditions.visibilityOf(otherFieldsLabel);
            ExpectedConditions.visibilityOf(addFormButton);
            ExpectedConditions.visibilityOf(addTableButton);
            formList = otherStandardFormFields;
            tableList = addTableButton;
            formListCheckedElements = checkedStandardFormFields;
        } else {
            ExpectedConditions.visibilityOf(standardFormFieldsLabel);
            ExpectedConditions.visibilityOf(addFormButton);
            ExpectedConditions.visibilityOf(addTableButton);
            formList = billingAndAllStandardFormFields;
            tableList = addTableButton;
            formListCheckedElements = checkedStandardFormFields;
        }

        List<String> actualFormList = new ArrayList<String>();
        List<String> actualTableList = new ArrayList<String>();
        List<String> actualCheckFormList = new ArrayList<String>();
        List<List<String>> allList = new ArrayList<List<String>>();

        for (String formField : formList.getText().split("\\r?\\n")) {
            actualFormList.add(formField);
        }
        for (String tableField : tableList.getText().split("\\r?\\n")) {
            actualTableList.add(tableField);
        }

        for (int i = 0; i < formListCheckedElements.size(); i++) {
            actualCheckFormList.add(formListCheckedElements.get(i).getText());
        }
        allList.add(actualFormList);
        allList.add(actualTableList);
        allList.add(actualCheckFormList);
        return allList;
    }

    // Validate Instance Name TextBox
    public String validateInstanceNameTextBox(String textboxValue) {
        ExpectedConditions.visibilityOf(primaryLanguageDropDown);
        ExpectedConditions.elementToBeClickable(primaryLanguageDropDown);
        ExpectedConditions.visibilityOf(defaultDomain);
        ExpectedConditions.elementToBeClickable(defaultDomain);
        ExpectedConditions.visibilityOf(addFormButton);
        ExpectedConditions.visibilityOf(addTableButton);
        ExpectedConditions.visibilityOf(textboxInstanceName);
        textboxInstanceName.sendKeys(textboxValue);
        createNewInstanceButton.click();
        return textboxInstanceName.getAttribute("value");
    }

    // Validate Description TextBox
    public String validateDescriptionTextBox(String descriptionTextboxValue) {
        ExpectedConditions.visibilityOf(description);
        description.sendKeys(descriptionTextboxValue);
        createNewInstanceButton.click();
        return description.getAttribute("value");
    }

    // Validate Domain TextBox
    public void validateDomainDDErrorMessage() throws InterruptedException {
        ExpectedConditions.visibilityOf(primaryLanguageDropDown);
        ExpectedConditions.elementToBeClickable(primaryLanguageDropDown);
        ExpectedConditions.visibilityOf(defaultDomain);
        ExpectedConditions.elementToBeClickable(defaultDomain);
        ExpectedConditions.visibilityOf(addFormButton);
        ExpectedConditions.visibilityOf(addTableButton);
        ExpectedConditions.visibilityOf(textboxInstanceName);
        Select objDomain = new Select(defaultDomain);
        defaultDomain.click();
        defaultDomain.click();
        Thread.sleep(5000);
    }

    // Insert All Required Fields for Instance Creation
    public void insertRequiedFields(String instanceName, String instanceDescription, String  domainName,String primaryLanguage) throws InterruptedException {
        ExpectedConditions.visibilityOf(textboxInstanceName);
        Select objLanguage = new Select(primaryLanguageDropDown);
        objLanguage.selectByVisibleText(primaryLanguage);
        Select objDomain = new Select(defaultDomain);
        objDomain.selectByVisibleText(domainName);
        if(domainName.equals("Other")){
            domainNameTextBox.sendKeys("Domain Other");
        }
        textboxInstanceName.sendKeys(instanceName);
        description.sendKeys(instanceDescription);
        primaryLanguageDropDown.click();
        defaultDomain.click();
        clickOnBrowseButton();
        Thread.sleep(500);
    }

    // Perform Click action on Create Instance Button for duplicate verification
    public void clickOnCreateNewInstanceButtonForDuplicateInstanceName() throws InterruptedException {
        ExpectedConditions.visibilityOf(duplicteErrorMessageContainer);
        ExpectedConditions.visibilityOf(duplicteErrorMessage);
        createInstanceandAnalyzeButton.click();
    }

    // Perform Click action on Cancel button
    public void clickCancelButton() throws InterruptedException {
        ExpectedConditions.visibilityOf(cancel);
        ExpectedConditions.elementToBeClickable(cancel);
        cancel.click();
    }


    public List<String> getAddedFormField(String formFieldName,String tableFieldName) {
        ExpectedConditions.visibilityOf(otherFieldsTextBox);
        ExpectedConditions.visibilityOf(addFormButton);
        ExpectedConditions.elementToBeClickable(addFormButton);
        ExpectedConditions.visibilityOf(addTableButton);
        ExpectedConditions.elementToBeClickable(addTableButton);
        otherFieldsTextBox.sendKeys(formFieldName);
        addFormButton.click();
        otherFieldsTextBox.sendKeys(tableFieldName);
        addTableButton.click();
        List<String> addedFormAndTableFieldsValue =  new ArrayList<String>();
        addedFormAndTableFieldsValue.add(otherFormFieldsAddedValue.getAttribute("placeholder"));
        addedFormAndTableFieldsValue.add(otherTableFieldsAddedValue.getAttribute("placeholder"));
        return addedFormAndTableFieldsValue ;
    }

    public String getBrowseButtonErrorMessage() {
        return errorMessageOfBrowseButton.getText();
    }

    public void selectDomain(String domainName) {
        Select objDomain = new Select(defaultDomain);
        objDomain.selectByVisibleText(domainName);
    }

    public void addField(String fieldName,String fieldType) {
        if(fieldType.equals("Form")) {
            ExpectedConditions.visibilityOf(otherFieldsTextBox);
            ExpectedConditions.visibilityOf(addFormButton);
            ExpectedConditions.elementToBeClickable(addFormButton);
            otherFieldsTextBox.sendKeys(fieldName);
            addFormButton.click();
        } else {
            ExpectedConditions.visibilityOf(otherFieldsTextBox);
            ExpectedConditions.visibilityOf(addTableButton);
            ExpectedConditions.elementToBeClickable(addTableButton);
            otherFieldsTextBox.sendKeys(fieldName);
            addTableButton.click();
        }
    }

    public WebElement otherFieldsTextBoxErrorBox(){
        return otherFieldsTextBoxErrorBox;
    }

    public void clickOnFieldNameTextBox() {
        otherFieldsTextBoxErrorBox.click();
    }
    public String getDuplicateFieldNameErrorMessage() {
        return dupliacteFieldnameErrorMessage.getText();
    }

    // Wait for Learning Instance Tab to be clickable
    public void waitForLITab() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                liTab.click();
                return true;
            }
        });
    }

    // Wait for New Learning Instance Page Load
    public void waitforNewLITitle() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                getTitle.isEnabled();
                return true;
            }
        });
    }

    // Wait for Duplicate Error Message
    public void waitForDuplicatedErrorMsg() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                duplicteErrorMessageContainer.isDisplayed();
                return true;
            }
        });
    }

    // Test support methods
    public List<String> propertyNameAsList(String propertyName) {
        ArrayList<String> propertyNames = new ArrayList<String>();
        String[] array = System.getenv((propertyName)).split(",");
        for (String property : array) {
            propertyNames.add(property.trim());
        }
        return propertyNames;
    }

    // used to extract data from the returned list when using getFormFields(...) method.
    public List<List<String>> getFormFields(String selectedDomain) {
        List<String> expectedStandardFormFields, expectedStandardTableFields, expectedCheckedFormList;
        List<List<String>> result = new ArrayList<List<String>>();
        switch (selectedDomain) {
            case "Invoices":
                expectedStandardFormFields = propertyNameAsList("invoiceStandardFormFields");
                expectedStandardTableFields = propertyNameAsList("invoiceStandardTableFields");
                expectedCheckedFormList = propertyNameAsList("invoiceCheckedList");
                break;
            case "Purchase Orders":
                expectedStandardFormFields = propertyNameAsList("purchaseOrdersFormFields");
                expectedStandardTableFields = propertyNameAsList("purchaseOrdersTableFields");
                expectedCheckedFormList = propertyNameAsList("purchaseOrdersCheckedList");
                break;
            case "Billing Statement":
                expectedStandardFormFields = propertyNameAsList("billingStatementFormFields");
                expectedStandardTableFields = propertyNameAsList("tableButton");
                expectedCheckedFormList = Collections.<String>emptyList();
                break;
            case "Contract":
                expectedStandardFormFields = propertyNameAsList("contractFormFields");
                expectedStandardTableFields = propertyNameAsList("tableButton");
                expectedCheckedFormList = Collections.<String>emptyList();
                break;
            case "Automobile Insurance Claim":
                expectedStandardFormFields = propertyNameAsList("automobileFormFields");
                expectedStandardTableFields = propertyNameAsList("tableButton");
                expectedCheckedFormList = Collections.<String>emptyList();
                break;
            case "Health Insurance Claim (1500)":
                expectedStandardFormFields = propertyNameAsList("health1500FormFields");
                expectedStandardTableFields = propertyNameAsList("tableButton");
                expectedCheckedFormList = Collections.<String>emptyList();
                break;
            case "Health Insurance Claim (UB 04)":
                expectedStandardFormFields = propertyNameAsList("healthUB04FormFields");
                expectedStandardTableFields = propertyNameAsList("tableButton");
                expectedCheckedFormList = Collections.<String>emptyList();
                break;
            default:
                expectedStandardFormFields = propertyNameAsList("otherFields");
                expectedStandardTableFields = propertyNameAsList("tableButton");
                expectedCheckedFormList = Collections.<String>emptyList();
        }
        result.add(expectedStandardFormFields);
        result.add(expectedStandardTableFields);
        result.add(expectedCheckedFormList);
        return result;
    }

    // To Upload multiple files by executing cmd file
    public String getallFilenames(String filePath) throws IOException, InterruptedException {
        //  Added code to input multiple files
        Process p = Runtime.getRuntime().exec(filePath);
        p.waitFor();
        InputStream in = p.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c = -1;
        while((c = in.read()) != -1) {
            baos.write(c);
        }
        String response = new String(baos.toByteArray());
        System.out.println("Response From Exe : "+response);
        List<String> listOfFileNames = new ArrayList<String>();
        for(String fileName: response.split("\\r?\\n")) {
            listOfFileNames.add("\"" + fileName + "\"");
        }
        String multipleFileNames =listOfFileNames.toString().replaceAll("[\\[\\]]", "").replaceAll(",", " ");
        System.out.println(multipleFileNames);
        return multipleFileNames;
    }

}
