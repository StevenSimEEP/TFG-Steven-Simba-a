package com.sistemas_examenes.services;

import java.util.Set;

import com.sistemas_examenes.entities.Exam;
import com.sistemas_examenes.entities.Question;

public interface QuestionService {

	Question createQuestion(Question question);
	
	Question updateQuestion(Question question);

	Set<Question> getAllQuestions();
	
	Question getQuestion(Long questionId);
	
	Set<Question> getQuestionsOfExam(Exam exam);
	
	void deleteQuestion(Long questionId);
	
	Question listQuestion(Long questionId);

}
