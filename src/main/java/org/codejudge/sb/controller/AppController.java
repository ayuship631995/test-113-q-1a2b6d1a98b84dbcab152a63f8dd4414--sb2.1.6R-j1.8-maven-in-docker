package org.codejudge.sb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codejudge.sb.dao.QuesRepo;
import org.codejudge.sb.dao.QuizRepo;
import org.codejudge.sb.entity.Questions;
import org.codejudge.sb.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.ApiOperation;

@RestController
public class AppController {

	 @ApiOperation("This is the hello world api")
	    @GetMapping("/")
	    public String hello() {
	        return "Hello World!!";
	    }
	
	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private QuesRepo quesRepo;
	

	
	
	@PostMapping("/api/quiz/")
	public ResponseEntity<?> saveQuiz(@RequestBody Quiz quiz ) {
		System.out.println(quiz);
		ObjectNode output = mapper.createObjectNode();
		
		if (quiz.getName() == null || quiz.getDescription() == null) {
			output.put("status", "failure");
			output.put("reason", "input body do not have correct data");
			return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);  			
		}
		
		quizRepo.save(quiz);
		System.out.println(quiz.getId());
		
		Quiz temp = quizRepo.findById(quiz.getId());
		
		return new ResponseEntity<>(temp, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getQuiz")
	public List<Quiz> getAllQuiz(){
		return quizRepo.findAll();
	}
	
	@GetMapping("/api/quiz/{id}")
	public ResponseEntity<?> getQuizById(@PathVariable int id){
		Quiz temp =  quizRepo.findById(id);
		ObjectNode output = mapper.createObjectNode();
		if(temp == null) {
			return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);  
		}
		
		return new ResponseEntity<>(temp,HttpStatus.OK);
			
	}

	@PostMapping("/api/questions/")
	public ResponseEntity<?> saveQues(@RequestBody Questions ques ) {
		System.out.println(ques);
		ObjectNode output = mapper.createObjectNode();
		
		
		
		if (ques.getName() == null || ques.getOptions() == null || ques.getCorrect_option() == null
				|| ques.getQuiz() == null || ques.getPoints() == null || !quizRepo.existsById(ques.getQuiz())) {
			output.put("status", "failure");
			output.put("reason", "input body do not have correct data");
			return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);  			
		}
		
		quesRepo.save(ques);
		
		Questions temp = quesRepo.findById(ques.getId());
		
		return new ResponseEntity<>(temp, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getQues")
	public List<Questions> getAllQues(){
		return quesRepo.findAll();
	}
	
	@GetMapping("/api/questions/{id}")
	public ResponseEntity<?> getQuestionById(@PathVariable int id){
		
		Questions temp =  quesRepo.findById(id);
		ObjectNode output = mapper.createObjectNode();
		if(temp == null) {
			return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);  
		}
		
		return new ResponseEntity<>(temp,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/api/quiz-questions/{id}")
	public ResponseEntity<?> getQuizQuestionById(@PathVariable int id) throws IOException{

	ObjectNode output = mapper.createObjectNode();
	ObjectNode jNode = mapper.createObjectNode();
	Quiz temp =  quizRepo.findById(id);

	if(temp == null) {
	return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);  
	}

	ArrayNode arrayNode = mapper.createObjectNode().arrayNode();
	output.put("name", temp.getName());
	output.put("description", temp.getDescription());

	List<Questions> allQues = quesRepo.findAll();
	for(Questions q : allQues) {
	if(q.getQuiz() == id) {

	JsonNode addNode = mapper.valueToTree(q);
	//JsonNode addNode2 =  mapper.readTree(mapper.writeValueAsString(q));
	jNode.set("questions", arrayNode.add(addNode));
	}

	}
	output.setAll(jNode);

	return new ResponseEntity<>(output,HttpStatus.OK);
	}
	
	
	

}
