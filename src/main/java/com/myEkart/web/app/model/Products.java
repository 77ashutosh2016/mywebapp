package com.myEkart.web.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="products")
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;


    @NotBlank(message = "Product Name can not blank")
    @Column(nullable = false)
    private String productName;

    @Column(length = 2000)
    private String productDescription;

    @Column(nullable = false)
    @NotNull(message = "Price can not blank")
    private BigDecimal productPrice;



    @Column(nullable = false)
    @NotNull(message = "Product Category can  not blank")
    private String productCategory;


    @Column(nullable = false)
    @NotBlank(message = "Brand is required")
    private String productBrand;


    @Column(name="stock_quantity", nullable = false)
    @Min(value = 0,message = "Stock can not negative")
    private Integer productStockQuantity;

    /*@Column(name="image_url")
    private String productImageUrl;*/



    @Column(name="product_discount")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private BigDecimal productDiscountPercentage=BigDecimal.ZERO;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.ACTIVE;


    @Column(name="created_at", updatable = false)
    private LocalDateTime productCreatedAt;

    @Column(name="updated_at")
    private LocalDateTime productUpdatedAt;


    @PrePersist
    protected void onCreate()
    {
        productCreatedAt= LocalDateTime.now();
        productUpdatedAt=LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate()
    {
        productUpdatedAt= LocalDateTime.now();
    }


    public enum ProductStatus{

        ACTIVE, INACTIVE, OUT_OF_STOCK
    }





}
