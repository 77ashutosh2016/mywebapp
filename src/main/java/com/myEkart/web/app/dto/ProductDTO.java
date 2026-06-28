package com.myEkart.web.app.dto;

import com.myEkart.web.app.model.Products;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDTO {
    private Long productId;

    @NotBlank(message = "Product name required" )
    private String productName;
    private String productDescription;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false , message = "Price must be greater")
    private BigDecimal productPrice;

    @NotBlank(message = "Product Category can not be blank")
    private String productCategory;


    @NotBlank(message = "Brand is required")
    private String productBrand;

    @Min(value = 0)
    private Integer productStockQuantity;

/*
    private String imageUrl;
*/

    private Products.ProductStatus ProductStatus;


    private BigDecimal productdiscountPercentage;

    private LocalDateTime productCreatedAt;
    private LocalDateTime productUpdatedAt;









}
