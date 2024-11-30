package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	public Object[][] getDataFromExcel(String filePath, String sheetName) throws IOException {
	    try (FileInputStream file = new FileInputStream(filePath); 
	         Workbook workbook = WorkbookFactory.create(file)) {
	        
	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) {
	            throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found.");
	        }
	        
	        int rowCount = sheet.getPhysicalNumberOfRows();
	        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
	        Object[][] data = new Object[rowCount - 1][columnCount];
	        
	        for (int i = 1; i < rowCount; i++) {
	            Row row = sheet.getRow(i);
	            for (int j = 0; j < columnCount; j++) {
	                Cell cell = row.getCell(j);
	                if (cell != null) {
	                    switch (cell.getCellType()) {
	                        case STRING:
	                            data[i - 1][j] = cell.getStringCellValue();
	                            break;
	                        case NUMERIC:
	                            if (DateUtil.isCellDateFormatted(cell)) {
	                                data[i - 1][j] = cell.getDateCellValue().toString();
	                            } else {
	                                String cellValue = String.valueOf(cell.getNumericCellValue());
	                                if (cellValue.endsWith(".0")) {
	                                    data[i - 1][j] = cellValue.substring(0, cellValue.length() - 2);
	                                } else {
	                                    data[i - 1][j] = cellValue;
	                                }
	                            }
	                            break;
	                        default:
	                            data[i - 1][j] = "";
	                    }
	                } else {
	                    data[i - 1][j] = "";
	                }
	            }
	        }
	        return data;
	    }
	}

}
