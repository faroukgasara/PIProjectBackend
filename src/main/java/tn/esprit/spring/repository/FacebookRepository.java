package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.FacebookData;
import tn.esprit.spring.entity.NotificationUser;

@Repository
public interface FacebookRepository extends CrudRepository<FacebookData,Long>{
	
	List<FacebookData> findByUserEmailContains(String email);

}
