package tn.esprit.spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Don;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.repository.CagnotteRepository;
import tn.esprit.spring.repository.DonRepository;
import tn.esprit.spring.repository.EvenementRepository;

@Service
public class DonService implements IDonService {

	@Autowired
	CagnotteRepository cagRepository;
	
	@Autowired
	EvenementRepository eventRepository;
	
	@Autowired 
	DonRepository donRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
    public Don addDon(Long  idcag, float montant,Date datedon, String email){
		
		User user = userRepository.findByEmail(email).orElse(null);
		Don don = new Don();
		don.setDateDon(datedon);
		don.setMontant(montant);
		don.setUser(user);
		
		
		Cagnotte e =  cagRepository.findById(idcag).orElse(null);
		don.setCagnotte(e);
		donRepository.save(don);
		return don;
		
		
		/*private Long id;
	      private float montant;
	      private Date dateDon;*/
	}
}
