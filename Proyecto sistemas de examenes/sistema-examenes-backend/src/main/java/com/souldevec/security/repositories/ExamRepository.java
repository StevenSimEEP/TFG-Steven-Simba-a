package com.souldevec.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souldevec.security.entities.Category;
import com.souldevec.security.entities.Exam;


public interface ExamRepository extends JpaRepository<Exam, Long>{
	
	List<Exam> findByCategory(Category category);
	
	List<Exam> findByActive(Boolean state);
	
	List<Exam> findByCategoryAndActive(Category category, Boolean state);

}
