package tn.esprit.spring.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Expert;
import tn.esprit.spring.entity.Subscriber;
import tn.esprit.spring.repository.SubscriberRepository;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class SubscriberService implements ISubscriberService{
	
	@Autowired
	SubscriberRepository myRepository ;
	
	@Autowired
	UserRepository userRepository ;

	@Override
	public void addSubscriber(String email) {
		User u =  userRepository.findByEmail(email).orElse(null);
		Subscriber s = new Subscriber();
		s.setUser(u);
		s.setPicture_qr("f");
		s.setCreatedAt(LocalDateTime.now());
		s.setExpiresAt(LocalDateTime.now().plusMonths(1));
		Subscriber s1 = myRepository.save(s);
		u.setSubscriber(s1);
		userRepository.save(u);
	}
	

	@Override
	public void unSubscribe(String email, Long id) {
		User u =  userRepository.findByEmail(email).orElse(null);
		myRepository.deleteById(id);
		u.setSubscriber(null);
		userRepository.save(u);
		
	}

}
