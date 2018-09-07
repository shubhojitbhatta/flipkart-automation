package net.com.datadriven;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelUtil(String path) {

        try {
            workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
            sheet = workbook.getSheetAt(0);
        } catch (FileNotFoundException e) {
            new Exception("failed to find workbook at " + path);
        } catch (IOException e) {
            new Exception("failed to read workbook at " + path);
        } catch (Exception e){
            new Exception(e.getMessage());
        }
    }

    public void setSheet(String sheetname) {

    }

    public int getRowCount() {

        return sheet.getPhysicalNumberOfRows();
    }

    public int getHeaderCount(int row) {
        int j = sheet.getRow(row).getPhysicalNumberOfCells();
        int i;
        for (i = 0; i < j; i++) {
            if (getCellData(row, i) == null) {
                break;
            }
        }
        return i;
    }

    public int getColumnCount(int row) {
        return sheet.getRow(row).getPhysicalNumberOfCells();
    }

    public String getCellData(int row, int column) {
        XSSFRow rowCell = sheet.getRow(row);

        if (rowCell == null) {
            return null;
        } else {
            XSSFCell cell = rowCell.getCell(column);
            if (cell != null) {
                return cell.getStringCellValue().trim();
            } else {
                return null;
            }

        }
    }

    public List<String> readRowData(int row) {
        Row row1 = sheet.getRow(row);
        int colCount = row1.getPhysicalNumberOfCells();
        List<String> rowValues = new ArrayList<String>();
        for (int i = 0; i < colCount; i++) {
            Cell cell = row1.getCell(i);
            if (!"".equals(cell.getStringCellValue())) {
                rowValues.add(cell.getStringCellValue());
            } else {
                break;
            }
        }
        return rowValues;
    }

}
