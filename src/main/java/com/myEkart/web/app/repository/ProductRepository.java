package com.myEkart.web.app.repository;

import com.myEkart.web.app.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {

boolean existsByProductNameIgnoreCase(String productName);

}
