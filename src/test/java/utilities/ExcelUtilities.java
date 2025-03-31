package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtilities {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;


    public ExcelUtilities(String path){

        this.path=path;

    }

    public int getRowcount(String sheetname) throws IOException{

        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet= workbook.getSheet(sheetname);
        int rowcount=sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    public int getCellcount(String sheetname, int rownum) throws IOException{

        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet= workbook.getSheet(sheetname);
        row=sheet.getRow(rownum);
        int cellcount=row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    public String getCellData(String sheetName,int rownum,int colnum) throws IOException{

        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell=row.getCell(colnum);

        DataFormatter formatter=new DataFormatter();
        String data;

        try{

            data= formatter.formatCellValue(cell);//Retun the formatted value of a cell into string format
        } catch (Exception e) {

            data="";
        }

        workbook.close();
        fi.close();
        return data;

    }

    public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException{

        File xlfile= new File(path);
        if(!xlfile.exists()) //if file not exists then create a new file
        {
            workbook=new XSSFWorkbook();
            fo=new FileOutputStream(path);
            workbook.write(fo);
        }

        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);

        if(workbook.getSheetIndex(sheetName)==-1) //if sheet not exists then create a new one
            workbook.createSheet(sheetName);
        sheet=workbook.getSheet(sheetName);

        if(sheet.getRow(rownum)==null)
            sheet.createRow(rownum);
        row=sheet.getRow(rownum);

        cell=row.createCell(colnum);
        cell.setCellValue(data);
        fo=new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }


}
