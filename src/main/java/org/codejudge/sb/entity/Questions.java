package org.codejudge.sb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Integer correct_option;
	private String name;
	private String options;
	private Integer points;
	private Integer quiz;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getCorrect_option() {
		return correct_option;
	}
	public void setCorrect_option(Integer correct_option) {
		this.correct_option = correct_option;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getQuiz() {
		return quiz;
	}
	public void setQuiz(Integer quiz) {
		this.quiz = quiz;
	}
	@Override
	public String toString() {
		return "Questions [id=" + id + ", correct_option=" + correct_option + ", name=" + name + ", options=" + options
				+ ", points=" + points + ", quiz=" + quiz + "]";
	}
	
	
	
}
