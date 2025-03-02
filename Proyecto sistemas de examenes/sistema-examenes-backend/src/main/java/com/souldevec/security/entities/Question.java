package com.souldevec.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;

	@Column(length = 5000, nullable = false)
	private String content;

	@Column
	private String image;

	@Column(nullable = false)
	private String option1;

	@Column(nullable = false)
	private String option2;

	@Column(nullable = false)
	private String option3;

	@Column(nullable = false)
	private String option4;
	
	@Transient
	@Column(nullable = false)
	private String responseGiven;
	
	@Column(nullable = false)
	private String response;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "exam_id", nullable = false)
	private Exam exam;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public Exam getExam() {
		return exam;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getResponseGiven() {
		return responseGiven;
	}

	public void setResponseGiven(String responseGiven) {
		this.responseGiven = responseGiven;
	}

	public Question() {
	}

}