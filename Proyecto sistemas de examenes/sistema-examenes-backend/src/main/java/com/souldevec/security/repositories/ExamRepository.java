package com.souldevec.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souldevec.security.entities.Exam;


public interface ExamRepository extends JpaRepository<Exam, Long>{

}
