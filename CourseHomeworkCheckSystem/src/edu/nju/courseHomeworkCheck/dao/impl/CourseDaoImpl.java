package edu.nju.courseHomeworkCheck.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.courseHomeworkCheck.dao.BaseDao;
import edu.nju.courseHomeworkCheck.dao.CourseDao;
import edu.nju.courseHomeworkCheck.models.Course;

@Repository
public class CourseDaoImpl implements CourseDao{
	
	@Autowired
	private BaseDao baseDao;

//	private Configuration config;
//	private SessionFactory sessionFactory;
//	private Session session;
//	private static CourseDaoImpl courseDao = new CourseDaoImpl();

//	private CourseDaoImpl() {
//		config = new Configuration().configure();
//		config.addAnnotatedClass(Course.class);
//		config.addAnnotatedClass(Student.class);
//		config.addAnnotatedClass(Homework.class);
//		sessionFactory=config.buildSessionFactory();
//	}

//	public static CourseDaoImpl getInstance() {
//		return courseDao;
//	}
	
	public Course findById(int courseid) {
		try{
			return (Course) baseDao.load(Course.class, String.valueOf(courseid));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
//		try{
//			session = sessionFactory.openSession();
//			String hql = "from Course c where c.courseid=?";
//			Query query = session.createQuery(hql);
//			query.setInteger(0, courseid);
//			List list = query.list();
//			session.close();
//			sessionFactory.close();
//			if(list!=null){
//				return (Course)list.get(0);
//			}
//			else{
//				return null;
//			}
//		}catch (Exception e) {			
//			e.printStackTrace();
//		}
//		return null;
//		return em.find(Course.class, courseid);
	}

	@SuppressWarnings("rawtypes")
	public List findByStudent(String studentid) {
		try{
			Session session = baseDao.getNewSession();
			String hql = "select s.courses from Student s where s.studentid=?";
			Query query = session.createQuery(hql);
			query.setString(0, studentid);
			List list = query.list();
			session.close();
//			sessionFactory.close();
			return list;
		}catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
		
//		Query query = em.createQuery("select s.courses from Student s "
//				+ "where s.studentid=?1");
//		query.setParameter(1, studentid);
//		list = query.getResultList();
//		em.clear();
//		return list;
	}

	public boolean updateCourse(Course course) {
		try{
			baseDao.update(course);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
//		try{
//			session=sessionFactory.openSession();
//			Transaction tx=session.beginTransaction();
//			session.update(course);
//			tx.commit();
//			session.close();
////			sessionFactory.close();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
		
//		try{
//			em.merge(course);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
//		return true;
	}

}
