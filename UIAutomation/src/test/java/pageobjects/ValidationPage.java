package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class ValidationPage {

    // Validator Icon
    @FindBy(how = How.XPATH, using = "//a/i[@class='launch-validator-icon']")
    private WebElement validatorIcon;
    // Validation Label
    @FindBy(how = How.XPATH, using = "//div[@class='pagetitle-label']")
    private WebElement validationLabel;
    // Learning Instances Tab
    @FindBy(how = How.XPATH, using = "//a[@class='navigation-primaryoption-button' and @href='/learning-instances/validations']")
    private WebElement learningInstancesTab;
    // Dropdown
    @FindBy(how = How.XPATH, using = "//SELECT[@class='selectinput-input']")
    private WebElement dropDown;
    // Search text box
    @FindBy(how = How.XPATH, using = "//div[@class='textinput-cell textinput-cell-input']/input")
    private WebElement textBoxSearch;
    //Search icon
    @FindBy(how = How.XPATH, using = "//SPAN[@class='icon fa fa-search datafilter-search-icon']")
    private WebElement searchIcon;
    //Li status
    @FindBy(how = How.XPATH, using = "(//tr/td[2])[1]")
    private WebElement status;
    //Li status
    @FindBy(how = How.XPATH, using = "(//tr/td[3])[1]")
    private WebElement fileProcessed;
    //Li status
    @FindBy(how = How.XPATH, using = "(//tr/td[4])[1]")
    private WebElement sentToValidation;
    //Li status
    @FindBy(how = How.XPATH, using = "(//tr/td[5])[1]")
    private WebElement validated;
    //Li status
    @FindBy(how = How.XPATH, using = "(//tr/td[6])[1]")
    private WebElement invalidFiles;

    // Perform click action on validator icon
    public void clickOnValidatorIcon() {
        validatorIcon.click();
    }

    // Get My Learning Instance Page Title
    public String getMyLearningInstanceLabel() {
        return validationLabel.getText();
    }

    // Perform Click action on Learning Instance Tab
    public void learningInstanceTabClick() {
        learningInstancesTab.click();
    }

    public void searchLI(String InstanceName) {
        //By Pritisundar
        Select objSel = new Select(dropDown);
        objSel.selectByValue("name");
        textBoxSearch.click();
        textBoxSearch.sendKeys(InstanceName);
        textBoxSearch.sendKeys(Keys.ENTER);
    }

    public String statusLi(){
        return status.getText();
    }

    public String fileProcessed(){
        return fileProcessed.getText();
    }
    public String sentToValidation(){
        return sentToValidation.getText();
    }
    public String validated(){
        return validated.getText();
    }
    public String invalidFiles(){
        return invalidFiles.getText();
    }
}


