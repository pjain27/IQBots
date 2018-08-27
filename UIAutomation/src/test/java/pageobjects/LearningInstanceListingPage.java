package pageobjects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import driver.Driver;
import org.testng.Assert;
import utils.testBase;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class LearningInstanceListingPage extends testBase {

    /* Home Page WebElements*/
    //search box
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Search...']")
    private WebElement searchTextBox;
    // Profile Icon
    @FindBy(how = How.XPATH, using = "//SPAN[@class='icon aa-icon aa-icon-toolbar-user']")
    private WebElement userIcon;
    // Dashboard Tab
    @FindBy(how = How.XPATH, using = "//a[@class='navigation-primaryoption-button' and @href='/dashboard']")
    private WebElement dashboardTab;
    // Learning Instance Tab
    @FindBy(how = How.XPATH, using = "//a[@class='navigation-primaryoption-button' and @href='/learning-instances']")
    private WebElement learningInstanceTab;
    // Bots Tab
    @FindBy(how = How.XPATH, using = "//a[@class='navigation-primaryoption-button' and @href='/bots']")
    private WebElement botsTab;
    // All Tabs
    @FindBy(how = How.XPATH, using = "div[@class='navigation-primaryoptions']//a")
    List<WebElement> tabsList;
    // Expected Tabs
    private List<String> expectedTabs = Arrays.asList("Dashboards", "Learning Instances", "Bots");
    // Logout Button
    @FindBy(how = How.XPATH, using = "//div[@class='user-info-container']//button")
    private WebElement logOut;
    // Logged In UserName
    @FindBy(how = How.XPATH, using = "//div[@class='user-info-container']//span")
    private WebElement loggedInuser;
    // MyTotals on Dashboard
    @FindBy(how = How.XPATH, using = "//span[@class='aa-project-performance-report-section-header--title']")
    private WebElement myTotals;
    // My Learning Instance Label
    @FindBy(how = How.XPATH, using = "//div[@class='pagetitle-label']")
    private WebElement myLearningInstanceLable;
    // IQBots Label
    @FindBy(how = How.XPATH, using = "//div[@class='pagetitle-label']")
    private WebElement iqBotsLable;
    //Learning Instance Page Search For Instance Name
    @FindBy(how = How.XPATH, using = "//INPUT[@type='text']")
    private WebElement searchForInstanceName;
    @FindBy(how = How.XPATH, using = "(//BUTTON[@type='button'])[4]")
    private WebElement searchDDItem;
    @FindBy(how = How.XPATH, using = "//DIV[@class='datafilter-tag-label']")
    private WebElement displaySearchInstanceName;
    @FindBy(how = How.XPATH, using = "(//a[@class='aa-link underline'])")
    private List<WebElement> listOfInstanceName;
    @FindBy(how = How.XPATH, using = ".//*[@id='aa-main-container']/div[3]/div[1]/div[1]/div/div[1]/label/div[1]/div/select")
    private WebElement searchDDLIPage;
    //for click in data row
    @FindBy(how = How.XPATH, using = "(//tr[1])[2]//td[2]")
    private WebElement clickElement;
    //Validate Link
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Validate')]")
    private WebElement validateLink;
    //staging to production or production to staging move button
    @FindBy(how = How.XPATH, using = "//div[@class='toggleinput-control']")
    private WebElement moveButton;
    //above training progress bar
    @FindBy(how = How.XPATH, using = "//div[@class='aa-progress-bar-current']")
    private WebElement aboveTrainingProgressbar;
    //Training progress bar
    @FindBy(how = How.XPATH, using = "(//div[1][@class='aa-progress-bar-current'])[2]")
    private WebElement trainingProgressBar;
    @FindBy(how = How.XPATH, using = "(//div[1][@class='aa-project-preview-info--value'])[2]/span")
    private WebElement noOfVisionBots;
    //accuracy
    @FindBy(how = How.XPATH, using = "(//div[@class='aa-project-preview-info--value'])[3]")
    private WebElement accuracy;
    //instance name
    @FindBy(how = How.XPATH, using = "(//div[@class='aa-project-preview-info--value'])[4]")
    private WebElement nameOfInstance;
    //no of files
    @FindBy(how = How.XPATH, using = "(//div[1][@class='aa-project-preview-info--value'])[5]/span")
    private WebElement noOfFiles;
    //Language
    @FindBy(how = How.XPATH, using = "(//div[@class='aa-project-preview-info--value'])[6]")
    private WebElement primaryLanguage;
    //Environment
    @FindBy(how = How.XPATH, using = "(//div[@class='aa-project-preview-info--value'])[7]")
    private WebElement environment;
    //LI Listing Table Bot Column
    @FindBy(how = How.XPATH, using = "//tr[1]/td[2]")
    private WebElement numberOfBotColumn;
    // Count of rows - instance Created
    @FindBy(how = How.XPATH, using = "//*[@id=\"aa-main-container\"]/div[3]/div[1]/div[3]/table/tbody/tr")
    private List<WebElement> countOfRows;

    // No current instance message
    @FindBy(how = How.CSS, using = ".aa-project-placeholder.aa-grid-row.vertical-center.horizontal-center>span")
    private WebElement noCurrentInstanceMessage;

    // Web elements declared by vandana for delete instance ----
    @FindBy(how = How.XPATH, using = "//*[@class='aa-project-no-results']")
    private WebElement projectsNotFoundMessage;

    @FindBy(how = How.XPATH, using = ".//*[@id='app-notifications']/li/div/div")
    private WebElement instancesuccessfullyDeletedMessage;

    By WaitForMessageInstancesuccessfullyDeleted = By.xpath(".//*[@id='app-notifications']/li/div/div");
    WebDriverWait wait = new WebDriverWait(Driver.webDriver, 30);

    // User Profile Icon
    private By userIconelement = By.xpath("//span[@class='icon aa-icon aa-icon-toolbar-user']");
    // Learning Instance Tab
    private By lielement = By.xpath("//a[@class='navigation-primaryoption-button']//span[contains(.,'Learning Instances')]");
    // Table Rows
    By tableRows = By.xpath("//tbody[@class='datatable-table-rows']");
    // Bots Tab
    private By botsTabBy = By.xpath("//a[@class='navigation-primaryoption-button' and @href='/bots']");

    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);

    // Generate dynamic xpath for instance name & perform click action on instance
    public void clickOnInstanceName(String instanceName) {
        String xpathPart1 = "//a[contains(text(),'";
        String fullXpath = xpathPart1 + instanceName + "')]";
        //By instanceLink = By.xpath(fullXpath);
        //System.out.println(instanceLink);
        WebElement element = Driver.webDriver.findElement(By.xpath(fullXpath));
        if (element.isDisplayed()) {
            element.click();
        }
    }

    // Get User Profile Icon using WebElement
    public WebElement getUserIcon() {
        return userIcon;
    }

    // Get User Profile Icon using By
    public By getuUserIconelement() {
        return userIconelement;
    }

    // Get Learning Instance Tab using By
    public By getLielement() {
        return lielement;
    }

    // Get Logout Button
    public WebElement getLogoutButton() {
        return logOut;
    }

    // Get Logged In User Name
    public String getLoggedinusername() {
        return loggedInuser.getText();
    }

    // Get My Totals Label on Dashboard
    public String getMytotal() {
        return myTotals.getText();
    }

    // Get My Learning Instance Page Title
    public String getMyLearningInstancePageTitle() {
        return myLearningInstanceLable.getText();
    }

    // Get Bots Page Title
    public String getBotsPageTitle() {
        return iqBotsLable.getText();
    }

    // Get Expected Tabs list
    public List<String> getExpectedTabs() {
        return expectedTabs;
    }

    // Select Tab based on argument
    public void clickonTabs(String tab) throws Exception {
        WebDriverWait wait = new WebDriverWait(Driver.webDriver, 15);
        switch (tab) {
            case "Dashboards":
                dashboardTab.click();
                wait.until(ExpectedConditions.textToBePresentInElement(myTotals, "My Totals"));
                System.out.println("inside dashboard wait");
                break;
            case "Learning Instance":
                learningInstanceTab.click();
                wait.until(ExpectedConditions.textToBePresentInElement(myLearningInstanceLable, "My Learning Instances"));
                break;
            case "Bots":
                baseClass.waitForElementClick(botsTabBy);
                botsTab.click();
                wait.until(ExpectedConditions.textToBePresentInElement(iqBotsLable, "IQ Bots"));
                break;
        }
    }

    // Performed click action on User Profile Icon
    public void clickProfileIcon() {
        userIcon.click();
    }

    // Performed click action on Logout button
    public void clickOnlogout() {
        logOut.click();
    }

    // Perform search using Instance Name
    public String searchForInstanceName(String uniqueInstanceName) {
        ExpectedConditions.visibilityOf(searchForInstanceName);
        ExpectedConditions.visibilityOf(searchDDItem);
        searchForInstanceName.sendKeys(uniqueInstanceName);
        searchForInstanceName.sendKeys(Keys.ENTER);
        //searchDDItem.click();
        return displaySearchInstanceName.getText();

    }

    // Click on Search
    public void clickOnSearchedInstanceName() {
        listOfInstanceName.get(0).click();
    }



    // SearchDD in LI pAge
    public WebElement getsearchDDValue() {
        return searchDDLIPage;
    }

    // Search by instance name and select instance
    public void selectInstanceName(String instanceName) {
        ExpectedConditions.visibilityOf(searchDDLIPage);
        ExpectedConditions.elementToBeClickable(searchDDLIPage);
        Select objSel = new Select(searchDDLIPage);
        //objSel.selectByValue("name");
        objSel.selectByVisibleText(instanceName);
    }

    //search Instance
    public void searchBots(String instanceName) throws Exception {
        //By Pritisundar
        searchTextBox.sendKeys(instanceName);
        //Driver.webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        searchTextBox.sendKeys(Keys.ENTER);
    }

    //click on Listing Table - Bot column
    public void clickOnNumberOfBotColumn() {
        baseClass.explicitWait("(//a[contains(text(),'AutoInstance')])[1]");
        numberOfBotColumn.click();
        System.out.print("Clicked");
    }

    //all data paths in table
    private String allBotsTableFullPath(int rowNum, int colNum) {
        //By Pritisundar
        String pathPart1 = "//tr[";
        String pathPart2 = "]/td[";
        String pathPartLast = "]";
        return pathPart1 + rowNum + pathPart2 + colNum + pathPartLast;
    }

    // Generate xpath for fetching details of expected group
    private String botsTrainingPath(int rowNum, int colNum) {
        //By Pritisundar
        String pathPart1 = "//tr[";
        String pathPart2 = "]/td[";
        String pathPartLast = "]/div";
        return pathPart1 + rowNum + pathPart2 + colNum + pathPartLast;
    }

    // Generate xpath for fetching details of expected instance
    public String tableBelowAllPath(int rowNum) {
        //By Pritisundar
        String path1 = "(//div[@class='aa-project-general-info--value'])[";
        String path2 = "]";
        return path1 + rowNum + path2;
    }

    // Assert instance details - instance name, # of bots, # of files, accuracy, training %, environment & language
    public void validateInstanceDataInLearningInstanceListing(String expectedResultsSheetName) throws Exception {
        //By Pritisundar
        String instanceName = Driver.webDriver.findElement(By.xpath("//tr[1]/td[1]/a")).getText();
        System.out.println(instanceName);
        System.out.println(Driver.instName);
        Assert.assertEquals(instanceName, Driver.instName);
        for (int i = 1; i < 6; i++) {
            if (i == 2) {
                String noOfBots = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(1, i))).getText();
                System.out.println(noOfBots);
                String s = testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 1, 1);
                System.out.println(s);
                assertThat(noOfBots).isEqualTo(s);

            } else if (i == 3) {
                String noOfFiles = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(1, i))).getText();
                System.out.println(noOfFiles);
                Thread.sleep(2000);
                if (clickElement.isDisplayed()) {
                    clickElement.click();
                }
                Thread.sleep(2000);
                assertThat(noOfFiles).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 2, 1));
            } else if (i == 4) {
                String accuracy = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(1, i))).getText();
                System.out.println(accuracy);
                assertThat(accuracy).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 3, 1));
            } else if (i == 5) {
                String valueOfTraining = Driver.webDriver.findElement(By.xpath(botsTrainingPath(1, i))).getText();
                System.out.println(valueOfTraining);
                assertThat(valueOfTraining).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 4, 1));
            } else {
                System.out.println("No require to validate After bot run");
            }
        }
        String trainingProgress = trainingProgressBar.getText();
        System.out.println(trainingProgress);
        assertThat(trainingProgress).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 4, 1));

        String noOfVision = noOfVisionBots.getText();
        System.out.println(noOfVision);
        assertThat(noOfVision).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 1, 1));

        String accuracyBots = accuracy.getText();
        System.out.println(accuracyBots);
        assertThat(accuracyBots).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 3, 1));

        String fileNumbers = noOfFiles.getText();
        System.out.println(fileNumbers);
        assertThat(fileNumbers).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 2, 1));

        String language = primaryLanguage.getText();
        System.out.println(language);
        assertThat(language).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 5, 1));

        String envs = environment.getText();
        System.out.println(envs);
        assertThat(envs).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 6, 1));

        System.out.println("After bots run validation completed");
    }

    // Get Instance Training percentage
    public void getTraiingStausBarValue() {
        System.out.println(aboveTrainingProgressbar.getText());
    }

    // Wait for notification
    public void waitForMessageToBeDisplayed() {
        baseClass.explicitWait(".//*[@id='app-notifications']/li/div/div");
    }

    // Get Instance successfully deleted message
    public String getMessageInstanceSuccessfullyDeleted() {
        return instancesuccessfullyDeletedMessage.getText();
    }

    // Click on Instance Name
    public void clickOnInstanceNameLink(int rowNum) {
        String pathPart1 = "(//a[@class='aa-link underline'])[";
        String pathPart2 = "]";
        String fullPath = pathPart1 + rowNum + pathPart2;
        Driver.webDriver.findElement(By.xpath(fullPath)).click();
    }

    // Perform search for deleted instance
    public void searchForDeteletedInstanceName(String learningInstancePageInstanceName) {
        ExpectedConditions.visibilityOf(searchForInstanceName);
        searchForInstanceName.sendKeys(learningInstancePageInstanceName);
        searchForInstanceName.click();
    }

    // Perform enter using Robot class
    public void hitEnterKey() throws AWTException {
        Robot robot = new Robot();
        robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    // Get search result message for instance
    public String getMessagedDisplayedForSearchedInstance() {
        return projectsNotFoundMessage.getText();
    }

    // Get search drop down values
    public List<String> verifySearchDropDownValue() {
        ExpectedConditions.visibilityOf(searchDDLIPage);
        List<String> uiDomainDDList = new ArrayList<String>();
        Select selectDomainType = new Select(searchDDLIPage);
        for (int i = 1; i < selectDomainType.getOptions().size(); i++)
            uiDomainDDList.add(selectDomainType.getOptions().get(i).getText());
        return uiDomainDDList; //return of Domain DropDown List
    }

    public int getTotalNumberOfInstanceCreated(){
        List<String> learningInstancePageTotalInstance = new ArrayList<String>();
        for(WebElement totalInstance : countOfRows){
            learningInstancePageTotalInstance.add(totalInstance.getText());
        }
        return learningInstancePageTotalInstance.size();
    }


    public String getNoInstanceMessage() {
       return noCurrentInstanceMessage.getText();
    }

    public List<String> getListOfInstanceName() {
        List<String> learningInstancePageAllInstanceName = new ArrayList<String>();
        for(WebElement instanceName : listOfInstanceName){
            learningInstancePageAllInstanceName.add(instanceName.getText());
        }
        return learningInstancePageAllInstanceName;
    }
}
