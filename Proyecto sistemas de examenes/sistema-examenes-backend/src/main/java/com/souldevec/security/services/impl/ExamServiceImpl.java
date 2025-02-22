package com.souldevec.security.services.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souldevec.security.entities.Exam;
import com.souldevec.security.repositories.ExamRepository;
import com.souldevec.security.services.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Override
	public Exam createExam(Exam exam) {
		return examRepository.save(exam);
	}

	@Override
	public Exam updateExam(Exam exam) {
		return examRepository.save(exam);
	}

	@Override
	public Set<Exam> getAllExams() {
		return new LinkedHashSet<Exam>(examRepository.findAll());
	}

	@Override
	public Exam getExam(Long examId) {
		return examRepository.findById(examId)
				.orElseThrow(() -> new RuntimeException("Examen no encontrado con ID: " + examId));
	}

	@Override
	public void deleteExam(Long examId) {
		if (examRepository.existsById(examId)) {
			examRepository.deleteById(examId);
		} else {
			throw new RuntimeException("No se encontró el examen con ID: " + examId);
		}
	}
}
