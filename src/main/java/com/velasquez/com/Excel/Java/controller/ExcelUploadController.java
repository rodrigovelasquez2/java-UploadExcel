package com.velasquez.com.Excel.Java.controller;
import com.velasquez.com.Excel.Java.models.entity.Product;
import com.velasquez.com.Excel.Java.services.ExcelService;
import com.velasquez.com.Excel.Java.models.dto.ResponseMessage;
import com.velasquez.com.Excel.Java.utils.ExcelHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelUploadController {

    private final ExcelService excelService;

    @GetMapping
    public String initHello(){
        return "Hello World!";
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message;

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.save(file);
                message = "Archivo subido correctamente: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Error al subir el archivo: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Por favor suba un archivo Excel (xlsx)!";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = excelService.getAllProducts();

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}