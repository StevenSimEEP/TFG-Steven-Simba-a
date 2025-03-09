package com.sistemas_examenes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas_examenes.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
