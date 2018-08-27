package pageobjects;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.testBase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class LearningInstanceDetailPage extends testBase {

    // Edit Instance Button
    @FindBy(how = How.XPATH, using = "//a[@class='aa-link aa-grid-row vertical-center']")
    private WebElement editInstanceButton;
    // Instance Name & Environment Label
    @FindBy(how = How.XPATH, using = "//div[@class='aa-grid-row vertical-center']")
    private WebElement instanceNameAndEnvironment;
    // Environment Toggle Button
    @FindBy(how = How.XPATH, using = "//div[@class='toggleinput-control']")
    private WebElement toggleButton;
    // Temp Element
    @FindBy(how = How.XPATH, using = "(//tbody[@class='datatable-table-rows'])[1]//tr[1]/td[1]")
    private WebElement firstinstance;
    // Save Button
    @FindBy(how = How.XPATH, using = "//a[@class='aa-link aa-grid-row vertical-center']//span[text()='Save']")
    private WebElement saveButton;
    // DataTable
    @FindBy(how = How.XPATH, using = "//tbody[@class='datatable-table-rows']/tr")
    private
    List<WebElement> rowList;
    // ProjectDetails Page WebElements
    @FindBy(how = How.XPATH, using = "//BUTTON[@class='aa-overlay-btn'][text()='Next']")
    private WebElement nextButton;
    @FindBy(how = How.XPATH, using = "(//BUTTON[@class='aa-overlay-btn'][text()='Got it!'][text()='Got it!'])[1]")
    private WebElement gotItButton;
    @FindBy(how = How.XPATH, using = "//A[text()='View details']")
    private WebElement viewDetailsHeading;
    // Spinner
    @FindBy(how = How.XPATH, using = "//DIV[@class=\"aa-project-details-container-header--projectclassifying\"]//span[@class=\"tooltip\"]")
    private WebElement spinner;
    // Total Group Count
    @FindBy(how = How.XPATH, using = "//div[@class='aa-project-details-folders-count']/span[3]")
    private WebElement totalClassifiedGroupCount;
    //above training progress bar
    @FindBy(how = How.XPATH, using = "//div[@class='aa-progress-bar-current']")
    private WebElement aboveTrainingProgressbar;

    // Staging Label
    private By stagingLabel = By.xpath("//div[@class='aa-project-environment-badge']");

    //----- added by Vandana - webElements for Delete Instance----------------------------------
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Delete Instance')]")
    private WebElement deleteInstanceButtonWebElement;
    By deleteInstanceButton = By.xpath("//button[contains(.,'Delete Instance')");
    @FindBy(how = How.XPATH, using = "//input[@name='delete-confirm-name']")
    private WebElement deleteConfirmNameTextbox;
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Cancel')]")
    private WebElement deleteInstanceCancelButton;
    @FindBy(how = How.XPATH, using = "//button[contains(.,'I understand, please delete')]")
    private WebElement deleteInstanceConfirmdeleteButton;
    @FindBy(how = How.XPATH, using = "//div[@class='modal-content']")
    private WebElement contentBoxAfterClickingDeleteInstanceButton;
    @FindBy(how = How.XPATH, using = "//*[@class = 'pagetitle-label']")
    private WebElement instanceName;
    @FindBy(how = How.XPATH, using = "//*[@class = 'aa-page-description']")
    private WebElement instanceDescription;

    //-------------------End of webElements for Delete Instance----

    // Check for instance Status, if instance is in production move it to staging & click on edit button
    public void editInstance() {
        String environment = checkInstanceEnvironment();
        System.out.print(environment);
        if (environment.contains("production")) {
            elementToBeClickable(toggleButton);
            toggleButton.click();
            editInstanceButton.click();
        } else
            editInstanceButton.click();
    }

    // Move Instance to Production and Perform click action
    public void clickOnToggleToMoveInstanceToProduction() {
        String environment = checkInstanceEnvironment();
        System.out.print(environment);
        if (environment.contains("staging")) toggleButton.click();
    }

    // Check Instance Environment
    private String checkInstanceEnvironment() {
        String environment = instanceNameAndEnvironment.getText();
        if (environment.contains("staging")) return "staging";
        else return "production";
    }

    // Perform click action on Save button for edit instance
    public void clickOnSave() {
        saveButton.click();
    }

    // Get total classified groups
    public int totalRows() {
        return rowList.size();
    }

    // Generate dynamic path for Group Name
    private String getAllDataPath(int rowNumber, int colNumber) throws Exception {
        String pathPart1 = "//tbody[@class='datatable-table-rows']/tr[";
        String pathPart2 = "]/td[";
        String pathLastPart = "]";
        return pathPart1 + rowNumber + pathPart2 + colNumber + pathLastPart;
    }

    // Get value of Group
    public String columnData(int rowNum, int colNum) throws Exception {
        String path = getAllDataPath(rowNum, colNum);
        return Driver.webDriver.findElement(By.xpath(path)).getText();
    }

    // Get total files for group
    public String getClassifiedGroupFilesCount(String expectedGroupName) throws Exception {
        int totalRows = this.totalRows();
        String filesCount = null;
        for (int i = 1; i <= totalRows; i++) {
            String actualGroupName = columnData(i, 1);
            if (actualGroupName.contains(expectedGroupName)) {
                filesCount = columnData(i, 2);
                break;
            }
        }
        return filesCount;
    }

    // Launch Designer for selected group
    public void launchDesignerForSelectedGroup(String expectedGroupName) throws Exception {
        int totalRows = this.totalRows();
        for (int i = 1; i <= totalRows; i++) {
            String groupName = columnData(i, 1);
            if (groupName.equalsIgnoreCase(expectedGroupName)) {
                String path = getAllDataPath(i, 9);
                Driver.webDriver.findElement(By.xpath(path)).click();
                break;
            }
        }
    }

    // Get Next Button Value
    public String getViewDetailsPageWithNextButton() {
        elementToBeClickable(nextButton);
        visibilityOf(nextButton);
        return nextButton.getText();
    }

    // Perform Click action on Next Button
    public void clickOnNextButton() {
        elementToBeClickable(nextButton);
        visibilityOf(nextButton);
        nextButton.click();
    }

    // Get Got It Button Value
    public String getViewDetailsPageWithGotItButton() {
        elementToBeClickable(gotItButton);
        visibilityOf(gotItButton);
        return gotItButton.getText();
    }

    // Perform Click action on Got It Button
    public void clickOnGotItButton() {
        elementToBeClickable(gotItButton);
        visibilityOf(gotItButton);
        gotItButton.click();
    }

    // Get Learning Instance Details Title

    public String getviewDetailsPageHeading() {
        visibilityOf(viewDetailsHeading);
        return viewDetailsHeading.getText();
    }

    // Get Staging label for Instance
    public By getStagingLabel() {
        return stagingLabel;
    }

    // Perform click action on CreateBot / Edit bot Link
    public void clickOnBotLink(int i) {
        String pathPart1 = "(//a[contains(text(),'Bot')])[";
        String pathLastPart = "]";
        String fullPath = pathPart1 + i + pathLastPart;
        Driver.webDriver.findElement(By.xpath(fullPath)).click();
    }

    //--- LI Page Method
    public void moveInstanceFromStagingToProduction(int rowNum) {
        String pathPart1 = "(//DIV[@class='toggleinput-control-indicator'])[";
        String pathPart2 = "]";
        String fullPath = pathPart1 + rowNum + pathPart2;
        Driver.webDriver.findElement(By.xpath(fullPath)).click();
    }

    // Check spinner availability
    public Boolean checkSpinner() {
        return spinner.isDisplayed();
    }

    //all data paths in table
    private String allBotsTableFullPath(int rowNum, int colNum) {
        //By Pritisundar
        String pathPart1 = "//tr[";
        String pathPart2 = "]/td[";
        String pathPartLast = "]";
        return pathPart1 + rowNum + pathPart2 + colNum + pathPartLast;
    }

    // Generate dynamic path for Group Name
    public String botsTrainingPath(int rowNum, int colNum) {
        //By Pritisundar
        String pathPart1 = "//tr[";
        String pathPart2 = "]/td[";
        String pathPartLast = "]/div";
        return pathPart1 + rowNum + pathPart2 + colNum + pathPartLast;
    }

    // Get Row values for selected group
    private String tableBelowAllPath(int rowNum) {
        //By Pritisundar
        String path1 = "(//div[@class='aa-project-general-info--value'])[";
        String path2 = "]";
        return path1 + rowNum + path2;
    }

    // Get total classified groups count for instance
    public String getTotalClassifiedGroupCount() {
        return totalClassifiedGroupCount.getText();
    }

    /**
     * @param groupName : Group Name to get details
     * @return : Row Number for expected group
     */
    private int getRowNumberForSelectedGroup(String groupName) {
        int rowNumber = 0;
        for (int i = 1; i <= 4; i++) {
            String groupNameNow = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(i, 1))).getText();
            if (groupNameNow.equalsIgnoreCase(groupName)) {
                System.out.println("Row Number" + groupName + i);
                rowNumber = i;
                break;
            }
        }
        return rowNumber;
    }

    /**
     * @param groupName                : Group to validate
     * @param environment              : Instance Environment [Staging / Production]
     * @param expectedResultsSheetName : Data validation sheet name
     * @throws Exception : exception
     */
    //group wise all data validation from table - Staging & Production
    public void getAllValueInTable(String groupName, String environment, String expectedResultsSheetName) throws Exception {
        System.out.println("getAllValueInTable validation started");
        int rowNumber = getRowNumberForSelectedGroup(groupName);
        String groupNameNow = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(rowNumber, 1))).getText();

        switch (environment) {
            case "staging":
                System.out.println("inside staging");

                String noOfTrainingFiles = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(rowNumber, 2))).getText();
                System.out.println(noOfTrainingFiles);
                assertThat(noOfTrainingFiles).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 2, 1));

                String noOfTrainingSuccess = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(rowNumber, 3))).getText();
                System.out.println(noOfTrainingSuccess);
                assertThat(noOfTrainingSuccess).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 3, 1));

                String noOfTrainingUnProcess = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(rowNumber, 4))).getText();
                System.out.println(noOfTrainingUnProcess);
                assertThat(noOfTrainingUnProcess).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 4, 1));
                break;

            case "production":
                String noOfProductionFiles = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(rowNumber, 5))).getText();
                System.out.println(noOfProductionFiles);
                assertThat(noOfProductionFiles).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 1, 1));

                String noOfProductionSuccess = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(rowNumber, 6))).getText();
                System.out.println(noOfProductionSuccess);
                assertThat(noOfProductionSuccess).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 2, 1));

                String noOfProductionFilesUnProcessed = Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(rowNumber, 7))).getText();
                System.out.println(noOfProductionFilesUnProcessed);
                assertThat(noOfProductionFilesUnProcessed).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 3, 1));
                break;
        }
    }

    // Assert summary values for instance
    public void validateTrainingSummary(String expectedResultsSheetName) throws Exception {
        getTraiingStausBarValue();
        System.out.println("Training Summary Verification started");

        String noOfVisionBots = Driver.webDriver.findElement(By.xpath(tableBelowAllPath(1))).getText();
        assertThat(noOfVisionBots).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 9, 1));

        String noOfFiles = Driver.webDriver.findElement(By.xpath(tableBelowAllPath(3))).getText();
        System.out.println(noOfFiles);
        assertThat(noOfFiles).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 10, 1));

        String accuracy = Driver.webDriver.findElement(By.xpath(tableBelowAllPath(2))).getText();
        System.out.println(accuracy);
        assertThat(accuracy).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 11, 1));

        String actualEnvironment = Driver.webDriver.findElement(By.xpath(tableBelowAllPath(4))).getText();
        System.out.println(actualEnvironment);
        assertThat(actualEnvironment).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 12, 1));

        String typeOfDomain = Driver.webDriver.findElement(By.xpath(tableBelowAllPath(5))).getText();
        System.out.println(typeOfDomain);
        assertThat(typeOfDomain).isEqualTo(testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 13, 1));

        String dateModified = Driver.webDriver.findElement(By.xpath(tableBelowAllPath(6))).getText();
        System.out.println(dateModified);
        System.out.println("validateAllDataAfterMoveProduction completed");
    }

    private void getTraiingStausBarValue() {
        System.out.println(aboveTrainingProgressbar.getText());
    }

    // Get edit button label
    public String getEditbuttonText() {
        return editInstanceButton.getText();
    }

    // Get delete button label
    public String getDeleteButtonText() {
        return deleteInstanceButtonWebElement.getText();
    }

    // Perform click action on Delete button
    public void clickOnDeleteInstanceButton() {
        visibilityOf(deleteInstanceButtonWebElement);
        elementToBeClickable(deleteInstanceButtonWebElement);
        deleteInstanceButtonWebElement.click();
    }

    // Get instance details after delete operation
    public boolean checkForContentModalExist() {
        return contentBoxAfterClickingDeleteInstanceButton.isDisplayed();
    }

    // Get delete confirmation message status
    public boolean checkFordeleteConfirmNameTextboxExist() {
        return deleteConfirmNameTextbox.isDisplayed();
    }

    // Get cancel button status
    public boolean checkForCancelButtonExistAndEnabled() {
        return deleteInstanceCancelButton.isDisplayed() && deleteInstanceCancelButton.isEnabled();
    }

    // Get Confirm Delete Button status
    public boolean checkForConfirmDeleteButtonExist() {
        return deleteInstanceConfirmdeleteButton.isDisplayed();
    }

    // Set Learning Instance Name in text box
    public void enterTheInstanceName(String learningInstancePageInstanceName) {
        deleteConfirmNameTextbox.sendKeys(learningInstancePageInstanceName);
        System.out.println(learningInstancePageInstanceName);
    }

    // Get Delete confirmation button status
    public boolean checkForConfirmDeleteButtonIsEnabled() {
        return deleteInstanceConfirmdeleteButton.isEnabled();
    }

    public void clickOnConfirmDeleteButton() {
        visibilityOf(deleteInstanceConfirmdeleteButton);
        elementToBeClickable(deleteInstanceConfirmdeleteButton);
        deleteInstanceConfirmdeleteButton.click();
    }

    // Perform click action on edit button
    public void clickOnEditButton() {
        editInstanceButton.click();
    }

    // Perform click action on Cancel button
    public void clickOnCancelButtonToCancelDeleting() {
        visibilityOf(deleteInstanceCancelButton);
        elementToBeClickable(deleteInstanceCancelButton);
        deleteInstanceCancelButton.click();
    }

    // Get instance name
    public WebElement getInstanceName() {
        return instanceName;
    }

    // Get instance Description
    public WebElement getInstanceDescription() {
        return instanceDescription;
    }

    //..............................................................................................
    //For Sanity
    //..............................................................................................

    //get all priority column values in Learning instance page
    public List<Integer> getPriorityColumnValues(int columnNum)throws  Exception{
        //By Pritisundar
        List<Integer> rowList=new ArrayList<>();
        List<WebElement> rowElements=Driver.webDriver.findElements(By.xpath("//tbody/tr"));
        for(int i=1;i<=rowElements.size();i++){
            String rowData=Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(i,columnNum))).getText().trim();
            int rowDataIntValues=Integer.parseInt(rowData);
            rowList.add(rowDataIntValues);
        }
        return rowList;
    }

    //get all create bot link available or not
    public String getAllCreateBotLink(String nameGroup)throws  Exception{
        //By Pritisundar
        List<WebElement> rowElements=Driver.webDriver.findElements(By.xpath("//tbody/tr"));
        for(int i=1;i<=rowElements.size();i++){
            String linkData=Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(i,9))).getText().trim();
            String groupName=Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(i,1))).getText().trim();
            if((nameGroup.equalsIgnoreCase("Group_1")) && (groupName.equalsIgnoreCase(nameGroup))){
                System.out.println("Create Bot link is available for group :"+groupName);
                return linkData;
            }
            else if(nameGroup.equalsIgnoreCase("Group_3") && groupName.equalsIgnoreCase(nameGroup)){
                System.out.println("Bot is already created for this group :"+groupName);
                return linkData;
            }
            else if(nameGroup.equalsIgnoreCase("Group_2") && groupName.equalsIgnoreCase(nameGroup)){
                System.out.println("Bot is already created for this group :"+groupName);
                return linkData;
            }
