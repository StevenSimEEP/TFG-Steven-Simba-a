package com.sistemas_examenes.services;

import java.util.Set;

import com.sistemas_examenes.entities.Category;

public interface CategoryService {

	Category createCategory(Category category);

	Category updateCategory(Category category);

	Set<Category> getAllCategories();

	Category getCategory(Long categoryId);

	void deleteCategory(Long categoryId);

}
