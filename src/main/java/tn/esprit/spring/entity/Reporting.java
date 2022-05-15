package tn.esprit.spring.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reporting implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String reported_by;
	
	
	@ManyToOne
	User user;
	
	private String reason ;
    @Enumerated(EnumType.STRING)
    private ReportingReason reportingReason;

}
