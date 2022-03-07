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
import tn.esprit.spring.entity.Answer;
import tn.esprit.spring.service.IAnswerService;


@Api(tags = "Answer")
@RestController
@RequestMapping("/Answer")
public class AnswerController {
	
	@Autowired
	IAnswerService answerService;
	
	@PostMapping("/add-answerService") //both answer and question i'm not sure about them
	@ResponseBody
	public void Add(@PathVariable("Idanswer") Answer a,@PathVariable("email") String email) { 
		answerService.Add(a,email);	
	}
	
	@GetMapping("/answers")
	@ResponseBody
	public List<Answer> getAllanswers(){
		return answerService.getAllAnswers();
	}
	
	@DeleteMapping("/delete-answer/{Idanswer}")
	@ResponseBody
	public void delete(@PathVariable Long Idanswer ) {
		answerService.delete(Idanswer );
	}
	
	@PutMapping("/update-answer")
	@ResponseBody
	public void update(@RequestBody Answer a) 
	{
		answerService.update(a);	
	}
	

	
}
