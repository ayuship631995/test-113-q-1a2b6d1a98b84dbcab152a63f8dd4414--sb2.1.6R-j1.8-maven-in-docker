package org.codejudge.sb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.codejudge.sb.entity.Quiz;
import org.codejudge.sb.entity.QuizRepository;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.status.Status;

@Component
public class DemoHelp {

//	private static final Logger logger = LoggerFactory.getLogger(DemoHelp.class);

	private static final ObjectMapper mapper = new ObjectMapper();
	private static int inc = 0;
	
	@Autowired
	QuizRepository repository;

	
	
	public int getId() {

		int id = Integer
				.parseInt(String.valueOf(System.currentTimeMillis()).substring(1, 10).concat(String.valueOf(inc)));
		inc = (inc + 1) % 10;
		return id;
	}

	
	public boolean isRequiredFieldAvalable(ObjectNode body) {
		if (body.has("name") && body.has("description") ) {
          return true;
		}
		return false;
	}
	
	public boolean isQuestionFieldAvalable(ObjectNode body) {
		if (body.has("name") && body.has("options") && body.has("correct_option") && body.has("quiz") && body.has("points")) {
          return true;
		}
		return false;
	}
	
	public boolean isCorrectData(ObjectNode body) {
		List<Quiz> Quizs = repository.findAll();
		
		if(body.get("mobile").asText().length() != 10 ) {
			return false;
		}
		
		
		
		
		return true;
	}
	
	public boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	
}