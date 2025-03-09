package com.sistemas_examenes.services.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas_examenes.entities.Category;
import com.sistemas_examenes.repositories.CategoryRepository;
import com.sistemas_examenes.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Set<Category> getAllCategories() {
		return new LinkedHashSet<>(categoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoryId));
	}

	@Override
	public void deleteCategory(Long categoryId) {
		if (categoryRepository.existsById(categoryId)) {
			categoryRepository.deleteById(categoryId);
		} else {
			throw new RuntimeException("No se encontró la categoría con ID: " + categoryId);
		}
	}
}
