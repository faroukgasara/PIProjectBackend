package tn.esprit.spring.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Expert;

@Service
public class UserManagement implements IUserManagement{
	
	@Autowired
	private UserRepository myRepository;

	@Override
	public List<User> getUsers() {
		return myRepository.findAll();
	}

	@Override
	public void deleteUser(String email) {
		User u = myRepository.findByEmail(email).orElse(null);
		myRepository.delete(u);
	}

	@Override
	public void updateUser(Expert user) {
		if(user.getAppUserRole().name()=="EXPERT"){
       	 myRepository.save(user);
		}else{
			User u = new User();
			u = user;
			 myRepository.save(
	                new User(
	                		user.getId(),
	                		user.getFirstName(),
	                		user.getLastName(),
	                		user.getEmail(),
	                		user.getPassword(),
	                		user.getAppUserRole(),
	                		user.getSubscriber()
	                )
	        );
		}
		
	}

}
