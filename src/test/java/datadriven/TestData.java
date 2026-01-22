package datadriven;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestData {
    DataFormatter formatter = new DataFormatter();

    public static void main(String[] args) throws IOException {
        List<String> data = getExcelData("Purchase");
        for (String d : data) {
            System.out.print(d + " ");
        }
    }

    @Test(dataProvider = "driveTest")
    public void testCaseData(String greeting, String communication, String id) {
        System.out.println(greeting + communication + id);
    }


    @DataProvider(name = "driveTest")
    public Object[][] getDataUsingTestNG() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\datadriven\\excelDriven.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int columnCount = row.getLastCellNum();
        Object data[][] = new Object[rowCount - 1][columnCount];

        for (int i = 0; i < rowCount - 1; i++) {
            row = sheet.getRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                XSSFCell cell = row.getCell(j);
                data[i][j] = formatter.formatCellValue(cell);
            }
        }

        return data;
    }

    public static List<String> getExcelData(String tcName) throws IOException {
        List<String> arr = new ArrayList<>();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\datadriven\\demodata.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        int sheets = wb.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (wb.getSheetName(i).equalsIgnoreCase("testdata")) {
                XSSFSheet sheet = wb.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> cells = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (cells.hasNext()) {
                    Cell value = cells.next();
                    if (value.getStringCellValue().equalsIgnoreCase("testcases")) {
                        column = k;
                    }
                    k++;
                }
                System.out.println(column);

                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(tcName)) {
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                arr.add(cv.next().getStringCellValue());
                            } else {
                                arr.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return arr;
    }
}
