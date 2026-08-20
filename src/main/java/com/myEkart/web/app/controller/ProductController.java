package com.myEkart.web.app.controller;


import com.myEkart.web.app.dto.ProductDTO;
import com.myEkart.web.app.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    ProductServiceImpl productService;


    @PostMapping("/createProduct")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO)
    {
        ProductDTO productDTO1= productService.createProduct(productDTO);

        return ResponseEntity.ok(productDTO1);
    }



    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDTO> getProducByProductId(@PathVariable Long id)
    {
        ProductDTO productDTO= productService.getProductById(id);

        return ResponseEntity.ok(productDTO);


    }


}
