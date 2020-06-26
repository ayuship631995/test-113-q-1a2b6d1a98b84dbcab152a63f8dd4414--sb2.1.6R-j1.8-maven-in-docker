package org.codejudge.sb.controller;

import java.io.IOException;

import org.codejudge.sb.DemoHelp;
import org.codejudge.sb.entity.QuizQuestionsRepo;
import org.codejudge.sb.entity.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.qos.logback.core.status.Status;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping
public class AppController {

	 @ApiOperation("This is the hello world api")
	    @GetMapping("/")
	    public String hello() {
	        return "Hello World!!";
	    }
	
	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	DemoHelp demoHelp;
	
	@Autowired
	QuizRepository quizRepo;
	
	@Autowired
	QuizQuestionsRepo quesRepo;
	
	@GetMapping(value = "/api/quiz/{quiz_id}")
	public ResponseEntity<?> getQuiz(@ApiParam(value = "quiz_id", required = true) @PathVariable int quiz_id)
			throws IOException {
		ObjectNode output = mapper.createObjectNode();
		if (quizRepo.isQuizExist(quiz_id)) {
			output = (ObjectNode) new ObjectMapper().readTree(mapper.writeValueAsString(quizRepo.findById(quiz_id)));
		} else {
			return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(output, HttpStatus.OK);

	}
	
	
	@PostMapping(value = "/api/quiz")
	public ResponseEntity<?> addQuiz(@RequestBody ObjectNode inputBody) {
		ObjectNode output = mapper.createObjectNode();

		System.out.println(inputBody);
		
		
			if (!demoHelp.isRequiredFieldAvalable(inputBody)) {
				output.put("status", "failure");
				output.put("reason", "input body do not have correct data");
				return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
			}
//			Lead prd = mapper.convertValue(inputBody, Lead.class);
//
//			Long fetch_id = demoHelp.getId();
//			if (!inputBody.has("id")) {
//				prd.setId(fetch_id);
//			}
//			if (prd.getStatus() == null) {
//				Enum<?> status = Enum.valueOf(Status.class, "Created");
//				prd.setStatus((Status) status);
//			}
//			if (prd.getCommunication() == null) {
//				prd.setCommunication("");
//			}
//			repository.insert(prd);
//
//			if (repository.isLeadExist(fetch_id)) {
//				output = (ObjectNode) new ObjectMapper()
//						.readTree(mapper.writeValueAsString(repository.findById(fetch_id)));
//			} else {
//				return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);
//			}

			return new ResponseEntity<>(output, HttpStatus.CREATED);
//		} catch (Exception ex) {
//			output.put("Error", ex.getMessage());
//			return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
//		}
	}
	
	
	@GetMapping(value = "/api/questions/{question_id}")
	public ResponseEntity<?> getQuizQues(@ApiParam(value = "question_id", required = true) @PathVariable int question_id)
			throws IOException {
		ObjectNode output = mapper.createObjectNode();
		if (quesRepo.isQuizQuesExist(question_id)) {
			output = (ObjectNode) new ObjectMapper().readTree(mapper.writeValueAsString(quizRepo.findById(question_id)));
		} else {
			return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(output, HttpStatus.OK);

	}
	
	
	@PostMapping(value = "/api/questions")
	public ResponseEntity<?> addQuizQues(@RequestBody ObjectNode inputBody) {
		ObjectNode output = mapper.createObjectNode();

		System.out.println(inputBody);
		
		
			if (!demoHelp.isQuestionFieldAvalable(inputBody)) {
				output.put("status", "failure");
				output.put("reason", "input body do not have correct data");
				return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
			}
	
			return new ResponseEntity<>(output, HttpStatus.CREATED);
			
	
	
	}	


}
