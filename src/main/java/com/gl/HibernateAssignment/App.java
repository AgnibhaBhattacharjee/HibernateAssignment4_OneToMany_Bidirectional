package com.gl.HibernateAssignment;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import config.HibernateConfig;
import entity.Course;
import entity.Student;


/**
 * Hello world!
 *
 */
public class App 
{private static SessionFactory factory = HibernateConfig.getSessionFactory();
    public static void main( String[] args )
    {
       
		System.out.println("config loaded");
		Session session= factory.openSession();
		
		//Create Student Object
		Student student= new Student();
		student.setName("Ram");
		student.setAge(20);
		
		Student s1= new Student();
		s1.setName("Shyam");
		s1.setAge(18);
		
		//Create Course object
		List<Course> courses= new ArrayList<Course>();
		
		Course course1= new Course();
		course1.setCourseName("Java Spring Boot Developement Course");
		//course1.setStudent(student);
		course1.setStudent(s1);
		Course course2= new Course();
		course2.setCourseName("React JS Developement Course");
		//course2.setStudent(student);
		course2.setStudent(s1);
		
		courses.add(course1);
		courses.add(course2);
		
		//Setting Courses of student
		//student.setCourses(courses);
		s1.setCourses(courses);
		
		
		
		//Insert data into the Student along with the data in the courses 
		Transaction tx= session.beginTransaction();
		//session.persist(student);
		
		//Saving the 2nd student object
		//session.persist(s1);
		
		
		//Fetching the student details along with courses
		Student student2= session.get(Student.class, 1);
		System.out.println(student2.toString());
		System.out.println(student2.getCourses());
		System.out.println("*******************************************");
		//Fetching the student details with having a particular course
		Course c1= session.get(Course.class, 6);
		System.out.println(c1.toString());
		System.out.println(c1.getStudent());
		
		

		
		
		//Removing a Course record from the database without affecting Students.
		session.remove(c1);
		
		
		tx.commit();
		session.close();
		
		
		
    }
   
}
