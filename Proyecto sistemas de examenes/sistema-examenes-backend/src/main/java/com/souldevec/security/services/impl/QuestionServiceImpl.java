package com.souldevec.security.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souldevec.security.entities.Exam;
import com.souldevec.security.entities.Question;
import com.souldevec.security.repositories.QuestionRespository;
import com.souldevec.security.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRespository questionRespository;

	@Override
	public Question createQuestion(Question question) {
		return questionRespository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return questionRespository.save(question);
	}

	@Override
	public Set<Question> getAllQuestions() {
		return new HashSet<>(questionRespository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		return questionRespository.findById(questionId)
				.orElseThrow(() -> new RuntimeException("Pregunta no encontrada con ID: " + questionId));
	}

	@Override
	public Set<Question> getQuestionsOfExam(Exam exam) {
		return questionRespository.findByExam(exam);
	}

	@Override
	public void deleteQuestion(Long questionId) {
		if (questionRespository.existsById(questionId)) {
			questionRespository.deleteById(questionId);
		} else {
			throw new RuntimeException("No se encontró la pregunta con ID: " + questionId);
		}
	}
}
