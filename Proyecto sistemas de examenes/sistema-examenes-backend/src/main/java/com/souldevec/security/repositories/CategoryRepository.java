package com.souldevec.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souldevec.security.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
