package com.demoProject.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demoProject.DTO.CategoryDto;
import com.demoProject.exception.ResourceNotFoundException;
import com.demoProject.model.Category;
import com.demoProject.repository.CategoryRepository;




@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryDto> getAllCategories(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return categoryRepository.findAll(pageable)
                .map(CategoryDto::fromEntity);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return CategoryDto.fromEntity(category);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryDto.toEntity();
        Category savedCategory = categoryRepository.save(category);
        return CategoryDto.fromEntity(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        
        Category updatedCategory = categoryRepository.save(category);
        return CategoryDto.fromEntity(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }
}