package utils;

import driver.Driver;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;

import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class testBase {
    private static Robot robot;
    private WebDriverWait wait = new WebDriverWait(Driver.webDriver, 30);

    // Fluent Wait implementation : wait for page load
    public void waitForPageLoad() {
        Wait<WebDriver> wait = new FluentWait<>(Driver.webDriver).withTimeout(1000, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            By pageLoader = By.xpath("//div[@class='aa-page-loading-overlay-loader']");
            if ((driver != null ? driver.findElements(pageLoader).size() : 0) != 0) {
                Boolean pageIsLoading = driver.findElement(pageLoader).isEnabled();
                System.out.println("Page is loading..");
                return false;
            } else {
                System.out.println("Size 0 - Loader not available");
                return true;
            }
        });
    }

    // Fluent Wait implementation : wait till progress bar exist
    public void waitForDocumentProcessing() {
        Wait<WebDriver> wait = new FluentWait<>(Driver.webDriver).withTimeout(2000, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            By progressBar = By.xpath("//div[@class='aa-analyzing-project-progress-bar-fill']");
            if ((driver != null ? driver.findElements(progressBar).size() : 0) != 0) {
                Boolean progressBarVisible = driver.findElement(progressBar).isEnabled();
                System.out.println("Progress bar true");
                return false;
            } else {
                System.out.println("Size 0 - Progress bar false");
                return true;
            }
        });
    }

    // Fluent Wait implementation : wait till progress bar appear in Analyze page
    public void waitForAnalyzePage() {
        Wait<WebDriver> wait = new FluentWait<>(Driver.webDriver).withTimeout(100, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            By progressBar = By.xpath("//div[@class='aa-analyzing-project-progress-bar-fill']");
            if ((driver != null ? driver.findElements(progressBar).size() : 0) != 0) {
                @SuppressWarnings("unused")
                Boolean i = driver.findElement(progressBar).isEnabled();
                return i = true;
            } else {
                System.out.println("Analyzing Page Available");
                return true;
            }
        });
    }

    // Fluent Wait implementation : wait till classifier / production document processing spinner appear in Learning Instance Details page
    public void waitForSpinnerToAppear() {
        Wait<WebDriver> wait = new FluentWait<>(Driver.webDriver).withTimeout(100, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            By spinner = By.xpath("//DIV[@class=\"aa-project-details-container-header--projectclassifying\"]//span[@class=\"tooltip\"]");
            Boolean spinnerIsVisible = driver.findElement(spinner).isEnabled();
            System.out.println("Spinner is available - wait for spinner to appear");
            return true;
        });
    }

    // Fluent Wait implementation : wait till classification / production document processing complete in Learning Instance Details page
    public void waitForSpinner() {
        Wait<WebDriver> wait = new FluentWait<>(Driver.webDriver).withTimeout(1000, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            By spinner = By.xpath("//DIV[@class=\"aa-project-details-container-header--projectclassifying\"]//span[@class=\"tooltip\"]");
            if ((driver != null ? driver.findElements(spinner).size() : 0) != 0) {
                Boolean spinnerIsVisible = driver.findElement(spinner).isEnabled();
                System.out.println("Spinner is available");
                return false;
            } else {
                System.out.println("Size 0 - Spinner  not available");
                return true;
            }
        });
    }

    // Fluent Wait implementation : wait for element to be clickable
    public void waitForElementClick(final By element) throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<>(Driver.webDriver).withTimeout(100, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        final Boolean until = wait.until((ExpectedCondition<Boolean>) driver -> {
            if (driver != null) {
                driver.findElement(element).click();
            }
            return true;
        });
    }

    // Fluent Wait implementation : wait for element to be visible
    public void waitForElementDisplay(final By element) throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<>(Driver.webDriver).withTimeout(100, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(Exception.class);
        final Boolean until = wait.until((ExpectedCondition<Boolean>) driver -> {
            if (driver != null) {
                driver.findElement(element).isDisplayed();
            }
            return true;
        });
    }

    /**
     * Use to upload files to instance
     *
     * @param folderPath    source directory
     * @param filesToUpload comma separated list of files
     * @throws Exception exception
     */
    public void fileUpload(String folderPath, String filesToUpload) throws Exception {
        robot = new Robot();
        StringSelection locationSelection = new StringSelection(folderPath);
        StringSelection fileSelection = new StringSelection(filesToUpload);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(locationSelection, null);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileSelection, null);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public void uploadAllFiles(String folderPath) throws AWTException {
        robot = new Robot();
        StringSelection locationSelection = new StringSelection(folderPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(locationSelection, null);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        //--- Select all files form dropdown of fileupload window to display all type of files
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB );
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN );
        robot.keyRelease(KeyEvent.VK_SHIFT );
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER );
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);

        //---- Upload all files---------
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);

      //  if (typeOfFile.equals("unsupportedFile")) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_SHIFT);
      //  }
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.setAutoDelay(1000);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER );
    }

    // Robot method used for Launch visionbot designer and close browser pop-up
    public void closePopUp() throws Exception {
        robot = new Robot();
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    // Command runner
    public boolean runBatchFile(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        if (process.exitValue() != 0) {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            System.out.println("Result: " + result);
            return false;
        } else {
            System.out.println("successfully launched");
            return true;
        }
    }

    // Read designer log file which is generated during smoke execution
    public String validateDesignerLogs(String filename) throws Exception {
        StringBuilder results = new StringBuilder();
        Scanner in = new Scanner(new File(filename));
        while (in.hasNext()) { // Iterates each line in the file
            results.append(in.nextLine());
        }
        in.close();
        return results.toString();
    }

    // Function will be used for File Wait Creation
    public void waitForFileCreation(String filename) throws Exception {
        File f = new File(filename);
        while (!f.exists()) {
            f = new File(filename);
            System.out.print("file not created");
        }
    }

    // Get web elements initialized by @FindBy
    public WebElement getWebElement(WebElement element) throws Exception {
        return element;
    }

    // Explicit Wait Implementation : Pass web element xpath and method will wait for element to be clickable - Default time out is 200 Seconds
    public void explicitWait(String elementPath) {
        //By Pritisundar
        By locator = By.xpath(elementPath);
        WebDriverWait wait = new WebDriverWait(Driver.webDriver, 200);
        System.out.println("waiting for element: " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    // Excel data reading methods get data
    public static String getData(String file, String Sheet, int rowNum, int colNum) throws Exception {
        //By Pritisundar
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheet(Sheet);
        XSSFRow row = sheet1.getRow(rowNum);
        String data = row.getCell(colNum).getStringCellValue();
        wb.close();
        return data;
    }

    //Excel write data
    public static void writeData(String file, String Sheet, int rowNum, int colNum, String value)throws Exception{
        //By Pritisundar
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheet(Sheet);
        XSSFRow row = sheet1.getRow(rowNum);
        FileOutputStream fos=new FileOutputStream(file);
        row.createCell(colNum).setCellValue(value);
        wb.write(fos);
        //fos.close();
    }

    //
    public void waitForYetToBeClassifiedToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".datatable-column"),"Yet to be classified"));
    }
}
