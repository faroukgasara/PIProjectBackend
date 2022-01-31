package tn.esprit.spring.registration;

import lombok.AllArgsConstructor;
import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.Expert;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody Expert request) {
    	
        return registrationService.register(request);
    }
    
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }


}
