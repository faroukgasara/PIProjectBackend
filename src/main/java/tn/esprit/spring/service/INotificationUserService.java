package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.List;

import tn.esprit.spring.entity.NotificationUser;

public interface INotificationUserService {

	public void sendGlobalNotification(String description);
	public void sendPrivateNotification(String description,String email);
	void readNotificationUser(Long id);
	public void deleteNotificationUser(Long id);
	List<NotificationUser> findByUserEmailContains(String email);
	
}
