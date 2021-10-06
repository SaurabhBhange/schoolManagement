package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.QuestionModel;
import com.school.model.ResultPojo;
import com.school.model.questionForm;
import com.school.service.QuestionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/")
public class QuizController {

	@Autowired
	QuestionService qService;

	@PostMapping("/quiz/submit")
	public ResponseEntity<?> submit(@RequestBody questionForm qForm, @RequestParam String username,@RequestParam int examId) {

		ResultPojo result = new ResultPojo();
		result.setExamId(examId);
		result.setUsername(username);
		result.setTotalQuestions(qForm.getQform().size());
		result.setTotalCorrect(qService.getResult(qForm));
		result.setTotalWrong(result.getTotalQuestions() - result.getTotalCorrect());
		
		qService.saveScore(result);

		return new ResponseEntity<ResultPojo>(result, HttpStatus.OK);
	}

	@GetMapping("/start/quiz")
	public ResponseEntity<?> startExam(@RequestParam String username, @RequestParam int examId) {
		if (username.equals("")) {
			return new ResponseEntity<String>("username cannot be null", HttpStatus.BAD_REQUEST);
		}

		questionForm questions = qService.getQuestions(examId);

		return new ResponseEntity<questionForm>(questions, HttpStatus.OK);
		}

	
	@PostMapping("/quiz/addquesion")
	public ResponseEntity<?> addQuestion(@RequestParam("title") String title, @RequestParam("optionA") String optionA,
			@RequestParam("optionB") String optionB, @RequestParam("optionC") String optionC,
			@RequestParam("optionD") String optionD, @RequestParam("ans") int ans,@RequestParam("choose") int choose,
			@RequestParam("examId") int examId, @RequestParam("mark") String mark) {

		QuestionModel model = qService.saveData(title, optionA, optionB, optionC, optionD, ans,choose, mark, examId);
		return new ResponseEntity<QuestionModel>(model, HttpStatus.OK);
	}
}
