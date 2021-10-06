package com.school.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.school.model.ExamModule;
import com.school.model.QuestionModel;
import com.school.model.ResultPojo;
import com.school.model.questionForm;
import com.school.repository.ExamModuleRepo;
import com.school.repository.QuestionRepo;
import com.school.repository.ResultRepo;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepo questRepo;

	@Autowired
	private ExamModuleRepo examModuleRepo;

	@Autowired
	private ResultRepo resultRepo;

	@Autowired
	private questionForm qForm;

	public QuestionModel saveData(String title, String optionA, String optionB, String optionC, String optionD, int ans,
			int choose, String mark, int examId) {

		ExamModule ex = examModuleRepo.findById(examId).orElse(new ExamModule());

		QuestionModel que = new QuestionModel(title, optionA, optionB, optionC, optionD, ans, choose, mark, ex);

		questRepo.save(que);
		return que;
	}

	public questionForm getQuestions(int examId) {

		List<QuestionModel> allQues = questRepo.findByExamId(examId);
		qForm.setQform(allQues);
		return qForm;
	}

	public questionForm getQuestions() {
		List<QuestionModel> allQues = questRepo.findAll();
		List<QuestionModel> qList = new ArrayList<QuestionModel>();

		Random random = new Random();

		for (int i = 0; i < 5; i++) {
			int rand = random.nextInt(allQues.size());
			qList.add(allQues.get(rand));
			allQues.remove(rand);
		}

		qForm.setQform(qList);

		return qForm;
	}

	public void saveScore(ResultPojo result) {

		ResultPojo saveResult = new ResultPojo();
		saveResult.setUsername(result.getUsername());
		saveResult.setTotalCorrect(result.getTotalCorrect());
		resultRepo.save(saveResult);
	}

	public int getResult(questionForm qForm) {
		int correct = 0;
		int marks = 0;

		for (QuestionModel q : qForm.getQform()) {
			if (q.getAns() == q.getChoose()) {

				correct++;
			}
		}

		return correct;
	}

	public List<ResultPojo> getTopScore() {
		List<ResultPojo> sList = resultRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));

		return sList;
	}

}
