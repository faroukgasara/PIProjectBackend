package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookService implements IFacebookService{
	
	private String accessToken;

	@Value("${spring.social.facebook.app-id}")
	private String facebookAppId;
	
	@Value("${spring.social.facebook.app-secret}")
	private String facebookSecret;
	
	
	private FacebookConnectionFactory createConnection(){
		return new FacebookConnectionFactory(facebookAppId,facebookSecret);
	}
	
	@Override
	public String generateFacebookAuthorizeUrl() {
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://localhost:8089/WomenEmpowerment/face/facebook");
		params.setScope("email");
		return createConnection().getOAuthOperations().buildAuthenticateUrl(params);
	}

	@Override
	public void generateFacebookAccessToken(String code) {
		accessToken = createConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:8089/WomenEmpowerment/face/facebook", null).getAccessToken();
		
	}

	@Override
	public String getUserData() {
		Facebook facebook = new FacebookTemplate(accessToken);
		String[] fields = {"id","first_name","name"};
		return facebook.fetchObject("me", String.class,fields);
	}

	@Override
	public PagedList<Post> getUserFeed() {
		// TODO Auto-generated method stub
		return new FacebookTemplate(accessToken).feedOperations().getFeed();
	}

}
