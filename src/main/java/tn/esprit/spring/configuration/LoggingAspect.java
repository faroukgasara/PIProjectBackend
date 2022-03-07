package tn.esprit.spring.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.ToDoList;
import lombok.AllArgsConstructor;
import tn.esprit.spring.User.IUserManagement;
import tn.esprit.spring.registration.token.ConfirmationTokenRepository;
import tn.esprit.spring.registration.token.ConfirmationTokenService;
import tn.esprit.spring.service.INotificationUserService;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@AllArgsConstructor
@Component
@EnableAsync
@Aspect
public class LoggingAspect {
	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

	private final ConfirmationTokenService confirmationTokenRepository;
	
	
	private final UserRepository userRepository;
	
	@Autowired
	IUserManagement userService;
	
	@Autowired
	INotificationUserService notificationUserService;

	@Async
	@Scheduled(cron="0 0 0 * * ?")
	public void toDoNotif() {
		List<User> u = userService.getUsers();
		for (User user : u) {
			List<ToDoList> todo = (List<ToDoList>) user.getTodolist();
			for (ToDoList toDoList : todo) {
				//if(toDoList.getDebutDate().compareTo(LocalDateTime.now().) == 0){
					
				//}
			}
		}
	}
	
	
	@Async
	@Scheduled(cron="0 0 0 * * ?")
	public void fixedRateMethod() {
		confirmationTokenRepository.deleteAllTokens();
	}
	

	
	@Async
	@Scheduled(fixedRate = 30000)
	public void UserReport() {
		List<User> u = userService.getUsers();
		for (User user : u) {
			//logger.info("Method with fixed Rate");
			if(user.getReporting().size()>=2){
				userRepository.lockedAppUser(user.getEmail());
				
			}
			
		}
	}
	
	
}
