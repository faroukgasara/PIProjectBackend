package tn.esprit.spring.User;

import java.util.List;

import tn.esprit.spring.entity.Expert;

public interface IUserManagement {
	public List<User> getUsers();
	public void deleteUser(String email);
	public void updateUser(Expert user);

}