//            else{
//                System.out.println("Create Bot or Edit bot linked is not available for this group :"+groupName);
//                return linkData;
//            }

        }
        return "did not get anything";
    }

    //Number of group created
    public void groupCount()throws Exception{
        //By Pritisundar
        List<WebElement> rowElements=Driver.webDriver.findElements(By.xpath("//tbody/tr"));
        int i=rowElements.size();
        System.out.println(rowElements.size());
        assertThat(i).isEqualTo(System.getenv("Number_of_groups"));
    }

    /**
     *
     * @param columnNumber : get the all details of a column.
     * @return
     */
    //number of file count group wise
    public String[] noOfFileEachGroup(int columnNumber){
        //By Pritisundar
        List<WebElement> rowElements=Driver.webDriver.findElements(By.xpath("((//tbody)[1])//tr"));
        String[] fileCountGroupWise=new String[rowElements.size()];
        System.out.println(rowElements.size());
        for(int i=0;i<rowElements.size();i++){
            int rowNum=1+i;
            //String groupName=Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(rowNum,1))).getText().trim();
            String numberFileText=Driver.webDriver.findElement(By.xpath(allBotsTableFullPath(rowNum,columnNumber))).getText().trim();
            if(numberFileText.equalsIgnoreCase("")||numberFileText.equals(null)){
                fileCountGroupWise[i]=" ";
            }
            else {
                //int numbers = Integer.parseInt(numberFileText);
                fileCountGroupWise[i] = numberFileText;
            }
            //System.out.println("The group name is :"+groupName+" :: "+"The number of files are in this group : "+numbers);
        }
        for(int i=0;i<fileCountGroupWise.length;i++)
        {
            System.out.print(fileCountGroupWise[i]+" ");
        }
        return fileCountGroupWise;
    }

    //sorting method for any integer array in ascending order...............
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    public static String[] bubblesortASC(String[] theArray) {
        int size = theArray.length;
        for (int last = size - 1; last > 0; last--) {
            int largest = 0;
            for (int scan = 1; scan <= last; scan++){

                if(isNumeric(theArray[scan])&& isNumeric(theArray[largest])){
                    if (Double.parseDouble(theArray[scan])>Double.parseDouble(theArray[largest]))
                        largest = scan;
                } else if((theArray[scan].contains("%") && isNumeric(theArray[scan].split("%")[0])) || theArray[largest].contains("%") && isNumeric(theArray[largest].split("%")[0])){
                    if (Double.parseDouble(theArray[scan].split("%")[0])>Double.parseDouble(theArray[largest].split("%")[0]))
                        largest = scan;
                } else{
                    if(theArray[scan].compareTo(theArray[largest])>0){
                        largest = scan;
                    }
                }

            }
            /** Swap the values */
            String temp = theArray[largest];
            theArray[largest] = theArray[last];
            theArray[last] = temp;
        }

        System.out.println();
        System.out.println("--------------Array After Bubble Sort ascending order----------");
        for(int i=0; i < theArray.length; i++){
            System.out.print(theArray[i] + " ");
        }

        return theArray;
    }

    public static String[] bubblesortDSC(String[] theArray) {
        int size = theArray.length;
        for (int last = size - 1; last > 0; last--) {
            int largest = 0;
            for (int scan = 1; scan <= last; scan++){

                if(isNumeric(theArray[scan])&& isNumeric(theArray[largest])){
                    if (Double.parseDouble(theArray[scan])<Double.parseDouble(theArray[largest]))
                        largest = scan;
                } else if((theArray[scan].contains("%") && isNumeric(theArray[scan].split("%")[0])) || theArray[largest].contains("%") && isNumeric(theArray[largest].split("%")[0])){
                    if (Double.parseDouble(theArray[scan].split("%")[0])<Double.parseDouble(theArray[largest].split("%")[0]))
                        largest = scan;
                } else{
                    if(theArray[scan].compareTo(theArray[largest])<0){
                        largest = scan;
                    }
                }

            }
            /** Swap the values */
            String temp = theArray[largest];
            theArray[largest] = theArray[last];
            theArray[last] = temp;
        }

        System.out.println();
        System.out.println("--------------Array After Bubble Sort descending order----------");
        for(int i=0; i < theArray.length; i++){
            System.out.print(theArray[i] + " ");
        }

        return theArray;
    }

    /**
     *
     * @param index : like priority,IQBot,Name,# no of training files etc.
     * @throws Exception
     */
    //click on column header like "priority"
    public void clickOnColumnHeader(int index)throws Exception {
        //By Pritisundar
        String path1="(//SPAN[@class='datatable-column-content'])[";
        String path2="]";
        String tabPath=path1+index+path2;
        explicitWait(tabPath);
        Driver.webDriver.findElement(By.xpath(tabPath)).click();
        Thread.sleep(2000);
        System.out.println("click performed"+index+"========="+tabPath);
    }


    public int columnIndexForHeader(String headerName)throws Exception {
        //By Pritisundar
        int a=0;
        for (int b = 1; b <= 9; b++) {
            String path1="(//SPAN[@class='datatable-column-content'])[";
            String path2="]";
            String tabPath=path1+b+path2;
            String runTimeHeaderName =Driver.webDriver.findElement(By.xpath(tabPath)).getText().trim();
            System.out.println("+++++++++++++"+runTimeHeaderName+"++++++++++++");
            if(runTimeHeaderName.equalsIgnoreCase(headerName)){
                System.out.println(headerName);
                System.out.println(Driver.webDriver.findElement(By.xpath(tabPath)).isDisplayed());
                //Driver.webDriver.findElement(By.xpath(tabPath)).click();
                a = b;
                break;
            }

        }
        return a;
    }

}
