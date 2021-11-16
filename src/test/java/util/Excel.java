package util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class Excel {
    public static String[][] readData(String src) throws IOException {
        FileInputStream file = new FileInputStream(new File(src));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row r=sheet.getRow(0);
        sheet.removeRow(r);
        String[][] data = new String[sheet.getPhysicalNumberOfRows()][sheet.getRow(1).getPhysicalNumberOfCells()+1];
        int i=0;
        for (Row row : sheet) {
            Iterator<Cell> cellIterator = row.cellIterator();
            data[i][0] = String.valueOf(i+1);
            int j = 1;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC: data[i][j] = String.valueOf((int) cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING: data[i][j] = cell.getStringCellValue();
                        break;
                }
                j++;
            }
            i++;
        }
        return data;
    }
    public static void writeData(String src,String data,int row,int cell) throws IOException {
        FileInputStream file = new FileInputStream(new File(src));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        sheet.getRow(row).createCell(cell).setCellValue(data);
        FileOutputStream out = new FileOutputStream(new File(src));
        workbook.write(out);out.close();
    }
}
