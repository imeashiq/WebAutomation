package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProvider {

	public static Object[][] readExcelData(String excelFileName) throws IOException {

		FileInputStream xl = new FileInputStream("");
		XSSFWorkbook wbook = new XSSFWorkbook(xl);
		XSSFSheet sheet = wbook.getSheetAt(0);
		int rowcount = sheet.getLastRowNum();
		System.out.println("No Of rows=" + rowcount);
		int colcount = sheet.getRow(0).getLastCellNum();
		System.out.println("No Of Columns=" + colcount);
		Object data[][] = new Object[rowcount][colcount];

		for (int i = 1; i <= rowcount; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < colcount; j++) {
				XSSFCell cell = row.getCell(j);
				String StringcellValue = cell.getStringCellValue();
				data[i - 1][j] = StringcellValue;
			}
		}
		return data;
	}
}
