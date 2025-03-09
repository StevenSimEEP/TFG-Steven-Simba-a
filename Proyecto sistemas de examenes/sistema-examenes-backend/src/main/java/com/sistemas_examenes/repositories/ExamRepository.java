package com.sistemas_examenes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas_examenes.entities.Category;
import com.sistemas_examenes.entities.Exam;


public interface ExamRepository extends JpaRepository<Exam, Long>{
	
	List<Exam> findByCategory(Category category);
	
	List<Exam> findByActive(Boolean state);
	
	List<Exam> findByCategoryAndActive(Category category, Boolean state);

}
