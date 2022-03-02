package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Reporting;

@Repository
public interface CommentairePubRepository extends JpaRepository<CommentairePub, Long>{


	@Query("SELECT COUNT(liked_by) FROM Likes l WHERE l.commentairesPubs= :pub")
	int  egwfwwef( @Param("pub") Long pub);
}
