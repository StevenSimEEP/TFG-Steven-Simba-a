package com.sistemas_examenes.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas_examenes.entities.Exam;
import com.sistemas_examenes.entities.Question;
import com.sistemas_examenes.repositories.QuestionRespository;
import com.sistemas_examenes.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRespository questionRespository;

	@Override
	public Question createQuestion(Question question) {
		if (question == null || question.getContent().isEmpty()) {
			throw new IllegalArgumentException("La pregunta no puede estar vacía.");
		}
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
		Question question = questionRespository.findById(questionId)
				.orElseThrow(() -> new RuntimeException("No se encontró la pregunta con ID: " + questionId));
		questionRespository.delete(question);
	}

	@Override
	public Question listQuestion(Long questionId) {
		return questionRespository.findById(questionId)
				.orElseThrow(() -> new RuntimeException("Pregunta no encontrada con ID: " + questionId));
	}
}
