package tn.esprit.spring.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagement implements IUserManagement{
	
	@Autowired
	private UserRepository myRepository;

	@Override
	public List<User> getUsers() {
		return myRepository.findAll();
	}

}
