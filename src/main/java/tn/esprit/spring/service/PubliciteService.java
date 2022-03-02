package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Console;

import tn.esprit.spring.entity.Publicite;
import tn.esprit.spring.repository.PopulationCibleRepository;
import tn.esprit.spring.repository.PubliciteRepository;
import tn.esprit.spring.entity.PopulationCible;
@Service
public class PubliciteService implements IPubliciteService{

	@Autowired
	PubliciteRepository pubRepo;
	@Autowired
	PopulationCibleRepository popRepo;
	@Override
	public String addPublicite(Publicite p ) {
		PopulationCible pc = p.getPopulationCible();
		Long idaddedpc = popRepo.save(pc).getId();
		Long idaddeedp = pubRepo.save(p).getId();
		
		
		PopulationCible pcaded= popRepo.findById(idaddedpc).orElse(null);
		Publicite paded = pubRepo.findById(idaddeedp).orElse(null);
		pcaded.setPublicite(paded);
		paded.setPopulationCible(pcaded);
	
		

		
		popRepo.save(pcaded);
		pubRepo.save(paded);
		
		//Publicite PA = pubRepo.findById(p.getId());
		
		return "aa";
	}

	@Override
	public List<Publicite> getAllPublicite() {
		
		return (List<Publicite>)pubRepo.findAll();
	}

	@Override
	public void deletePub(Long id) {
		pubRepo.deleteById(id);
		
	}
	
	
	

}
