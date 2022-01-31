package tn.esprit.spring.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRole;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Expert extends User{
	
	private static final long serialVersionUID = 1L;
	
	private String specialty;
	
	public Expert(String firstName, String lastName, String email, String password, UserRole appUserRole,
			String specialty) {
		super(firstName, lastName, email, password, appUserRole);
		this.specialty = specialty;
	}
	

}
