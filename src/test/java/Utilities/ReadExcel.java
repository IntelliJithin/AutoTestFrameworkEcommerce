package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    XSSFWorkbook excelWorkbook = null;
    XSSFSheet excelSheet = null;
    XSSFRow row = null;
    XSSFCell cell = null;

   public String getData(String sheetName, int colNum, int rowNum)
   {
       try {
           if (rowNum<=0)
               return "";
           int index = excelWorkbook.getSheetIndex(sheetName);
           if (index== -1)
               return "";
           excelSheet = excelWorkbook.getSheetAt(index);
           row = excelSheet.getRow(rowNum-1);
           if (row == null)
               return "";
           cell = row.getCell(colNum);
           if (cell==null)
               return "";
           if (cell.getCellType()== Cell.CELL_TYPE_STRING)
               return cell.getStringCellValue();
           else if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
           {
               return String.valueOf(cell.getNumericCellValue());
           }
           else if (cell.getCellType()==Cell.CELL_TYPE_BLANK)
               return "";
           else
               return String.valueOf(cell.getBooleanCellValue());
       }
       catch (Exception e)
       {
           e.printStackTrace();
           return "row "+rowNum+" or column "+ colNum+ " does not exist";
       }
   }

}
