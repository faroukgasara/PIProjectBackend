package tn.esprit.spring.service;


import java.util.List;

import tn.esprit.spring.entity.Answer;

public interface IAnswerService {
	
	public Answer Add(Answer answer, String email);
	//Answer find(Long id);
	Answer update(Answer Answer);
	void delete(Long idAnswer);
	public List<Answer> getAllAnswers();

}
