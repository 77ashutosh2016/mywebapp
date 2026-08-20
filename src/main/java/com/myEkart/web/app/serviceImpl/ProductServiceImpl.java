package com.myEkart.web.app.serviceImpl;


import com.myEkart.web.app.dto.ProductDTO;
import com.myEkart.web.app.exception.DuplicateResourceException;
import com.myEkart.web.app.exception.ProductNotFoundException;
import com.myEkart.web.app.model.Products;
import com.myEkart.web.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl {




    @Autowired
    private ProductRepository productRepository;

    public ProductDTO createProduct(ProductDTO productDTO)
    {
         Products products= new Products();
        products.setProductBrand(productDTO.getProductBrand());
        products.setProductId(productDTO.getProductId());
        products.setProductCategory(productDTO.getProductCategory());
        products.setProductDescription(productDTO.getProductDescription());
        products.setProductName(productDTO.getProductName());
        products.setProductPrice(productDTO.getProductPrice());
        products.setProductStockQuantity(productDTO.getProductStockQuantity());
        products.setProductDiscountPercentage(productDTO.getProductdiscountPercentage());
        products.setProductStatus(productDTO.getProductStatus());


        if(productRepository.existsByProductNameIgnoreCase(productDTO.getProductName()))
        {

            throw new DuplicateResourceException("Product with name "+ productDTO.getProductName()+"already exist");
        }

        Products product= productRepository.save(products);

        ProductDTO productDTO1 = new ProductDTO();

        productDTO1.setProductBrand(product.getProductBrand());
        productDTO1.setProductId(product.getProductId());
        productDTO1.setProductCategory(product.getProductCategory());
        productDTO1.setProductDescription(product.getProductDescription());
        productDTO1.setProductName(product.getProductName());
        productDTO1.setProductPrice(product.getProductPrice());
        productDTO1.setProductStockQuantity(product.getProductStockQuantity());
        productDTO1.setProductdiscountPercentage(product.getProductDiscountPercentage());
        productDTO1.setProductStatus(product.getProductStatus());

        return productDTO1;


    }

    public ProductDTO getProductById(Long productId)
    {

        ProductDTO productDTO= new ProductDTO();
        Products products=new Products();


         products= productRepository.findByProductId(productId);

         if(products==null)
         {
             throw new ProductNotFoundException("Product not Found"+ "ProductId"+productId);
         }
         return  mapToDTO(products);

    }



    /*public List<ProductDTO> getAllProducts()
    {
        List<ProductDTO> productDTOList= new ArrayList<>();
        List<Products> productList= productRepository.findAllProduct();

        for(Products products:productList)
        {
            productDTOList.add(mapToDTO(products));

        }

        return productDTOList;


    }
*/






    private ProductDTO mapToDTO(Products products)
    {
        ProductDTO productDTO= new ProductDTO();

        productDTO.setProductName(products.getProductName());
        productDTO.setProductPrice(products.getProductPrice());
        productDTO.setProductDescription(products.getProductDescription());
        productDTO.setProductCategory(products.getProductCategory());
        productDTO.setProductBrand(products.getProductBrand());
        productDTO.setProductdiscountPercentage(products.getProductDiscountPercentage());
        productDTO.setProductStatus(products.getProductStatus());
        productDTO.setProductCreatedAt(products.getProductCreatedAt());
        productDTO.setProductUpdatedAt(products.getProductUpdatedAt());
        productDTO.setProductStockQuantity(products.getProductStockQuantity());
        productDTO.setProductId(products.getProductId());

        return  productDTO;





    }

}
