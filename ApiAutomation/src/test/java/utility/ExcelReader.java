package api.utility;
/**
 * Author: Vijay Bhusri
 * Description: This file contains methods to read data from an excel sheet.
 * We use Apache's poi for reading excel sheet.
 * As poi is really vast and we need only few operations to do, So, this is created to ease the process for
 * everyone in the team. 
 */

import com.aventstack.extentreports.ExtentTest;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import static api.common.GlobalVariables.TEST_DATA_SHEET;
import static api.common.GlobalVariables.TEST_NAME;

// Creating a class which we will be using for reading data from Excel sheet
public class ExcelReader extends Utility {

	// Excel file path
	public String path;
	// For reading data from Excel sheet
	public FileInputStream fis = null;
	// For writing data to Excel sheet
	public FileOutputStream fileOut = null;
	// For .xlsx workbook
	private XSSFWorkbook workbook = null;
	// For .xlsx sheet in a workbook
	private XSSFSheet sheet = null;
	// For row in a sheet
	private XSSFRow row = null;
	// For column in a sheet
	private XSSFCell cell = null;

	//Constructor for ExcelReader class
	public ExcelReader(String path, ExtentTest logTestForTestBase) throws IOException {
		try {
			//Setting path for excel file
			this.path = path;
			//Open the excel file.
			fis = new FileInputStream(path);
			//Creating a workbook object
			workbook = new XSSFWorkbook(fis);
			//Creating a sheet object and reading from first sheet
			sheet = workbook.getSheetAt(0);
			//Closing the excel file object
			fis.close();
		} catch(Exception e){
			log4j.error("ExcelReader method - ERROR - " + e);
			logException(logTestForTestBase, "ExcelReader method - ERROR", e);
		}
	}

	/*
	 *  This method is used to get row count in a sheet
	 *  Input: Sheet Name
	 *  Output: Number of rows in a sheet
	 */

	public int getRowCount(String sheetName) {
		//index can be 0-N number based on how many sheets we have in an excel sheet.
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			return 0; }
		else {
			sheet = workbook.getSheetAt(index);
			int totalNumberOfRows = sheet.getLastRowNum() + 1;
			return totalNumberOfRows;
		}
	}

	/*
	 * This method returns the data from a cell
	 * Input: Sheet name, Column Name, Row Number
	 * Output: Data in String format
	 */
	public String getCellData(String sheetName, String colName, int rowNum) throws IOException {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;

			if(index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);

			for(int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					colNum = i;
			}

			if (colNum == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);

			if (row == null)
				return "";
			cell = row.getCell(colNum);

			if (cell == null)
				return "";

			//Find out if data type is String, then, directly return the data
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
				//If data type is number/numeric values, then:
			else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				//If data is in date format, then, combine data in M/D/YY format and return the data
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
				}
				return cellText;
			}
			else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			//add into log
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}


	/*
	 * This method returns the data from a cell
	 * Input: Sheet name, Column Number, Row Number
	 * Output: Data in String format
	 */
	public String getCellData(String sheetName, int colNum, int rowNum) throws IOException {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);

			if (row == null)
				return "";
			cell = row.getCell(colNum);

			if (cell == null)
				return "";
			//Find out if data type is String, then, directly return the data
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
				//If data type is number/numeric values, then:
			else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				//If data is in date format, then, combine data in M/D/YY format and return the data
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
				}
				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			//add into log
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

	/*
	 * This method sets the data at a specific Column and row.
	 * Input: Sheet name, Column Name, Row Number, Data in String format
	 * Output: true/false (true means data was set successfully, false means data was not set successfully)
	 */
	public boolean setCellData(String sheetName, String colName, int rowNum, String data) throws IOException {
		try {
			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;

			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);

			for(int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}

			if (colNum == -1)
				return false;

			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);

			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);

			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			//add to log
			return false;
		}
		return true;
	}

	/*
	 * This method is used to set data in a specific column and row.
	 * Input: Sheet name, Column Number, Row Number, Data in String format
	 * Output: true/false (true means data was set successfully, false means data was not set successfully)
	 */
	public boolean setCellData(String sheetName, int colNum, int rowNum, String data) throws IOException {
		try {
			if (rowNum <= 0 || colNum <0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);

			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			//add to log
			return false;
		}
		return true;
	}

	/*
	 * This method is used to add a new sheet to the excel workbook
	 * Input: Sheet Name
	 * Output: true, if sheet created successfully. false, if sheet was not created successfully.
	 */
	public boolean addSheet(String sheetName) throws IOException {
		FileOutputStream fileOut;
		try {
			int index = workbook.getSheetIndex(sheetName);
			if(index == -1) {
				workbook.createSheet(sheetName);
				fileOut = new FileOutputStream(path);
				workbook.write(fileOut);
				fileOut.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			//add to log
			return false;
		}
		return true;
	}

	/*
	 * This method is used to delete an existing sheet from the excel workbook
	 * Input: Sheet Name
	 * Output: true, if sheet was deleted successfully. false, if sheet was not deleted successfully.
	 */
	public boolean removeSheet(String sheetName) throws IOException {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * This method is used to add a new column to a sheet
	 * Input: Sheet Name, Column Name
	 * Output: true, if column was added successfully. false, if column was not added successfully.
	 */
	public boolean addColumn(String sheetName, String colName) throws IOException {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);

			if (row == null)
				row = sheet.createRow(0);

			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(colName);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			//add to log
			return false;
		}
		return true;
	}

	/*
	 * This method is used to remove an existing column and all the contents
	 * Input: Sheet Name, Column Number
	 * Output: true, if column was deleted successfully. false, if column was not deleted successfully.
	 */
	public boolean removeColumn(String sheetName, int colNum) throws IOException {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			sheet = workbook.getSheet(sheetName);

			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						row.removeCell(cell);
					}
				}
			}
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			//add to log
			return false;
		}
		return true;
	}

	/*
	 * This method finds whether a sheet exists
	 * Input: Sheet Name
	 * Output: true, if sheet exists. false, if sheet does not exist.
	 */
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			return index != -1;
		} else
			return true;
	}

	/*
	 * This method gets number of columns in a sheet
	 * Input: Sheet Name
	 * Output: Number of columns
	 */
	public int getColumnCount(String sheetName) {
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();
	}

	/*
	 * This method finds text in a column. It assumes that first column is a header.
	 * Input: Sheet Name, Column Name, Data to find in String format
	 * Output: returns -1 if it cannot find otherwise return row number.
	 */
	public int getCellRowNum(String sheetName, String colName, String cellValue) throws IOException {
		//Starting from row number 2 as assuming that we have a header at row#1
		for (int row = 2; row <= getRowCount(sheetName); row++) {
			if (getCellData(sheetName, colName, row).equalsIgnoreCase(cellValue)) {
				return row;
			}
		}
		return -1;
	}
}