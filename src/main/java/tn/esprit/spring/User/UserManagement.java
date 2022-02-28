package tn.esprit.spring.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tn.esprit.spring.entity.FacebookData;
import tn.esprit.spring.entity.UserEmotions;
import tn.esprit.spring.repository.UserEmotionsRepository;


@Service
public class UserManagement implements IUserManagement{
	
	@Autowired
	private UserRepository myRepository;
	
	@Autowired
	private UserEmotionsRepository userEmotionsRepository;

	@Override
	public List<User> getUsers() {
		return myRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
	}

	@Override
	public void deleteUser(String email) {
		User u = myRepository.findByEmail(email).orElse(null);
		myRepository.delete(u);
	}

	@Override
	public void updateUser(User user) {
			 myRepository.save(user);
	}

	@Override
	public List<User> findByFirstNameContains(String firstName) {
		return myRepository.findByFirstNameContains(firstName);
	}

	@Override
	public List<User> findByFirstNameOrLastNameContains(String firstName,String lastName) {
		
		return myRepository.findByFirstNameOrLastNameContains(firstName, lastName);
	}

	@Override
	public List<User> findByLocked(Boolean locked) {
		return myRepository.findByLocked(locked);
	}

	@Override
	public int unlockedAppUser(String email) {
		return myRepository.unlockedAppUser(email);
	}

	@Override
	public int lockedAppUser(String email) {
		return myRepository.lockedAppUser(email);
	}

	@Override
	public List<Object[]> countTotalUsersByYear() {
		return myRepository.countTotalUsersByYear();
	}

	
	
	static int countOccurrences(String str, String word)
	{
	    String a[] = str.split(" ");
	 
	    int count = 0;
	    for (int i = 0; i < a.length; i++)
	    {
	    if (word.equals(a[i]))
	        count++;
	    }
	 
	    return count;
	}
	
	String[] sadness = {"sad","sadness","deplorable","distressing","lamentable","pitiful","sorry","sadness","bittersweet","doleful","heavyhearted", "melancholic","melancholy",
			"mournful","pensive","tragic", "tragical","tragicomic","tragicomical","wistful"}; 
	String[] happiness = {"happiness","felicitous","cheerful","content","contented","elated","euphoric","glad","joyful","joyous","blessed","prosperous","halcyon",
	        "riant","laughing","blissful","bright","golden"}; 
	String[] suicide = {"self-annihilation","self-destruction","killing","death","kill","suicidal","slayer","killer"}; 
	String[] violence = {"ferocity","killing","death","kill","fierceness","furiousness","fury","vehemence","wildness","savagery","savageness"}; 
	String[] fear ={"mournful"};
	String[] anger ={"mournful"};
	@Override
	public Map<String, Float> UserPrediction(String email) {
		int nbgood = 0;
		int nbbad = 0;
		int nbsuicide = 0;
		int nbviolence = 0;
		int all =0;
		Map<String, Float> mapOfLists = new HashMap<String, Float>();
		User u = myRepository.findByEmail(email).orElse(null);
		Set<FacebookData> facebookData = u.getFacebookData();
		
		for (FacebookData facebookData2 : facebookData) {
			String[] words = facebookData2.getMessage().split("\\s+");
			all = all+words.length;
		}
		for (FacebookData facebookData2 : facebookData) {
			for(String str : sadness){
				nbbad = nbbad+countOccurrences(facebookData2.getMessage(),str);
				//System.out.println(nbbad); 
			}
			
			
			for(String str : happiness){
				nbgood = nbgood+countOccurrences(facebookData2.getMessage(),str);
				//System.out.println(nbgood); 
			}
			
			
			for(String str : suicide){
				nbsuicide = nbsuicide+countOccurrences(facebookData2.getMessage(),str);
				//System.out.println(nbgood); 
			}
			for(String str : violence){
				nbviolence = nbviolence+countOccurrences(facebookData2.getMessage(),str);
				//System.out.println(nbgood); 
			}
			
		}
		UserEmotions UE = new UserEmotions();
		UE.setUser(u);
		UE.setHappy(nbgood*100/all);
		UE.setSad(nbbad*100/all);
		UE.setViolence(nbviolence*100/all);
		UE.setSuicide(nbsuicide*100/all);
		
		
		
		userEmotionsRepository.save(UE);
		mapOfLists.put("Sad",(float) nbbad*100/all);
		mapOfLists.put("Happy", (float) nbgood*100/all);
		mapOfLists.put("Suicide", (float) nbsuicide*100/all);
		mapOfLists.put("Violence", (float) nbviolence*100/all);

		
		return mapOfLists;
	}

}
