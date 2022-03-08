package tn.esprit.spring.registration;

import lombok.AllArgsConstructor;
import tn.esprit.spring.User.User;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // http://localhost:8089/WomenEmpowerment/registration
    @PostMapping
    public String register(@RequestBody User request) {
    	
        return registrationService.register(request);
    }
    
    // http://localhost:8089/WomenEmpowerment/registration/confirm
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
    
    
    // http://localhost:8089/WomenEmpowerment/registration/forgetpassword
    @GetMapping("/forgetpassword/{email}")
    public String forgetpassword(@PathVariable("email") String email) {
        return registrationService.forgetpassword(email);
    }
    
    
    // http://localhost:8089/WomenEmpowerment/registration/reset
    @GetMapping("/reset/{token}/{email}/{password}")
    public String reset(@PathVariable("token") String token,@PathVariable("email") String email,@PathVariable("password") String password) {
        return registrationService.resetPassword(token,email,password);
    }


}
