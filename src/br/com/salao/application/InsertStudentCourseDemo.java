package br.com.salao.application;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.salao.entity.Course;
import br.com.salao.entity.Instructor;
import br.com.salao.entity.InstructorDetail;
import br.com.salao.entity.Student;

public class InsertStudentCourseDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Instructor.class)
			.addAnnotatedClass(InstructorDetail.class)
			.addAnnotatedClass(Course.class)
			.addAnnotatedClass(Student.class)
			.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Long courseId = 10L;
			Course c1 = session.get(Course.class, courseId);
			
			Student s1 = new Student("Cristiano", "Carvalho", "cacofut@hotmail.com", LocalDate.of(1987,1,28));
			Student s2 = new Student("Julio", "Jesse", "julio@hotmail.com", LocalDate.of(1988,2,18));
			Student s3 = new Student("Jose", "Alameda", "joset@hotmail.com", LocalDate.of(1989,3,25));
			Student s4 = new Student("Carlos", "Currio", "carlos@hotmail.com", LocalDate.of(1990,4,8));
			
			c1.addStudent(s1);  
			c1.addStudent(s2);
			c1.addStudent(s3);
			c1.addStudent(s4);
			
			session.save(s1);
			session.save(s2);
			session.save(s3);
			session.save(s4);
			
			session.getTransaction().commit();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
}
