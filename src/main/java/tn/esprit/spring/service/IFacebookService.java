package tn.esprit.spring.service;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.web.bind.annotation.PathVariable;

public interface IFacebookService {
	public String generateFacebookAuthorizeUrl();
	public void generateFacebookAccessToken(String code);
	public String getUserData();
	public PagedList<Post> getUserFeed();

}
