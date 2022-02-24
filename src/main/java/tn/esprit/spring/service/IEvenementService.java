package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Reservation;

public interface IEvenementService {


	Evenement addEvent(Evenement event, Reservation res);

	Evenement UpdateEvent(Long idevent, Evenement event, Reservation res, Date dd, Date df);

	String deleteevent(Long idevent);

	Evenement getevent(Long idevent);

	List<Evenement> getallevent(Long idevent);

	Evenement addEventonly(Evenement event);

	Evenement effectuer(Long idevent, Long idres, Long idcag);
	

}
