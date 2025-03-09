package com.sistemas_examenes.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas_examenes.entities.Exam;
import com.sistemas_examenes.entities.Question;


public interface QuestionRespository extends JpaRepository<Question, Long>{

	Set<Question> findByExam(Exam exam);
}
