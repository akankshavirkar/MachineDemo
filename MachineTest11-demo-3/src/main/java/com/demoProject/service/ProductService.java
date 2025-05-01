package com.demoProject.service;





import org.springframework.data.domain.Page;

import com.demoProject.DTO.ProductDto;
import com.demoProject.DTO.ProductResponseDto;


public interface ProductService {
    Page<ProductResponseDto> getAllProducts(int page);
    ProductResponseDto getProductById(Long id);
    ProductResponseDto createProduct(ProductDto productDto);
    ProductResponseDto updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}