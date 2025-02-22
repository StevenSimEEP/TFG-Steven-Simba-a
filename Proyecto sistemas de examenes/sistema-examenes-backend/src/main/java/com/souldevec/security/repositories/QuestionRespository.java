package com.souldevec.security.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souldevec.security.entities.Exam;
import com.souldevec.security.entities.Question;


public interface QuestionRespository extends JpaRepository<Question, Long>{

	Set<Question> findByExam(Exam exam);
}
