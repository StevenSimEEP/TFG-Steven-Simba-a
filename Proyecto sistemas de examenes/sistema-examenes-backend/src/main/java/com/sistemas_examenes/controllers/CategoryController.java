package com.sistemas_examenes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemas_examenes.entities.Category;
import com.sistemas_examenes.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		Category savedCategory = categoryService.createCategory(category);
		return ResponseEntity.ok(savedCategory);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<?> listCategoryById(@PathVariable("categoryId") Long categoryId) {
	    Category category = categoryService.getCategory(categoryId);
	    if (category == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(category);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> listCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
	@PutMapping("/")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
	    Category updatedCategory = categoryService.updateCategory(category);
	    return ResponseEntity.ok(updatedCategory);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId) {
	    try {
	        categoryService.deleteCategory(categoryId);
	        return ResponseEntity.ok().body("Categoría eliminada correctamente");
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(404).body("No se encontró la categoría con ID: " + categoryId);
	    }
	}
}
