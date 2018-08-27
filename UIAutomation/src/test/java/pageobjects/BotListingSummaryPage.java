package pageobjects;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import javax.swing.*;
import java.awt.event.KeyEvent;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class BotListingSummaryPage {

    //By Pritisundar
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Search...']")
    private WebElement searchBots;
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

    // Search Bot by Instance Name
    public void searchBots(String botsByInstanceName) {
        //By Pritisundar
        searchBots.sendKeys(botsByInstanceName);
        searchBots.sendKeys(Keys.ENTER);
    }

    // Perform click action on selected group for instance
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

    //  Run documents for Group
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

    // Generate dynamic xpath for group selection
    public String allBotsTableFullPath(int rowNum, int colNum) {
        //By Pritisundar
        String pathPart1 = "//tr[";
        String pathPart2 = "]/td[";
        String pathPartLast = "]/a";
        return pathPart1 + rowNum + pathPart2 + colNum + pathPartLast;
    }

    // Get value of group
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

    // Perform click action on Run once button for selected group
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

    // Launch Visionbot designer for selected group
    public void clickOnEditButton(int rowNum) {
        //By Pritisundar
        String pathPart1 = "(//span[@class='icon fa fa-pencil'])[";
        String pathPart2 = "]";
        String fullPath = pathPart1 + rowNum + pathPart2;
        Driver.webDriver.findElement(By.xpath(fullPath)).click();
    }

    // Get bots summary details before bot run
    public void getAllDetailsBeforeRun() {
        //By Pritisundar
        System.out.println(noOfFileProcessed.getText());
        String noofFilePrcs = noOfFileProcessed.getText();
        Assert.assertEquals(noofFilePrcs, "0");

        System.out.println(totalNoOfFileProcessed.getText());
        String totalNoofFile = totalNoOfFileProcessed.getText();
        ;
        Assert.assertEquals(totalNoofFile, "1");

        System.out.println(fileSuccessfullyProcessed.getText());
        String fileUnderSuccess = fileSuccessfullyProcessed.getText();
        Assert.assertEquals(fileUnderSuccess, "0");

        System.out.println(sentFiles.getText());
        String noOfFilesent = sentFiles.getText();
        Assert.assertEquals(noOfFilesent, "0");

        System.out.println(documentAccuracy.getText());
        String accuracyDocument = documentAccuracy.getText();
        Assert.assertEquals(accuracyDocument, "Document Accuracy\n" +
                "Average: 0%");

        System.out.println(fieldAccuracy.getText());
        String fAccuracy = fieldAccuracy.getText();
        Assert.assertEquals(fAccuracy, "Field Accuracy\n" +
                "Average: 0%");

    }

    // Get Bots summary details after bot run
    public void getAllDetailsAfterRun() throws Exception {
        //By Pritisundar
        searchBots("12345678AutoInstance");
        clickOnGroupName("Group_2");
        searchBots(System.getenv("instance_Name"));
        System.out.println(noOfFileProcessed.getText());

        String noofFilePrcs = noOfFileProcessed.getText();
        Assert.assertEquals(noofFilePrcs, "1");

        System.out.println(totalNoOfFileProcessed.getText());
        String totalNoofFile = totalNoOfFileProcessed.getText();
        ;
        Assert.assertEquals(totalNoofFile, "1");

        System.out.println(fileSuccessfullyProcessed.getText());
        String fileUnderSuccess = fileSuccessfullyProcessed.getText();
        Assert.assertEquals(fileUnderSuccess, "0");

        System.out.println(sentFiles.getText());
        String noOfFilesent = sentFiles.getText();
        Assert.assertEquals(noOfFilesent, "1");

        System.out.println(documentAccuracy.getText());
        String accuracyDocument = documentAccuracy.getText();
        Assert.assertEquals(accuracyDocument, "Document Accuracy\n" + "Average: 0%");

        System.out.println(fieldAccuracy.getText());
        String fAccuracy = fieldAccuracy.getText();
        Assert.assertEquals(fAccuracy, "Field Accuracy\n" + "Average: 0%");

    }


    //.................Sanity..................
    @FindBy(how = How.XPATH, using = "(//div[@class='aa-flex-grid-item'])[9]")
    private WebElement fileValidate;
    @FindBy(how = How.XPATH, using = "(//div[@class='aa-flex-grid-item'])[10]")
    private WebElement fileAsInvalid;
    @FindBy(how = How.XPATH, using = "(//div[@class='aa-flex-grid-item'])[11]")
    private WebElement avgTimeSpend;

    public String fileValidateForBotAfterInstanceMoveProduction(){
        System.out.println(fileValidate.getText());
        return fileValidate.getText().trim();
        // Assert.assertEquals(validateFiles, "Files validated 1 / 2 (50%)");
    }
    public String invalidFileValidateForBotAfterInstanceMoveProduction(){
        System.out.println(fileAsInvalid.getText());
        return fileAsInvalid.getText().trim();
        // Assert.assertEquals(validateInvalidFiles, "Marked as invalid 1 / 2 (50%)");
    }

    public String timeSpendToValidate(){
        System.out.println(avgTimeSpend.getText());
        return avgTimeSpend.getText().trim();
        // Assert.assertEquals(avgSpendTime, "Average time spent to validate 20s");
    }

}
