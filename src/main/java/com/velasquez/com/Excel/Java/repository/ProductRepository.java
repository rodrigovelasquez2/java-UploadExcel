package com.velasquez.com.Excel.Java.repository;

import com.velasquez.com.Excel.Java.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
