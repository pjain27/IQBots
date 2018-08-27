package pageobjects;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import utils.testBase;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class BotListingPage {
    //By Pritisundar
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Search...']")
    private WebElement searchBots;
    @FindBy(how = How.XPATH, using = "//span[@class='icon fa fa-play']")
    private WebElement runOnceButton;
    @FindBy(how = How.XPATH, using = "//span[text()='Files Processed']/following-sibling::span[1]")
    private WebElement noOfFileProcessed;
    @FindBy(how = How.XPATH, using = "//span[text()='Files Processed']/following-sibling::span[2]")
    private WebElement totalNoOfFileProcessed;
    @FindBy(how = How.XPATH, using = "//span[text()='Files successfully processed']/following-sibling::span")
    private WebElement fileSuccessfullyProcessed;
    @FindBy(how = How.XPATH, using = "//span[text()='Files sent for validation']/following-sibling::span")
    private WebElement sentFiles;
    @FindBy(how = How.XPATH, using = "(//div[@class='aa-flex-grid-item'])[6]")
    private WebElement documentAccuracy;
    @FindBy(how = How.XPATH, using = "(//div[@class='aa-flex-grid-item'])[7]")
    private WebElement fieldAccuracy;
    @FindBy(how = How.XPATH, using = "//*[@type=\"text\"]")
    private WebElement searchTextbox;
    @FindBy(how = How.XPATH, using = "(//BUTTON[@type='button'])[3]")
    private WebElement searchDropListName;
    @FindBy(how = How.XPATH, using = "//*[@class = 'pagetitle-label']")
    private WebElement instanceName;
    @FindBy(how = How.XPATH, using = "//DIV[@class='datafilter-tag-label']")
    private WebElement displayedSearchItemLabel;
    @FindBy(how = How.XPATH, using = "//*[@class='navigation-primaryoption-button-title'][text()='Bots']")
    private WebElement botTab;
    @FindBy(how = How.XPATH, using = ".//*[@id='aa-main-container']/div[3]/div[1]/div[1]/div[1]/label/div[1]/div/select")
    private WebElement searchFieldDropDown;
    @FindBy(how = How.XPATH, using = "//*[@class='pagetitle-label']")
    private WebElement botPageTitle;
    @FindBy(how = How.XPATH, using = "//*[@class='aa-project-bots-header-actions']")
    private WebElement botPageHeader;
    @FindBy(how = How.XPATH, using = "(//tr[1])[2]/td[3]")
    private WebElement status;
    @FindBy(how = How.XPATH, using = "(//tr[1])[2]/td[4]")
    private WebElement success;
    @FindBy(how = How.XPATH, using = "(//tr[1])[2]/td[5]")
    private WebElement validation;

   private By searchTextBox = By.xpath("//input[@placeholder='Search...']");
    private By spinnerOFLIDetailPage = By.xpath("//IMG[@src='/images/ring-loader.gif']");

    Robot robot;
    testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);

    // Added by vandana
    @FindBy(how = How.XPATH, using = ".//*[@id='aa-main-container']/div[3]/div[1]/div[3]")
    private WebElement botsNotFoundMessage;
    @FindBy(how = How.XPATH, using = ".//*[@id='aa-main-container']/div[3]/div[1]/div[3]")
    private WebElement moveInstanceFromProductionToStagingOnDetailPage;
    @FindBy(how = How.XPATH, using = " .//*[@id='aa-main-container']/div[3]/div[1]/div[3]/table/tbody/tr/td[2]")
    private List<WebElement> listOfInstancenameOnBotPageTable;
    @FindBy(how = How.XPATH, using = ".//*[@id='aa-main-container']/div[3]/div[1]/div[3]/table/tbody/tr/td[1]")
    private List<WebElement> listOfGroupNameOnBotPageTable;
    @FindBy(how = How.XPATH, using = "//*[@class='app-notification-container danger']")
    private WebElement errorMessageAfterRunBotMoreThenOnce;

    private WebDriverWait wait = new WebDriverWait(Driver.webDriver, 30);

    @FindBy(how = How.CSS, using = ".aa-bots-placeholder")
    private WebElement noCurrentBotsMessage;
    //-----

    // Get Searchbox WebElements
    public By getSearchBox() {
        return searchTextBox;
    }

    // Get Bot Page Title
    public WebElement getBotPageTitle() {
        return botPageTitle;
    }

    // Search Bot by Instance Name
    public void searchBots(String botsByInstanceName) {
        //By Pritisundar
        searchBots.sendKeys(botsByInstanceName);
        searchBots.sendKeys(Keys.ENTER);
    }

    // Perform click Action on Group Name
    public void clickOnGroupName(String group_Name) {
        //By Pritisundar
        for (int i = 1; i <= 3; i++) {
            String inName = allBotsTableDataPath(i, 1);
            System.out.println(inName);
            if (inName.equalsIgnoreCase(group_Name)) {
                System.out.println("----if statement-----");
                WebElement element = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(i, 1)));
                Actions act = new Actions(Driver.webDriver);
                act.moveToElement(element).build().perform();
                System.out.println("Clicked on Group name");
                break;
            }
        }
    }

    // Perform click Action on Run once Button
    public void clickOnRunButton() {
        runOnceButton.click();
    }

    // Run bot of Instance by group name
    public void runBotGroup(String instanceName) throws Exception {
        //By Pritisundar
        for (int i = 1; i <= 3; i++) {
            String inName = allBotsTableDataPath(i, 1);
            System.out.println(inName);
            if (inName.equalsIgnoreCase(instanceName)) {
                WebElement element = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(i, 1)));
                Actions act = new Actions(Driver.webDriver);
                act.moveToElement(element).build().perform();
                clickOnRunButton(i);
                break;
            }
        }
    }

    // Generate dynamic xpath for bot details table
    public String allBotsTableFullPath(int rowNum, int colNum) {
        //By Pritisundar
        String pathPart1 = "//tr[";
        String pathPart2 = "]/td[";
        String pathPartLast = "]/a";
       return (pathPart1 + rowNum + pathPart2 + colNum + pathPartLast);
        //String text=Driver.webDriver.findElement(By.xpath(fullPath)).getText();
        //System.out.println("----text----"+text);
    }

    // Get value from dynamically generated xpath for bot details
    public String allBotsTableDataPath(int rowNum, int colNum) {
        //By Pritisundar
        String pathPart1 = "//tr[";
        String pathPart2 = "]/td[";
        String pathPartLast = "]/a";
        String fullPath = pathPart1 + rowNum + pathPart2 + colNum + pathPartLast;
        String text = Driver.webDriver.findElement(By.xpath(fullPath)).getText();
        System.out.println("----text----" + text);
        return text;
    }

    // Perform click action on Bot run button
    public void clickOnRunButton(int rowNum) throws Exception {
        //By Pritisundar
        String pathPart1 = "(//span[@class='icon fa fa-play'])[";
        String pathPart2 = "]";
        String fullPath = pathPart1 + rowNum + pathPart2;
        System.out.println(fullPath);
        System.out.println(Driver.webDriver.findElement(By.xpath(fullPath)).isEnabled());
        WebElement element = Driver.webDriver.findElement(By.xpath(fullPath));
        Actions act = new Actions(Driver.webDriver);
        act.moveToElement(element).build().perform();
    }

    // Perform click action on Edit button in Bot listing grid
    public void clickOnEditButton(int rowNum) {
        //By Pritisundar
        String pathPart1 = "(//span[@class='icon fa fa-pencil'])[";
        String pathPart2 = "]";
        String fullPath = pathPart1 + rowNum + pathPart2;
        Driver.webDriver.findElement(By.xpath(fullPath)).click();
    }

    // Get all details before bot run to validate
    public void getAllDetailsOfBotSummary(String expectedResultsSheetName) throws Exception {
        //By Pritisundar
        System.out.println(noOfFileProcessed.getText());
        String noofFilePrcs = noOfFileProcessed.getText();
        //Assert.assertEquals(noofFilePrcs, testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 1, 1).toString());
        assertThat(noofFilePrcs).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 1, 1));

        System.out.println("getAllDetailsBeforeRun" + totalNoOfFileProcessed.getText());
        String totalNoofFile = totalNoOfFileProcessed.getText();
        //Assert.assertEquals(totalNoofFile, testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 2, 1).toString());
        assertThat(totalNoofFile).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 2, 1));

        System.out.println("getAllDetailsBeforeRun" + fileSuccessfullyProcessed.getText());
        String fileUnderSuccess = fileSuccessfullyProcessed.getText();
        //Assert.assertEquals(fileUnderSuccess, testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 3, 1).toString());
        assertThat(fileUnderSuccess).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 3, 1));

        System.out.println(sentFiles.getText());
        String noOfFilesent = sentFiles.getText();
        //Assert.assertEquals(noOfFilesent, testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 4, 1).toString());
        assertThat(noOfFilesent).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 4, 1));

        System.out.println(documentAccuracy.getText());
        String accuracyDocument = documentAccuracy.getText();
        //Assert.assertEquals(accuracyDocument, testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 5, 1).toString());
        assertThat(accuracyDocument).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 5, 1));

        System.out.println(fieldAccuracy.getText());
        String fAccuracy = fieldAccuracy.getText();
        //Assert.assertEquals(fAccuracy, testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 6, 1).toString());
        assertThat(fAccuracy).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 6, 1));
        System.out.println("Bot Listing page Before bot run completed");
    }

    // Wait for Bot Page
    public void waitUntilPageHeaderIsDisplayed() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(Driver.webDriver).withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                botPageHeader.isDisplayed();
                return true;
            }
        });
    }

    // Get instance name
    public WebElement getBotPageInstanceName() {
        return instanceName;
    }

    // Get Search Drop down
    public WebElement getSearchFieldDropDown() {
        return searchFieldDropDown;
    }

    // Close visionbot designer
    public void closeDesigner() throws Exception {
        robot = new Robot();
        robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_F4);
    }

    // Perform click action on Bots Tab
    public void clickOnBotTab() {
        botTab.click();
    }


    // Select criteria as Search by Instance Name
   
    public void selectInstanceName(String instanceName) {
        ExpectedConditions.visibilityOf(searchFieldDropDown);
        ExpectedConditions.elementToBeClickable(searchFieldDropDown);
        Select objSel = new Select(searchFieldDropDown);
        //objSel.selectByValue("projectName");
        objSel.selectByVisibleText(instanceName);
    }

    // Perform search by Instance Name
   
    public String searchForInstanceName(String learningInstancePageInstanceName) {
        ExpectedConditions.visibilityOf(searchTextbox);
        searchTextbox.sendKeys(learningInstancePageInstanceName);
        searchTextbox.sendKeys(Keys.ENTER);
        ExpectedConditions.visibilityOf(searchDropListName);
        //searchDropListName.click();
        return instanceName.getText();
    }

    // Move Bot from Staging to Production
    public void moveFromStagingToProduction(int rowNum) {
        String pathPart1 = "(//DIV[@class='toggleinput-control-indicator'])[";
        String pathPart2 = "]";
        String fullPath = pathPart1 + rowNum + pathPart2;
        Driver.webDriver.findElement(By.xpath(fullPath)).click();
    }

    // Calculate Toggle button [move to production button] path in Bot Listing page
    public String productionButtonPath(int rowNum) {
        //By Pritisundar
        String path1 = "(//div[@class='toggleinput-control'])[";
        String path2 = "]";
        return (path1 + rowNum + path2);
    }

    // Move Group from Staging to Production in Bot Listing page
    public void moveGroupToProduction(String group_Name) throws Exception {
        //By Pritisundar
        for (int i = 1; i <= 3; i++) {
            String inName = allBotsTableDataPath(i, 1);
            System.out.println(inName);
            if (inName.equalsIgnoreCase(group_Name)) {
                System.out.println("----if statement-----");
                WebElement element = Driver.webDriver.findElement(By.xpath(productionButtonPath(i)));
                System.out.println(element.isDisplayed());
                element.click();
                System.out.println("Moved group to Production");
                break;
            }
        }

    }

    // Assert Bot details after moving Bot from Staging to Production
    public void getAllDetailsAfterMoveProduction(String expectedResultsSheetName) throws Exception {
        //By Pritisundar

        String statusNow = status.getText();
        System.out.println(statusNow);
        assertThat(statusNow).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 1, 1));

        String successNow = success.getText();
        System.out.println(successNow);
        assertThat(successNow).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 2, 1));

        String validationNow = validation.getText();
        System.out.println(validationNow);
        assertThat(validationNow).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 3, 1));

        System.out.println(noOfFileProcessed.getText());
        String noofFilePrcs = noOfFileProcessed.getText();
        assertThat(noofFilePrcs).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 4, 1));

        System.out.println(totalNoOfFileProcessed.getText());
        String totalNoofFile = totalNoOfFileProcessed.getText();
        assertThat(totalNoofFile).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 5, 1));

        System.out.println(fileSuccessfullyProcessed.getText());
        String fileUnderSuccess = fileSuccessfullyProcessed.getText();
        assertThat(fileUnderSuccess).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 6, 1));

        System.out.println(sentFiles.getText());
        String noOfFilesent = sentFiles.getText();
        assertThat(noOfFilesent).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 7, 1));

        System.out.println(documentAccuracy.getText());
        String accuracyDocument = documentAccuracy.getText();
        assertThat(accuracyDocument).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 8, 1));

        System.out.println(fieldAccuracy.getText());
        String fAccuracy = fieldAccuracy.getText();
        assertThat(fAccuracy).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 9, 1));
        System.out.println(" After Move to production validation all complete");
    }

    // Search deleted instance
   
    public void searchForDeteletedInstanceNameOnBotPage(String learningInstancePageInstanceName) {
        ExpectedConditions.visibilityOf(searchTextbox);
        searchTextbox.sendKeys(learningInstancePageInstanceName);

    }

    // Get Instance Not Available message
    public String getMessagedisplayedFromBotPageForSearchInstance() {
        return botsNotFoundMessage.getText();
    }

    // Wait for Bot listing Table
    public void waitForBotTableToBeDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='datatable-table']")));
    }

    // Validate List Items for Search dropdown

    public List<String> getBotPageSearchDropDownValue() {
        ExpectedConditions.visibilityOf(searchFieldDropDown);
        List<String> uiDomainDDList = new ArrayList<String>();
        Select selectDomainType = new Select(searchFieldDropDown);
        for (int i = 1; i < selectDomainType.getOptions().size(); i++)
            uiDomainDDList.add(selectDomainType.getOptions().get(i).getAttribute("value"));
        return uiDomainDDList; //return of Domain DropDown List
    }

    public String getNoCurrentBotsMessage() {
        return noCurrentBotsMessage.getText();
    }

    public List<String> getListOfInstanceNameonBotPage() {
        List<String> botPageAllInstanceName = new ArrayList<String>();
        for(WebElement instanceName : listOfInstancenameOnBotPageTable){
            botPageAllInstanceName.add(instanceName.getText());
        }
        return botPageAllInstanceName;
    }

    public List<String> getGroupNameFromBotTableList() {
        List<String> botPageGroupName = new ArrayList<String>();
        for(WebElement instanceName : listOfGroupNameOnBotPageTable){
            botPageGroupName.add(instanceName.getText());
        }
        return botPageGroupName;
    }

    public String getErrorMessageAfterBotRunMoreTheOnce() {
        return errorMessageAfterRunBotMoreThenOnce.getText();
    }

    public String getTotalFileSuccessfullyProcessed() {
        ExpectedConditions.visibilityOf(noOfFileProcessed);
        return  noOfFileProcessed.getText();
    }
}
