package com.velasquez.com.Excel.Java.services;

import com.velasquez.com.Excel.Java.models.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelService {
    void save(MultipartFile file);

    List<Product> getAllProducts();
}
