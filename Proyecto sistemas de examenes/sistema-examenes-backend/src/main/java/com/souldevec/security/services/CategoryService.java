package com.souldevec.security.services;

import java.util.Set;

import com.souldevec.security.entities.Category;

public interface CategoryService {

	Category createCategory(Category category);

	Category updateCategory(Category category);

	Set<Category> getAllCategories();

	Category getCategory(Long categoryId);

	void deleteCategory(Long categoryId);

}
