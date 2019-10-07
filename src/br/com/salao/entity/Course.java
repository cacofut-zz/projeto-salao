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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@ManyToOne(cascade = {
		CascadeType.DETACH, 
		CascadeType.MERGE, 
		CascadeType.PERSIST, 
		CascadeType.REFRESH
	})
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {
		CascadeType.DETACH,
		CascadeType.MERGE,
		CascadeType.PERSIST,
		CascadeType.REFRESH
	})
	@JoinTable(
		name = "student_course",
		joinColumns = @JoinColumn(name = "course_id"),
		inverseJoinColumns = @JoinColumn(name = "student_id")
	)
	private List<Student> students;
	
	public Course(String title) {	
		this.title = title;
	}
	
	public void addStudent(Student student) {
		if( students == null ) {
			students = new ArrayList<>();
		}
		students.add(student);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
		
	
	
	
}
