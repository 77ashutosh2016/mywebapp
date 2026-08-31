package com.myEkart.web.app.repository;

import com.myEkart.web.app.dto.ProductDTO;
import com.myEkart.web.app.model.Products;
import com.myEkart.web.app.serviceImpl.ProductServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {

boolean existsByProductNameIgnoreCase(String productName);


// spring.datasource.url=jdbc:h2:mem:mydb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE

Products findByProductId(Long productId);

   // @Query("Select p from Products where"+ "(p.productPrice >= startPrice) AND "+"p.productPrice<= endPrice")

    @Query("""
           SELECT p
           FROM Products p
           WHERE p.productPrice >= :startPrice
           AND p.productPrice <= :endPrice
           """)
   List<Products> ProductsBetween(@Param("startPrice") BigDecimal startPrice, @Param("endPrice") BigDecimal endPrice);

    /*List<Products> findAllProduct();*/

}
