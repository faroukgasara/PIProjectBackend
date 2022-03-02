package tn.esprit.spring.configuration;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.registration.token.ConfirmationTokenRepository;
import tn.esprit.spring.registration.token.ConfirmationTokenService;
import tn.esprit.spring.repository.PublicationRepository;
import tn.esprit.spring.service.IPublicationService;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@AllArgsConstructor
@Component
@EnableAsync
@Aspect
public class LoggingAspect {
	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

	private final ConfirmationTokenService confirmationTokenRepository;
	private final PublicationRepository pubrepo;
	@Async
	@Scheduled(cron="0 0 0 * * ?")
	public void fixedRateMethod() {
		confirmationTokenRepository.deleteAllTokens();
	}
	@Autowired
	IPublicationService pubserv;
	
	@Async
	@Scheduled(fixedRate=5000000)
	public void deletePub() {
		List<Publication> p= pubserv.getPublications();
		for (Publication publication : p) {
			if(publication.getCommentairesPub().size()==0){
				
				pubserv.deletePub(publication.getId());
			}
			
		}
		
	
	}
	@Async
	@AfterReturning("execution(* tn.esprit.spring.service.PublicationService.AjouterPub(..))")
	public void deletePub1() {
		List<Publication> p1= pubrepo.findAll();
		List<String> list1 = new ArrayList<String>(); 
	     list1.add("xxx"); 
	     list1.add("xx"); 
	     list1.add("x");
		List<Publication> p= pubserv.getPublications();
		for (Publication publication : p) {
			if(list1.contains(publication.getTitle())){
				System.out.println("mots Interdit title");
				
				pubserv.deletePub(publication.getId());
				
			}
			else if (list1.contains(publication.getDescription())){
				System.out.println("mots Interdit description");
				
				pubserv.deletePub(publication.getId());
				
				
			}
			
		}
	
	}
	
	
	
}
