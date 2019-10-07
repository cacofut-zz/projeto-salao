package br.com.salao.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.salao.entity.Course;
import br.com.salao.entity.Instructor;
import br.com.salao.entity.InstructorDetail;
import br.com.salao.entity.Student;

public class DeletingCoursesStudentDemo {
	
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
			
			Long idCourse = 10L;
			Course c1 = session.get(Course.class, idCourse);
			
			System.out.print("Course deleting: ");
			System.out.println(c1);					
			session.delete(c1);
			System.out.println("Course deleted");
			
				
			session.getTransaction().commit();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
}
