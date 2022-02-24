package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.registration.token.ConfirmationToken;
import tn.esprit.spring.repository.CommentairePubRepository;
import tn.esprit.spring.repository.PublicationRepository;
@Service
public class CommentairePubService implements ICommentairePubService {

	@Autowired
	PublicationRepository pubrepo;
	@Autowired
	CommentairePubRepository comrepo;
	@Autowired
	UserRepository userrepo;
	
	
	
	@Override
	public void AjouterCommentare(CommentairePub com, Long idPub, String email) {

		User u = userrepo.findByEmail(email).orElse(null);
		 boolean userExists = userrepo
	                .findByEmail(u.getEmail())
	                .isPresent();
		 
		
		  if (userExists) {
			  com.setCreatedAt(LocalDateTime.now());
		Publication p=pubrepo.findById(idPub).orElse(null);
		CommentairePub C=comrepo.save(com);
		
		C.setCreatedAt(LocalDateTime.now());
		C.setPublications(p);
		C.setCommented_By(email);
		comrepo.save(C);
		
		
	}else{
		throw new IllegalStateException("email already taken");
	}}
	
	 
	



	@Override
	public List<CommentairePub> getCommentairePub() {
		return comrepo.findAll();
	}



	@Override
	public void deleteCom(Long id) {
		CommentairePub p = comrepo.findById(id).orElse(null);
		comrepo.delete(p);
		}



	@Override
	public void updateCom(CommentairePub comP) {
		comrepo.save(comP);
	}
	



	




}
