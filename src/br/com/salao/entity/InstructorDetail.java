package br.com.salao.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@Column(name = "hobby")
	private String hobby;
	
	@Column(name = "web_site")
	private String webSite;
	   
	@OneToOne(cascade = {
		CascadeType.DETACH, 
		CascadeType.MERGE, 
		CascadeType.PERSIST, 
		CascadeType.REFRESH
	}, mappedBy = "instructorDetail")
	private Instructor instructor;

	public InstructorDetail(LocalDate birthDate, String hobby, String webSite) {	
		this.birthDate = birthDate;
		this.hobby = hobby;
		this.webSite = webSite;
	}
	
	
	
}
