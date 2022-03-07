package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //(strategy = GenerationType.AUTO) 
	private long idAnswer;
	
	//@Size(min=1, max=20)
	private String text;
	
	@ManyToOne
	@JsonIgnore
	private Question question;

	@JsonIgnore
	@NotNull
	private Boolean iscorrect;
	
	//@ManyToMany(mappedBy="answers", cascade = CascadeType.ALL)  
	//private Set<Question> questions;
}
