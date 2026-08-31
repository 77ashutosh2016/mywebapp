package com.myEkart.web.app.serviceImpl;


import com.myEkart.web.app.dto.ProductDTO;
import com.myEkart.web.app.exception.DBException;
import com.myEkart.web.app.exception.DuplicateResourceException;
import com.myEkart.web.app.exception.ProductNotFoundException;
import com.myEkart.web.app.model.Products;
import com.myEkart.web.app.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


@Slf4j
@Service
public class ProductServiceImpl {

    //private static final Logger log= LoggerFactory.getLogger(ProductServiceImpl.class);




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


    // To getProductById

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



    public List<ProductDTO> getAllProducts()
    {
        List<ProductDTO> productDTOList= new ArrayList<>();
        List<Products> productList= productRepository.findAll();

        if(!productList.isEmpty())
        {
            for (Products products : productList) {
                productDTOList.add(mapToDTO(products));

            }

            return productDTOList;
        }
        else{

            log.error("No Product Found");
            return  Collections.emptyList();


        }


    }







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

    public Products maptoModel(ProductDTO productDTO)
    {
        Products products=new Products();

        products.setProductId(productDTO.getProductId());
        products.setProductName(productDTO.getProductName());
        products.setProductBrand(productDTO.getProductBrand());
        products.setProductCategory(productDTO.getProductCategory());
        products.setProductDescription(productDTO.getProductDescription());
        products.setProductStatus(productDTO.getProductStatus());
        products.setProductPrice(productDTO.getProductPrice());
        products.setProductDiscountPercentage(productDTO.getProductdiscountPercentage());
        products.setProductStockQuantity(productDTO.getProductStockQuantity());

        return products;


    }


    // Updating the Product

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Products products = new Products();
        ProductDTO productDTO1=new ProductDTO();
        boolean present = productRepository.existsById(productDTO.getProductId());
        log.info("Product is available for update ID "+ productDTO.getProductId());

        if (present) {
            products = maptoModel(productDTO);
            Products product1=new Products();
            try {
                 product1 = productRepository.save(products);
            }catch (DBException ex)
            {
                log.error("Error Encounter in DB ");
            }
            productDTO1=mapToDTO(product1);



        } else {
            log.error("Product Not Found ID " + productDTO.getProductId());
            return productDTO;

        }

        return productDTO1;


    }




    // Get product by Price Range


        public List<?> getProductByPriceRange(BigDecimal startPrice, BigDecimal endPrice)
        {

            List<ProductDTO> productDTOList=new ArrayList<>();
            List<Products> products= productRepository.ProductsBetween(startPrice,endPrice);


            if(products.isEmpty())
            {
                throw new ProductNotFoundException(" No Product Found in this Range");

            }
            else {

                for (Products products1 : products) {
                    ProductDTO productDTO = mapToDTO(products1);
                    productDTOList.add(productDTO);

                }
            }

            return Collections.emptyList();


        }


}
