package com.myEkart.web.app.repository;

import com.myEkart.web.app.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {

boolean existsByProductNameIgnoreCase(String productName);

Products findByProductId(Long productId);

/*List<Products> findAllProduct();*/

}
