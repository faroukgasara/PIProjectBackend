package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Reporting;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long>{
	@Query("SELECT f FROM Publication f WHERE f.title LIKE %?1%" //to search
            + " OR f.description LIKE %?1%"
            + " OR f.type LIKE %?1%"
            +"OR f.users Like %?1% ")
            //+ " OR CONCAT(p.price, '') LIKE %?1%")
    public List<Publication> search(String keyword);

}
