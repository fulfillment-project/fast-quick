package com.fastquick.data.util;

import com.fastquick.data.entity.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelGenerator {

    public static ByteArrayInputStream generateExcel(List<Product> products) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("AShop 상품 목록 조회");

            
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("상품코드");
            header.createCell(1).setCellValue("상품명");
            header.createCell(2).setCellValue("상품 바코드");
            header.createCell(3).setCellValue("재고");
            header.createCell(4).setCellValue("안전재고");
            header.createCell(5).setCellValue("입고량");
            header.createCell(6).setCellValue("출고량");

            int rowIdx = 1;
            for (Product product : products) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(product.getProductId());
                row.createCell(1).setCellValue(product.getProductName());
                row.createCell(2).setCellValue(product.getBarcode());               
                row.createCell(3).setCellValue(product.getQuantity());
                row.createCell(4).setCellValue(product.getSafeQuantity());
                row.createCell(5).setCellValue(product.getImportAmount());
                row.createCell(6).setCellValue(product.getExportAmount());
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
        }
    }
}
