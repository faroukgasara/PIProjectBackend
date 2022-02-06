package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.User.User;
import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Commentaire implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String commentaire;
	private String description;
	private String type;
	private String emojis;
	private Long like;
	private Long dislike;
	
	@JsonIgnore
	@ManyToOne
	Publication publications;
	
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commentaires")
    private Set<MotsInterdit> motsInterdits;
}
