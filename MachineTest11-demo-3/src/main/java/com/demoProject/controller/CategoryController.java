package com.demoProject.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demoProject.DTO.CategoryDto;
import com.demoProject.service.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
     this.categoryService = categoryService;
       // System.out.println(this.categoryService+"Akanksha");
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> getAllCategories(
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(categoryService.getAllCategories(page));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
//        return ResponseEntity.ok(categoryService.getCategoryById(id));
//    }
//
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    //Created the new values
    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    //Updated the values in tables
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}