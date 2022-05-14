package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.ChatbotKnowledge;
import tn.esprit.spring.repository.ChatBotRepository;

@Service
public class ChatBotService implements IChatBotService{

	@Autowired
	ChatBotRepository myRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addKnowledge(ChatbotKnowledge k) {
		k.setActive(true);
		myRepository.save(k);
	}
	
	@Override
	public void updateKnowledge(ChatbotKnowledge k) {
		myRepository.save(k);
	}
	
	@Override
	public void deleteKnowledge(long id) {
		myRepository.deleteById(id);
	}

	@Override
	public void setKnowledgeActive(long id) {
		myRepository.setKnowledgeActive(id);
		
	}

	@Override
	public void setKnowledgeInactive(long id) {
		myRepository.setKnowledgeInactive(id);
		
	}

	@Override
	public void trainMe(String question, String answer) {
		ChatbotKnowledge k =new ChatbotKnowledge();
		k.setAnswer(answer);
		k.setQuestion(question);
		k.setActive(false);
		myRepository.save(k);
		
	}

	@Override
	public String answer(String question ,String email) {
		User u = userRepository.findByEmail(email).orElse(null);
		float max =0 ; 
		
	
		
		List<ChatbotKnowledge> ck = (List<ChatbotKnowledge>) myRepository.findAll();
		
		for (ChatbotKnowledge chatbotKnowledge : ck) {
			if(chatbotKnowledge.getActive() && chatbotKnowledge.getQuestion().toLowerCase().contains(question.toLowerCase())){
				return chatbotKnowledge.getAnswer();
			}
			
		}
		
		return "Sorry, Im dumb! ";
		
	}
	
	

}
