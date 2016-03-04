package edu.nju.courseHomeworkCheck.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.courseHomeworkCheck.dao.BaseDao;
import edu.nju.courseHomeworkCheck.dao.StudentDao;
import edu.nju.courseHomeworkCheck.models.Student;

@Repository
public class StudentDaoImpl implements StudentDao{

	@Autowired
	private BaseDao baseDao;
	
//	private Configuration config;
//	private SessionFactory sessionFactory;
//	private Session session;
//	private static StudentDaoImpl studentDao = new StudentDaoImpl();
//
//	private StudentDaoImpl() {
//		config = new Configuration().configure();
//		config.addAnnotatedClass(Student.class);
//		config.addAnnotatedClass(Course.class);
//		config.addAnnotatedClass(Homework.class);
//		sessionFactory=config.buildSessionFactory();
//	}
//
//	public static StudentDaoImpl getInstance() {
//		return studentDao;
//	}
	
//	private static StudentDaoImpl studentDao = new StudentDaoImpl();
//	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
//	private StudentDaoImpl(){}
//	
//	public static StudentDaoImpl getInstance(){
//		return studentDao;
//	}
	
	public Student findById(String studentid) {
		try{
			return (Student) baseDao.load(Student.class, studentid);
		}catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
		
//		Query query = em.createQuery("from Student s where s.studentid=studentid");
//		return (Student)query.getSingleResult();
//		Student student = em.find(Student.class, studentid);
//		return student;
		
	}

	public void save(Student student) {
		baseDao.save(student);
	}

	public boolean updateStudent(Student student) {
		try{
			baseDao.update(student);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

}
