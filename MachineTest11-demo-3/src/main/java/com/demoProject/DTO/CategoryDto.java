package com.demoProject.DTO;

import com.demoProject.model.Category;

public class CategoryDto {
    private Long id;
    private String name;
    private String description;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Conversion methods
    public static CategoryDto fromEntity(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    public Category toEntity() {
        Category category = new Category();
        category.setId(this.getId());
        category.setName(this.getName());
        category.setDescription(this.getDescription());
        return category;
    }
}