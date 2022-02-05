package tn.esprit.spring.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entity.Reporting;
import tn.esprit.spring.entity.Subscriber;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails,Serializable {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String picture ;
    private String adress ;
    private String companyName ;
    private String numTel ;
    private String associationName ;
    private String description ;
    private String CIN ;
    private String availability ;
    private int age ;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Reporting> reporting;
    
    @JsonIgnore
    @OneToOne
    private Subscriber subscriber;

    
	public User(Long id, String firstName, String lastName, String email, String password, UserRole appUserRole,
			String picture, String adress, String companyName, String numTel,
			String associationName, String description, String cIN, String availability, int age,
			Subscriber subscriber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
		this.picture = picture;
		this.adress = adress;
		this.companyName = companyName;
		this.numTel = numTel;
		this.associationName = associationName;
		this.description = description;
		CIN = cIN;
		this.availability = availability;
		this.age = age;
		this.subscriber = subscriber;
	}
	
	public User(String firstName, String lastName, String email, String password, UserRole appUserRole,
			String picture, String adress, String companyName, String numTel,
			String associationName, String description, String cIN, String availability, int age,
			Subscriber subscriber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
		this.picture = picture;
		this.adress = adress;
		this.companyName = companyName;
		this.numTel = numTel;
		this.associationName = associationName;
		this.description = description;
		CIN = cIN;
		this.availability = availability;
		this.age = age;
		this.subscriber = subscriber;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
