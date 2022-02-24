package tn.esprit.spring.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.service.IEvenementService;

@RestController
@Api(tags = "Event management")
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:8089")
public class evenementController {
	
	
	@Autowired
	IEvenementService EventService;
	
	
	// http://localhost:8089/addevent/dd/df/
	@PostMapping("/addevent")
	@ResponseBody
	public Evenement addEvent(@RequestBody Evenement event,@RequestBody Reservation res)
	{
		//@RequestBody Cagnotte cag
		return	EventService.addEvent(event,res);
		
	}
	
	
	// http://localhost:8089/addeventon//
		@PostMapping("/addeventonly")
		@ResponseBody
		public Evenement addEventonly(@RequestBody Evenement event)
		{
			return	EventService.addEventonly(event);
			
		}
		
		// http://localhost:8089/addeventon//
				@PostMapping("/effectuerevent/{idevent}/{idres}/{idcag}")
				@ResponseBody
				public Evenement effectuerevent(@PathVariable("idevent") Long idevent,@PathVariable("idres") Long idres,@PathVariable("idcag") Long idcag)
				{
					return	EventService.effectuer(idevent,idres,idcag);
					
				}


}
