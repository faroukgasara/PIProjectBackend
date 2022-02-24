package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Reporting;

@Repository
public interface CommentairePubRepository extends JpaRepository<CommentairePub, Long>{

}
