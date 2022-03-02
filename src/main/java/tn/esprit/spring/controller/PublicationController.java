package tn.esprit.spring.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.sun.istack.logging.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.service.*;
import tn.esprit.spring.User.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import tn.esprit.spring.entity.*;;
@RestController
@RequestMapping("/publication")
public class PublicationController {


	@Autowired
	IPublicationService pubserv;

	@RequestMapping( "/add/{email}")
	

	
	@ResponseBody
	public void ajoutercategorie(@RequestBody  Publication pub,@PathVariable("email") String email )throws IOException {
	
		 pubserv.AjouterPub(pub, email);
	}
		
		// http://localhost:8089/WomenEmpowerment/publication
		@GetMapping("/")
		@ResponseBody
		public ResponseEntity<List<Publication>> getUsers(){
			return ResponseEntity.ok().body(pubserv.getPublications());
			
		}
		
		// http://localhost:8089/WomenEmpowerment/publication/delete/
		@DeleteMapping("/delete/{id}")
		@ResponseBody
		public void deleteUser(@PathVariable("id") Long id){
			pubserv.deletePub(id);
			
		}
		
		
		//http://localhost:8089/WomenEmpowerment/publication/update
		@PutMapping("/update/{email}")
		@ResponseBody
		public void updateUser(@RequestBody Publication pub,@PathVariable("email")String email) {
			pubserv.updatePub(pub, email);
		
		}	
		
		@GetMapping("/search/{keyword}")
		public List<Publication> search(@PathVariable("keyword") String keyword) {
	       return  this.pubserv.search (keyword); 
	    }
		
	}