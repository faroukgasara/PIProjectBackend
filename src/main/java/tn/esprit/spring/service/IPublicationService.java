package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.Publication;

public interface IPublicationService {
	public void AjouterPub(Publication pub ,String email) ;
	public List<Publication> getPublications();
	public void deletePub(Long id);
	public void updatePub(Publication pub , String email);
	List<Publication> search(String keyword);

}
