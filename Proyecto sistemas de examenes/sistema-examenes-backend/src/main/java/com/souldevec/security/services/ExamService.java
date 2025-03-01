package com.souldevec.security.services;

import java.util.List;
import java.util.Set;

import com.souldevec.security.entities.Category;
import com.souldevec.security.entities.Exam;

public interface ExamService {

	Exam createExam(Exam exam);
	
	Exam updateExam(Exam exam);
	
	Set<Exam> getAllExams();
	
	Exam getExam(Long examId);
	
	void deleteExam(Long examId);
	
	List<Exam> listExamsByCategory(Category category );
	
	List<Exam> getActiveExams();
	
	List<Exam> getActiveExamsByCategory(Category category); 
	
}
