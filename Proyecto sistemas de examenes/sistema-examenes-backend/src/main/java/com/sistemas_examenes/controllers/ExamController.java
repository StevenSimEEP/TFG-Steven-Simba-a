package com.sistemas_examenes.controllers;

import java.util.List;

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
import com.sistemas_examenes.entities.Exam;
import com.sistemas_examenes.services.ExamService;

@RestController
@RequestMapping("/exam")
@CrossOrigin("*")
public class ExamController {

	@Autowired
	private ExamService examService;

	@PostMapping("/")
	public ResponseEntity<Exam> saveExam(@RequestBody Exam exam) {
		return ResponseEntity.ok(examService.createExam(exam));
	}

	@PutMapping("/")
	public ResponseEntity<Exam> updateExam(@RequestBody Exam exam) {
		return ResponseEntity.ok(examService.updateExam(exam));
	}

	@GetMapping("/")
	public ResponseEntity<?> listExams() {
		return ResponseEntity.ok(examService.getAllExams());
	}

	@GetMapping("/{examId}")
	public ResponseEntity<Exam> listExam(@PathVariable("examId") Long examId) {
	    Exam exam = examService.getExam(examId);
	    if (exam == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(exam);
	}

	@DeleteMapping("/{examId}")
	public ResponseEntity<?> deleteExam(@PathVariable("examId") Long examId) {
	    Exam exam = examService.getExam(examId);
	    if (exam == null) {
	        return ResponseEntity.notFound().build();
	    }
	    examService.deleteExam(examId);
	    return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/category/{categoryId}")
	public List<Exam> listExamsByCategory(@PathVariable("categoryId") Long categoryId) {
		Category category = new Category();
		category.setCategoryId(categoryId);
		return examService.listExamsByCategory(category);
	}
	
	@GetMapping("/active")
	public List<Exam> listActiveExams() {
		return examService.getActiveExams(); 
	}
	
	@GetMapping("/category/active/{categoryId}")
	public List<Exam> listActiveExamsByCategory(@PathVariable("categoryId") Long categoryId) {
		Category category = new Category();
		category.setCategoryId(categoryId);
		return examService.getActiveExamsByCategory(category);
	}
}
