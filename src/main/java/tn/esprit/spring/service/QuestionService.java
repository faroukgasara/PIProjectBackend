package tn.esprit.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Answer;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.entity.Quiz;
import tn.esprit.spring.repository.AnswerRepository;
import tn.esprit.spring.repository.QuestionRepository;
import tn.esprit.spring.repository.QuizRepository;

@Service
public class QuestionService implements IQuestionService{

	private QuestionRepository questionRepository;
	UserRepository userRepository;
	AnswerRepository  answerRepository;
	QuizRepository quizRepository;
	
	
	@Override
	public Question Add(Question question, String email) {
		User u = userRepository.findByEmail(email).orElse(null);
		return questionRepository.save(question);
	}

	@Override
	public Question update(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public void delete(Long idQuestion) {
		questionRepository.deleteById(idQuestion);
		
	}

	@Override
	public List<Question> getAllQuestions() {
		return (List<Question>) questionRepository.findAll();

	}

	//********************************************************************************************
	@Override
	public void AffectAnswerToQuestion(Long IdQuestion, Long IdAnswer) {
		Answer a= answerRepository.findById(IdAnswer).orElse(null);
		Question q= questionRepository.findById(IdQuestion).orElse(null);
		a.setQuestion(q);
		answerRepository.save(a);	
	}

	@Override
	public void AffectQuestionToQuiz(Long IdQuestion, Long IdQuiz) {
		Question q = questionRepository.findById(IdQuestion).orElse(null);
		Quiz quiz = quizRepository.findById(IdQuiz).orElse(null);
		quiz.getQuestions().add(q);
		questionRepository.save(q);
		
	}
	

	//@Override
	//public List<Answer> findAnswersByQuestion(Long id) {
		// TODO Auto-generated method stub
	//	return null;
	//}

	//@Override
	//@Transactional(readOnly = true)
	//public List<Answer> findAnswersByQuestion(Long id) {
		//Question q = find(id);
	//	return q.getAnswers();
	//}

}
