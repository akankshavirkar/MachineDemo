package com.demoProject.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demoProject.DTO.ProductDto;
import com.demoProject.DTO.ProductResponseDto;
import com.demoProject.exception.ResourceNotFoundException;
import com.demoProject.model.Category;
import com.demoProject.model.Product;
import com.demoProject.repository.CategoryRepository;
import com.demoProject.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, 
                            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.findAll(pageable)
                .map(ProductResponseDto::fromEntity);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return ProductResponseDto.fromEntity(product);
    }

    @Override
    public ProductResponseDto createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));
        
        Product product = productDto.toEntity();
        product.setCategory(category);
        
        Product savedProduct = productRepository.save(product);
        return ProductResponseDto.fromEntity(savedProduct);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));
        
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        
        Product updatedProduct = productRepository.save(product);
        return ProductResponseDto.fromEntity(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }
}