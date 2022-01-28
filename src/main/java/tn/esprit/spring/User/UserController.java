package tn.esprit.spring.User;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import lombok.Data;

@RestController
@Api(tags = "User management")
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8089")
public class UserController {
	
	@Autowired
	IUserManagement userService;
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body(userService.getUsers());
		
	}

	


}
