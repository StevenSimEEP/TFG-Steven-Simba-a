package com.souldevec.security.services;

import java.util.Set;

import com.souldevec.security.entities.Exam;
import com.souldevec.security.entities.Question;

public interface QuestionService {

	Question createQuestion(Question question);
	
	Question updateQuestion(Question question);

	Set<Question> getAllQuestions();
	
	Question getQuestion(Long questionId);
	
	Set<Question> getQuestionsOfExam(Exam exam);
	
	void deleteQuestion(Long questionId);

}
