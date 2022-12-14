package utills;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class ExcelUtills {
	private XSSFSheet ExcelWSheet;
	private XSSFWorkbook ExcelWBook;
	private FileInputStream ExcelFile;

	// Constructor to connect to the Excel with sheetname and Path
	public ExcelUtills(String Path, String SheetName) throws Exception {

		try {
			// Open the Excel file
			ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to set the rowcount of the excel.
	public int excel_get_rows() throws Exception {

		try {
			return ExcelWSheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method to get the data and get the value as strings.
	public String getCellDataasstring(int RowNum, int ColNum) throws Exception {

		try {
			String CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
			//System.out.println("The value of CellData " + CellData);
			return CellData;
		} catch (Exception e) {
			return "Errors in Getting Cell Data";
		}
	}

	// This method to get the data and get the value as number.
	public double getCellDataasnumber(int RowNum, int ColNum) throws Exception {

		try {
			double CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();
			//System.out.println("The value of CellData " + CellData);
			return CellData;
		} catch (Exception e) {
			return 000.00;
		}
	}

	public Object[][] getTableArray() throws Exception

	{
		String[][] tabArray = null;

		try {

			int startRow = 2;

			int startCol = 0;

			int totalRows = ExcelWSheet.getLastRowNum();

			int totalCols = ExcelWSheet.getRow(0).getLastCellNum();

			tabArray = new String[totalRows][totalCols];

			for (int i = startRow - 1; i <= totalRows; i++) {

				for (int j = startCol; j < totalCols; j++) {

					tabArray[i - 1][j] = getCellDataasstring(i, j);

					//System.out.println(tabArray[startRow - 1][startCol]);

				}

			}

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		} finally {
			ExcelWBook.close();
			ExcelFile.close();
		}

		return (tabArray);

	}

	public Object[][] getTableArray(String Path, String SheetName) throws Exception {

		String[][] tabArray = null;

		try {

			ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 2;

			int startCol = 0;

			int totalRows = ExcelWSheet.getLastRowNum();

			int totalCols = ExcelWSheet.getRow(0).getLastCellNum();

			tabArray = new String[totalRows][totalCols];

			for (int i = startRow - 1; i <= totalRows; i++) {

				for (int j = startCol; j < totalCols; j++) {

					tabArray[i - 1][j] = getCellDataasstring(i, j);

					System.out.println(tabArray[startRow - 1][startCol]);

				}

			}

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		} finally {
			ExcelWBook.close();
			ExcelFile.close();
		}

		return (tabArray);

	}

}