package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Blacklist;
import tn.esprit.spring.repository.BlacklistRepository;

@Service
public class BlacklistService implements IBlacklistService{

	@Autowired
	BlacklistRepository blacklistRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addUserToBlacklist(String email) {
		User u = userRepository.findByEmail(email).orElse(null);
		Blacklist b = new Blacklist();
		b.setUser(u);
		blacklistRepository.save(b);
		
	}

}
