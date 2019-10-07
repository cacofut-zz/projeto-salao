package br.com.salao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "instructor")
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;
		
	@OneToMany(mappedBy = "instructor", cascade = {
		CascadeType.DETACH, 
		CascadeType.MERGE, 
		CascadeType.PERSIST, 
		CascadeType.REFRESH
	}, fetch = FetchType.LAZY)
	private List<Course> courses;
	
	public Instructor(String firstName, String lastName) {	
		this(null, firstName, lastName);
	}
	
	public Instructor(Long id, String firstName, String lastName) {	
		this(firstName, lastName, null);		
	}
	
	public Instructor(String firstName, String lastName, 
		InstructorDetail instructorDetail) {	
		this(null, firstName, lastName, instructorDetail, null);			
			
	}
	
	public Instructor(Long id, String firstName, String lastName, 
		InstructorDetail instructorDetail, List<Course> courses) {		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.instructorDetail = instructorDetail;
		this.courses = courses;
	}
	
	
	
	public void addCourse(Course course) {
		if( courses == null ) {
			courses = new ArrayList<>();
		}		
		courses.add(course);
		course.setInstructor(this);
	}	

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}



	
}
