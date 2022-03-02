package tn.esprit.spring.service;

import java.io.Console;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.repository.CagnotteRepository;
import tn.esprit.spring.repository.EvenementRepository;
import tn.esprit.spring.repository.ReservationRepository;

@Service
public class EvenementService implements IEvenementService  {

	
	@Autowired
	ReservationRepository reservationRepository ;
	
	
	@Autowired
	CagnotteRepository cagRepository;
	
	@Autowired
	EvenementRepository eventRepository ;
	
	
	@Autowired
	ICagnotteService cagnotteService ;
	
	@Autowired
	IReservationService reservationService ;
	
	
	
	@Override
	public Evenement addEventonly(Evenement event){
		
		eventRepository.save(event);
		
		return event;
	}
	
	
	@Override
	public Evenement effectuer(Long idevent,Long idres,Long idcag){
		
		Reservation res = reservationService.getres(idres);
		Cagnotte cag =  cagnotteService.getcag(idcag);
		Evenement e =  eventRepository.findById(idevent).orElse(null);

		e.setCagnotte(cag);
		e.setReservation(res);
		eventRepository.save(e);
		
		return e;	
		
	}
	
	@Override
	public Evenement addEvent(Evenement event){
		
		System.out.println("aaaa");
		System.out.println(event.getCagnotte());
		reservationRepository.save(event.getReservation());
		cagRepository.save(event.getCagnotte()) ;
		eventRepository.save(event);
		
		return event;
		
	}
	
	@Override
	public Evenement UpdateEvent(Long  idevent,Evenement event,Reservation res,Date dd , Date df){
		Evenement e =  eventRepository.findById(idevent).orElse(null);
		
		e.setAffiche(event.getAffiche());		
		e.setTitre(event.getTitre());
		e.setLieux(event.getLieux());
		e.setDateEvenement(event.getDateEvenement());
		e.setDescription(event.getDescription());
		e.setReservation(res);
		e.getCagnotte().setDateDebut(dd);
		e.getCagnotte().setDateFin(df);
		
		eventRepository.save(e);
		
		return event;
			
	}
	
	@Override
	public String deleteevent(Long  idevent){
		Evenement e =  eventRepository.findById(idevent).orElse(null);
		
		reservationService.deleteReservation(e.getReservation().getId());
		
		cagnotteService.deletecagnotte(e.getCagnotte().getId());

		eventRepository.delete(e);
		
		return " Event deleted "+ idevent;

	}
	
	@Override
	public Evenement getevent(Long  idevent){
		Evenement e =  eventRepository.findById(idevent).orElse(null);
		
		return e;

	}
	
	@Override
	public List<Evenement> getallevent(Long  idevent){
		List<Evenement> e =  eventRepository.findAll();
		
		return e;

	}
}
