package edu.ivan.decl.reader;

import edu.ivan.decl.entity.Declaration;
import edu.ivan.decl.entity.ITN;
import edu.ivan.decl.exception.ProjectException;
import edu.ivan.decl.parser.DateParser;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private File file;

    public ExcelReader(File file) {
        this.file = file;
    }

    public List<List<String>> readFile(String sheetName) throws ProjectException{
        HSSFWorkbook workbook;
        List<List<String>> result = new ArrayList<>();
        DateParser dateParser = new DateParser();
        try{
            workbook = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = workbook.getSheet(sheetName);
            for (int r = 195; r < 219; r++) {
                System.out.println(r);
                List<String> currTransport = new ArrayList<>();
                HSSFRow row = sheet.getRow(r);

                //Act date
                String dateOfAct = row.getCell(2).getStringCellValue();
                if(dateOfAct.isEmpty()) {
                    continue;
                }
                currTransport.add(dateOfAct);

                //Contract number
                try {
                    int contractNumber = (int) row.getCell(5).getNumericCellValue();

                    currTransport.add(String.valueOf(contractNumber));
                }catch (IllegalStateException e){
                    currTransport.add("б/н");
                }

                //Contract date
                String contractDate = row.getCell(6).getStringCellValue();
                LocalDate localDate = dateParser.createDate(contractDate);
                currTransport.add(localDate.toString());

                //Route
                String route = row.getCell(7).getStringCellValue();
                currTransport.add(route);

                //Sum
                Double sum = Math.round(row.getCell(10).getNumericCellValue() * 100d) / 100d;
                currTransport.add(sum.toString());


                //ITN number
                try {
                    int ItnNumber = (int) row.getCell(8).getNumericCellValue();

                    currTransport.add(String.valueOf(ItnNumber));
                }catch (IllegalStateException e){
                    currTransport.add("б/н");
                }

                //ITN date
                String ItnDate = row.getCell(9).getStringCellValue();
                LocalDate localItnDate = dateParser.createDate(ItnDate);
                currTransport.add(localItnDate.toString());

                result.add(currTransport);
            }

            HSSFRow row = sheet.getRow(218);
            Double profit = Math.round(row.getCell(10).getNumericCellValue() * 100d) / 100d;
            Declaration.setQuartalProfit(profit);

            workbook.close();
            return result;
        }catch (IOException e) {
            throw new ProjectException(e);
        }
    }
}
