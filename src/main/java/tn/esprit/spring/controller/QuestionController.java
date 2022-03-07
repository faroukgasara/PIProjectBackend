package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.service.IAnswerService;
import tn.esprit.spring.service.IQuestionService;


@Api(tags = "Question")
@RestController
@RequestMapping("/Question")
public class QuestionController {
	
	@Autowired
	IQuestionService questionService;
	@Autowired
	IAnswerService AnswerService;
	
	
	@PostMapping("/add-question")
	@ResponseBody
	public void Add(@PathVariable("Idquestion") Question q,@PathVariable("email") String email) { 
		
		
		questionService.Add(q,email);	
	}
	
	@GetMapping("/questions")
	@ResponseBody
	public List<Question> getAllquestions(){
		return questionService.getAllQuestions();
	}
	
	@DeleteMapping("/delete-question/{Idquestion}")
	@ResponseBody
	public void Deletequestion(@PathVariable Long Idquestion ) {
		questionService.delete(Idquestion );
	}
	
	@PutMapping("/update-question")
	@ResponseBody
	public void Update(@RequestBody Question q) 
	{
		questionService.update(q);	
	}
	
	@PutMapping("/Affecter/{Idanswer}/{Idquestion}")
	@ResponseBody
	public void AffectAnswerToQuestion(@PathVariable("IdQuestion") Long IdQuestion,@PathVariable("IdAnswer")Long IdAnswer){
		
		questionService.AffectAnswerToQuestion(IdQuestion, IdAnswer);
		
	}
	@PutMapping("/Affecter/{IdQuiz}/{Idquestion}")
	@ResponseBody
	public void AffectQuestionToQuiz(@PathVariable("IdQuestion")Long IdQuestion,@PathVariable("IdQuiz") Long IdQuiz){
		questionService.AffectAnswerToQuestion(IdQuestion, IdQuiz);
	}
	

}
