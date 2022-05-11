package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.ToDoList;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList,Long>{
	
    @Transactional
    @Modifying
    @Query("UPDATE ToDoList a " + "SET a.progress = ?1 WHERE a.id = ?2")
    void updateProgress(int progress,Long id);
    
	List<ToDoList> findByUserEmail(String email);

}
