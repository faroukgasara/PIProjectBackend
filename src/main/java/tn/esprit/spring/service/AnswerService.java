package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Answer;
import tn.esprit.spring.repository.AnswerRepository;


@Service
public class AnswerService implements IAnswerService{

	private AnswerRepository answerRepository;
	UserRepository userRepository;

	@Override
	@Transactional
	public Answer Add(Answer answer, String email) {
		//accessControlService.checkUserPriviledges(Iduser, answer);
		User u = userRepository.findByEmail(email).orElse(null);
				return answerRepository.save(answer);
	}
	@Override
	public List<Answer> getAllAnswers() {
		return (List<Answer>) answerRepository.findAll();
	}

	@Override
	@Transactional
	public Answer update(Answer Answer){
		
		return answerRepository.save(Answer);
	}

	@Override
	@Transactional
	public void delete(Long idAnswer) {
		answerRepository.deleteById(idAnswer);
		
		
	}
	
}
