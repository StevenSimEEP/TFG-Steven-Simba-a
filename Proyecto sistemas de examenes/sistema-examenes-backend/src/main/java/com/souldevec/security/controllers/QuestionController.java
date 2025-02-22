package com.souldevec.security.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
import com.souldevec.security.entities.Question;
import com.souldevec.security.services.ExamService;
import com.souldevec.security.services.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private ExamService examService;

	@PostMapping("/")
	public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.createQuestion(question));
	}

	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.updateQuestion(question));
	}

	@GetMapping("/exam/{examId}")
	public ResponseEntity<?> listExamQuestions(@PathVariable("examId") Long examId) {
		Exam exam = examService.getExam(examId);

		if (exam == null) {
			return ResponseEntity.notFound().build();
		}

		Set<Question> questions = exam.getQuestions();
		List<Question> exams = new ArrayList<>(questions);

		if (exams.size() > Integer.parseInt(exam.getNumberQuestions())) {
			exams = exams.subList(0, Integer.parseInt(exam.getNumberQuestions() + 1));
		}
		
		Collections.shuffle(exams);
		return ResponseEntity.ok(exams);
	}
	
	@GetMapping("/{questionId}")
	public Question listQuestionById(@PathVariable("questionId") Long questionId) {
		return questionService.getQuestion(questionId);
	}
	
	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable("questionId") Long questionId) {
		questionService.deleteQuestion(questionId);
	}
}
