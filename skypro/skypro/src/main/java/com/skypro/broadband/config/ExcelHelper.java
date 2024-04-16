package com.skypro.broadband.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.skypro.broadband.dto.UserCSVDto;

// import com.zyapaar.analytics.dto.ProfileCompletionFormulaDto;

public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "id", "userId", "userType", "message", "topic", "link", "readFlag", "triggerdBy",
      "createdDate" };
  static String SHEET = "USer List";

  public static ByteArrayInputStream paymentToExcel(List<UserCSVDto> userProfile) {

    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);

      // headerExcel
      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }

      int rowIdx = 1;
      for (UserCSVDto exceldownlaod : userProfile) {
        Row row = sheet.createRow(rowIdx++);

        row.createCell(0).setCellValue(String.valueOf(exceldownlaod.getId()));
        row.createCell(1).setCellValue(String.valueOf(exceldownlaod.getUserId()));
        row.createCell(2).setCellValue(String.valueOf(exceldownlaod.getUserType()));
        row.createCell(3).setCellValue(String.valueOf(exceldownlaod.getMessage()));
        row.createCell(4).setCellValue(String.valueOf(exceldownlaod.getTopic()));
        row.createCell(5).setCellValue(String.valueOf(exceldownlaod.getLink()));
        row.createCell(6).setCellValue(String.valueOf(exceldownlaod.getReadFlag()));
        row.createCell(7).setCellValue(String.valueOf(exceldownlaod.getTriggeredBy()));
        row.createCell(8).setCellValue(String.valueOf(exceldownlaod.getCreatedDate()));
      }

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }
}
