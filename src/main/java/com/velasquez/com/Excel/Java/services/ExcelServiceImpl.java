package com.velasquez.com.Excel.Java.services;

import com.velasquez.com.Excel.Java.models.entity.Product;
import com.velasquez.com.Excel.Java.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    private final ProductRepository productRepository;

    @Override
    public void save(MultipartFile file) {
        try {
            List<Product> products = excelToProducts(file.getInputStream());
            productRepository.saveAll(products);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar el archivo Excel: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    private List<Product> excelToProducts(InputStream inputStream) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Product> products = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Saltar encabezado
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Product product = new Product();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            product.setName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            product.setPrice(currentCell.getNumericCellValue());
                            break;
                        case 2:
                            product.setQuantity((int) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            product.setCategory(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                products.add(product);
            }

            workbook.close();
            return products;
        } catch (IOException e) {
            throw new RuntimeException("Error al parsear el archivo Excel: " + e.getMessage());
        }
    }
}
