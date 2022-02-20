package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.IFacebookService;

@RestController
@RequestMapping("/face")
public class FacebookController {
	
	@Autowired
	private IFacebookService facebookService;
	
	@GetMapping
	public List<String> welcome(){
		List<String> urls = new ArrayList<String>();
		urls.add("http://localhost:8089/WomenEmpowerment/face/generateFacebookAuthorizeUrl");
		urls.add("http://localhost:8089/WomenEmpowerment/face/getUserData");
		urls.add("http://localhost:8089/WomenEmpowerment/face/getUserFeed");
		return urls;
		
	}
	
	@GetMapping("/generateFacebookAuthorizeUrl")
	public String generateFacebookAuthorizeUrl(){
		return facebookService.generateFacebookAuthorizeUrl();
	}
	
	
	@GetMapping("/facebook")
	public void generateFacebookAccessToken(@RequestParam("code") String code){
		facebookService.generateFacebookAccessToken(code);
	}
	
	@GetMapping("/getUserData")
	public String getUserData(){
		return facebookService.getUserData();
	}
	
	
	@GetMapping("/getUserFeed")
	public PagedList<Post> getUserFeed(){
		return facebookService.getUserFeed();
	}

}
