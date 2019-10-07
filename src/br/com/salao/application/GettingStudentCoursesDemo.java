package br.com.salao.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.salao.entity.Course;
import br.com.salao.entity.Instructor;
import br.com.salao.entity.InstructorDetail;
import br.com.salao.entity.Student;

public class GettingStudentCoursesDemo {
	
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
			
			Long idStudent = 1L;
			Student s1 = session.get(Student.class, idStudent);
			
			System.out.print("Student: ");
			System.out.println(s1);
			
			System.out.println("Student's courses");
			System.out.println(s1.getCourses());
			

		
			
			session.getTransaction().commit();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
}
