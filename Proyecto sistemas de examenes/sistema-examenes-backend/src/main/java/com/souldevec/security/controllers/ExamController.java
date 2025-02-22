package com.souldevec.security.controllers;

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

import com.souldevec.security.entities.Exam;
import com.souldevec.security.services.ExamService;

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
}
