package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class AnalyzingDocumentPage {

    // Analyzing Page Web Elements
    @FindBy(how = How.XPATH, using = "//div[@class='aa-flex-grid aa-analyzing-project-docs-grid']//div[@class='pagetitle-label']")
    private WebElement analyzingPageHeader;
    @FindBy(how = How.XPATH, using = "//span[@class='aa-analyzing-project-docs-info-section--title']")
    private WebElement learningInstanceTitleDetails;
    @FindBy(how = How.XPATH, using = "//*[@class='aa-analyzing-project-docs-info-section--value']")
    private List<WebElement> learningInstanceValueDetails;
    @FindBy(how = How.XPATH, using = "//div[contains(@class,'aa-analyzing-project-progress-bar')]")
    private WebElement progressBar;
    @FindBy(how = How.XPATH, using = "//*[@id=\"aa-main-container\"]/div/div[2]/div[2]/div/div/div")
    private WebElement analyzingProgressBar;
    @FindBy(how = How.XPATH, using = "//*[@id=\"aa-main-container\"]/div/div[2]/div[2]/div/div/div/div")
    private WebElement analyzingProgressBarFill;
    @FindBy(how = How.XPATH, using = "//*[@id=\"aa-main-container\"]/div/div[2]/div[2]/div/div/span[2]")
    private WebElement analyzingStartFile;
    @FindBy(how = How.XPATH, using = "//div[@class='aa-analyzing-project-progress-bar']/following-sibling::span[2]")
    private WebElement analyzingTotalFile;
    @FindBy(how = How.XPATH, using = "//*[@id=\"aa-main-container\"]/div/div[2]/div[2]/div/div/text()[1]")
    private WebElement estimatedTimeRemaining;
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Close and run in background')]")
    private WebElement closeAndRunInBackgroundButton;
    @FindBy(how = How.XPATH, using = "//IMG[@src='/images/ring-loader.gif']")
    private WebElement spinnerOFLIDetailPage;
    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Finish and close')]")
    private WebElement finishAndCloseButton;


    //Progress bar using By
    private By pbar = By.xpath("//span[@class='icon aa-icon aa-icon-toolbar-user']");

    //Details using By
    public By instanceInfo = By.xpath("aa-analyzing-project-docs-info");


    // Get Analyzing Page Title
    public String getAnalyzingPageTitle() {
        return analyzingPageHeader.getText();
    }

    // Get Learning Instance Fields Listing
    public List<String> learningInstanceValueDetails() {

        List<String> actualDetailList = new ArrayList<String>();
        for (WebElement learningInstanceValueDetail : learningInstanceValueDetails) {
            actualDetailList.add(learningInstanceValueDetail.getText());

        }
        return actualDetailList;
    }

    // Get Total Files in Analysing Documents Page
    public String totalFilesInAnalyzingDocuments() throws InterruptedException {
        Thread.sleep(3000);
        ExpectedConditions.visibilityOf(analyzingTotalFile);
        return analyzingTotalFile.getText();
    }

    // Get Estimated Time Remaining for Analysing documents
    public String getEstimatedTimeRemaining() {
        ExpectedConditions.visibilityOf(progressBar);
        ExpectedConditions.visibilityOf(analyzingProgressBar);
        ExpectedConditions.visibilityOf(analyzingProgressBarFill);
        return estimatedTimeRemaining.getText();
    }

    // Get ProgressBar element
    public WebElement getProgressbar() {
        return progressBar;
    }

    // Get CloseAndRunButton element
    public WebElement closeAndRunButton() {
        return closeAndRunInBackgroundButton;
    }

    // Perform click action on CloseAndRunInBackground Button
    public void clickOnCloseAndRunInBackgroungButton() {
        ExpectedConditions.visibilityOf(closeAndRunInBackgroundButton);
        ExpectedConditions.elementToBeClickable(closeAndRunInBackgroundButton);
        closeAndRunInBackgroundButton.click();
    }

    // Get progress bar
    public By progressbar() {
        return pbar;
    }

    public WebElement finishAndCloseButton() {
        ExpectedConditions.visibilityOf(finishAndCloseButton);
        ExpectedConditions.elementToBeClickable(finishAndCloseButton);
        return finishAndCloseButton ;
        //closeAndRunInBackgroundButton.click();
    }


}
