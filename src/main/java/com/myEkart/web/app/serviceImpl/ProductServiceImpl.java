package com.myEkart.web.app.serviceImpl;


import com.myEkart.web.app.dto.ProductDTO;
import com.myEkart.web.app.exception.DuplicateResourceException;
import com.myEkart.web.app.model.Products;
import com.myEkart.web.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
