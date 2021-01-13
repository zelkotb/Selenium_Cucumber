package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;

/**
 * 
 * @author EL KOTB ZAKARIA
 *
 */

public class ExcelUtil {
	// Main Directory of the project
	public static final String currentDir = System.getProperty("user.dir");

	// Location of Test data excel file
	public static String testDataExcelPath = null;

	// Excel Sheet
	public static String testDataExcelFileName = null;

	// Excel WorkBook
	private static XSSFWorkbook excelWBook;

	// Excel Sheet
	private static XSSFSheet excelWSheet;

	// Excel cell
	private static XSSFCell cell;

	// Excel row
	private static XSSFRow row;

	// Row Number
	public static int rowNumber;

	// Column Number
	public static int columnNumber;

	// Column Name
	public static String columnName;

	// Setter and Getters of row and columns
	public static void setRowNumber(int pRowNumber) {
		ExcelUtil.rowNumber = pRowNumber;
	}

	public static int getRowNumber() {
		return ExcelUtil.rowNumber;
	}

	public static void setColumnName(String pcolumnName) {
		ExcelUtil.columnName = pcolumnName;
	}

	public static String getColumnName() {
		return ExcelUtil.columnName;
	}

	public static void setColumnNumber(int pColumnNumber) {
		ExcelUtil.columnNumber = pColumnNumber;
	}

	public static int getColumnNumber() {
		return ExcelUtil.columnNumber;
	}

	public static void setFileName(String ptestDataExcelFileName) {
		ExcelUtil.testDataExcelFileName = ptestDataExcelFileName;
	}

	public static int getLastRowNum(String sheetName) throws IOException {
		// Open the Excel file
		FileInputStream ExcelFile = new FileInputStream(ExcelUtil.testDataExcelPath + ExcelUtil.testDataExcelFileName);
		ExcelUtil.excelWBook = new XSSFWorkbook(ExcelFile);
		ExcelUtil.excelWSheet = ExcelUtil.excelWBook.getSheet(sheetName);
		return ExcelUtil.excelWSheet.getLastRowNum();
	}

	// This method has two parameters: "Test data excel file name" and "Excel sheet
	// name"
	// It creates FileInputStream and set excel file and excel sheet to excelWBook
	// and excelWSheet variables.
	public static void setExcelFileSheet(String sheetName) {
		// MAC or Windows Selection for excel path
		if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
			ExcelUtil.testDataExcelPath = ExcelUtil.currentDir + "//src//test//resources//";
		} else if (Platform.getCurrent().toString().contains("WIN")) {
			ExcelUtil.testDataExcelPath = ExcelUtil.currentDir + "\\src\\test\\resources\\";
		} else {
			ExcelUtil.testDataExcelPath = "C:\\Users\\Administrateur\\git\\uat\\src\\test\\resources\\";
		}

		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(
					ExcelUtil.testDataExcelPath + ExcelUtil.testDataExcelFileName);
			ExcelUtil.excelWBook = new XSSFWorkbook(ExcelFile);
			ExcelUtil.excelWSheet = ExcelUtil.excelWBook.getSheet(sheetName);
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// This method reads the test data from the Excel cell.
	// We are passing row number and column number as parameters.
	public static String getCellData(int RowNum, int ColNum) {
		try {
			ExcelUtil.cell = ExcelUtil.excelWSheet.getRow(RowNum).getCell(ColNum);
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(ExcelUtil.cell);
			return cellData;
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method takes row number as a parameter and returns the data of given row
	// number.
	public static XSSFRow getRowData(int RowNum) {
		try {
			ExcelUtil.row = ExcelUtil.excelWSheet.getRow(RowNum);
			return ExcelUtil.row;
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method gets excel file, row and column number and set a value to the
	// that cell.
	public static void setCellData(String value, int RowNum, int ColNum) {
		try {
			ExcelUtil.row = ExcelUtil.excelWSheet.getRow(RowNum);
			ExcelUtil.cell = ExcelUtil.row.getCell(ColNum);
			if (ExcelUtil.cell == null) {
				ExcelUtil.cell = ExcelUtil.row.createCell(ColNum);
				ExcelUtil.cell.setCellValue(value);
			} else {
				ExcelUtil.cell.setCellValue(value);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(
					ExcelUtil.testDataExcelPath + ExcelUtil.testDataExcelFileName);
			ExcelUtil.excelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void setCellDataByName(String value, int RowNum, String colName) {
		try {
			int ColNum = -1;
			ExcelUtil.row = ExcelUtil.excelWSheet.getRow(0);

			for (int i = 0; i < ExcelUtil.row.getLastCellNum(); i++) {
				if (ExcelUtil.row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
					ColNum = i;
				}
			}

			ExcelUtil.row = ExcelUtil.excelWSheet.getRow(RowNum);
			ExcelUtil.cell = ExcelUtil.row.getCell(ColNum);
			if (ExcelUtil.cell == null) {
				ExcelUtil.cell = ExcelUtil.row.createCell(ColNum);
				ExcelUtil.cell.setCellValue(value);
			} else {
				ExcelUtil.cell.setCellValue(value);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(
					ExcelUtil.testDataExcelPath + ExcelUtil.testDataExcelFileName);
			ExcelUtil.excelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public static String getCellDataByName(int RowNum, String colName) throws IOException {
		int col_Num = -1;
		ExcelUtil.row = ExcelUtil.excelWSheet.getRow(0);

		for (int i = 0; i < ExcelUtil.row.getLastCellNum(); i++) {
			if (ExcelUtil.row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
				col_Num = i;
			}
		}

		ExcelUtil.row = ExcelUtil.excelWSheet.getRow(RowNum);
		ExcelUtil.cell = ExcelUtil.row.getCell(col_Num);

		if (ExcelUtil.cell.getCellType() == CellType.STRING) {
			return ExcelUtil.cell.getStringCellValue();
		} else if ((ExcelUtil.cell.getCellType() == CellType.NUMERIC)
				|| (ExcelUtil.cell.getCellType() == CellType.FORMULA)) {
			String cellValue = String.valueOf(ExcelUtil.cell.getNumericCellValue());
			if (DateUtil.isCellDateFormatted(ExcelUtil.cell)) {
				DateFormat df = new SimpleDateFormat("dd/MM/yy");
				Date date = ExcelUtil.cell.getDateCellValue();
				cellValue = df.format(date);
			}
			return cellValue;
		}

		else if (ExcelUtil.cell.getCellType() == CellType.BLANK) {
			return "";
		}

		else {
			return String.valueOf(ExcelUtil.cell.getBooleanCellValue());
		}

	}
}