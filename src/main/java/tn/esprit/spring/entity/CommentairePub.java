package tn.esprit.spring.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

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
public class CommentairePub implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
//	@NotNull()
//	@NotBlanc

	private String comment ; 
	private String description;
	private String type;
	private String emojis;
	private Long likes;
	private Long dislike;
	
	private String commented_By;
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@JsonIgnore
	@ManyToOne
	Publication publications;
	
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commentairesPub")
    private Set<MotsInterdit> motsInterdits;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commentairesPubs")
    private Set<Likes> likess;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commentairesPubss")
    private Set<Dislike> dislikess;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commentairesPubsss")
    private Set<Emojis> emojiss;
}
