package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.service.ICommentairePubService;
import tn.esprit.spring.service.IPublicationService;

@RestController
@RequestMapping("/commentaire")
public class CommentairePubController {
	@Autowired
	ICommentairePubService comserv;
	@PostMapping("/add/{idPub}/{email}")
	@ResponseBody
	public void ajoutercategorie(@ RequestBody  CommentairePub com,@PathVariable("idPub") Long idPub,@PathVariable("email") String email){
		comserv.AjouterCommentare(com, idPub, email);
	}

	// http://localhost:8089/WomenEmpowerment/commentaire
	@GetMapping("/")
	@ResponseBody
	public ResponseEntity<List<CommentairePub>> getUsers(){
		return ResponseEntity.ok().body(comserv.getCommentairePub());
		
	}
	
	// http://localhost:8089/WomenEmpowerment/publication/delete/
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable("id") Long id){
		comserv.deleteCom(id);
		
	}
	
	
	//http://localhost:8089/WomenEmpowerment/publication/update
	@PutMapping("/update")
	@ResponseBody
	public void updateUser(@RequestBody CommentairePub comP) {
		comserv.updateCom(comP);
	
	}	

}

