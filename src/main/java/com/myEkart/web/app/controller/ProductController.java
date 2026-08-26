package com.myEkart.web.app.controller;


import com.myEkart.web.app.dto.ProductDTO;
import com.myEkart.web.app.serviceImpl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
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
    public ResponseEntity<?> getProducByProductId(@PathVariable Long id)
    {

        ProductDTO productDTO= productService.getProductById(id);
        if(productDTO!=null)
        return ResponseEntity.ok(productDTO);

        else{
            log.error("Product Not found :"+productDTO.getProductId());

           return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .body("Product Not Found ID: ");
        }


    }


    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProducts()
    {

       List< ProductDTO> productDTO= productService.getAllProducts();
            if(!productDTO.isEmpty())
               return ResponseEntity.ok(productDTO);

            else {

                log.error("No Products Found");
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Products Not Found : ");


            }

    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO)
    {

        if(vaildateRequest(productDTO)){
            return ResponseEntity.ok(productService.updateProduct(productDTO));

        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }





    }


    public Boolean vaildateRequest(ProductDTO productDTO)
    {
        boolean result=true;

        if(productDTO.getProductId()==null || productDTO.getProductId()<=0)
        {
            log.error("productId is incorrect");
          return false;
        }

        if(productDTO.getProductName()==null || productDTO.getProductName()=="")
        {
            log.error("productName is incorrect");
            return false;

        }
        return result;


    }


}
