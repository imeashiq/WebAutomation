package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public HashMap<String, String> readAttributesFromExcel(String sheetName) {
		// TODO Auto-generated method stub
		HashMap<String, String> attributes = new HashMap<String, String>();
		try {
			FileInputStream file = new FileInputStream(new File("Resources/IdentityAttributes.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell1 = cellIterator.next();
				Cell cell2 = cellIterator.next();
				attributes.put(cell1.getStringCellValue(), cell2.getStringCellValue());
			}
			workbook.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attributes;
	}

}
