package edu.nju.courseHomeworkCheck.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.courseHomeworkCheck.dao.BaseDao;
import edu.nju.courseHomeworkCheck.dao.HomeworkDao;
import edu.nju.courseHomeworkCheck.models.Course;
import edu.nju.courseHomeworkCheck.models.Homework;

@Repository
public class HomeworkDaoImpl implements HomeworkDao{

	@Autowired
	private BaseDao baseDao;
	private Session session;
	
//	private Configuration config;
//	private SessionFactory sessionFactory;
//	private Session session;
//	private static HomeworkDaoImpl homeworkDao = new HomeworkDaoImpl();
//
//	private HomeworkDaoImpl() {
//		config = new Configuration().configure();
//		config.addAnnotatedClass(Homework.class);
//		config.addAnnotatedClass(Course.class);
//		config.addAnnotatedClass(Student.class);
//		config.addAnnotatedClass(HomeworkUpload.class);
//		config.addAnnotatedClass(HomeworkGrade.class);
//		sessionFactory=config.buildSessionFactory();
//	}
//
//	public static HomeworkDaoImpl getInstance() {
//		return homeworkDao;
//	}
//	private static HomeworkDaoImpl homeworkDao = new HomeworkDaoImpl();
//	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
//	private HomeworkDaoImpl(){}
//	
//	public static HomeworkDaoImpl getInstance(){
//		return homeworkDao;
//	}

	@SuppressWarnings("rawtypes")
	public List findByCourse(int courseid){
		try{
			session = baseDao.getNewSession();
			String hql = "select c.homeworks from Course c where c.courseid=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, courseid);
			List list = query.list();
			session.close();
//			sessionFactory.close();
			return list;
		}catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
		
//		Query query = em.createQuery("select c.homeworks from Course c "
//				+ "where c.courseid=?1 ");
//		query.setParameter(1, courseid);
//		List list = query.getResultList();
//		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findByStudent(String studentid){
		List listCourse = null;
		List list = new ArrayList();
		try{
			session = baseDao.getNewSession();
			String hql = "select s.courses from Student s where s.studentid=?";
			Query query = session.createQuery(hql);
			query.setString(0, studentid);
			listCourse = query.list();
			for(int i=0;i<listCourse.size();i++){
				Course c = (Course) listCourse.get(i);
				if(c!=null){
					List l = findByCourse(c.getCourseid());
					if(l!=null){
						list.addAll(l);
					}
				}
			}
//			session.close();
//			sessionFactory.close();
		}catch (Exception e) {			
			e.printStackTrace();
		}
		return list;
//		List list = new ArrayList();
//		List listCourse = null;
//		Query query = em.createQuery("select s.courses from Student s "
//			+ "where s.studentid=?1");
//		query.setParameter(1, studentid);
//		listCourse = query.getResultList();
//		for(int i=0;i<listCourse.size();i++){
//			Course c = (Course)listCourse.get(i);
//			if(c!=null){
//				List l = findByCourse(c.getCourseid());
//				if(l!=null){
//					list.addAll(l);
//				}
//			}
//		}
//		em.clear();
//		return list;
	}
	
	public Homework findById(int homeworkId){
		try{
			return (Homework) baseDao.load(Homework.class, String.valueOf(homeworkId));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
//		try{
//			session = sessionFactory.openSession();
//			String hql = "from Homework h where h.homeworkid=?";
//			Query query = session.createQuery(hql);
//			query.setInteger(0, homeworkId);
//			List homeworks = query.list();
//			session.close();
////			sessionFactory.close();
//			if(homeworks!=null){
//				return (Homework)homeworks.get(0);
//			}
//			else{
//				return null;
//			}
//		}catch (Exception e) {			
//			e.printStackTrace();
//		}
//		return null;
//		Homework homework = em.find(Homework.class, homeworkId);
//		return homework;
	}
	
	public boolean updateHomework(Homework homework){
		try{
			session=baseDao.getNewSession();
			Transaction tx=session.beginTransaction();
			session.update(homework);
			tx.commit();
			session.close();
//			sessionFactory.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
//		try{
//			em.merge(homework);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
//		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public List findUploadConditionByStudent(String studentid){
		try{
			session = baseDao.getNewSession();
			String hql = "from HomeworkUpload hu where hu.student.studentid=?";
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
		
//		List list = null;
//		Query query = em.createQuery("select hu from HomeworkUpload hu "
//			+ "where hu.student.studentid=?1");
//		query.setParameter(1, studentid);
//		list = query.getResultList();
//		em.clear();
//		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List findGradeByStudent(String studentid){
		try{
			session = baseDao.getNewSession();
			String hql = "from HomeworkGrade hg where hg.student.studentid=?";
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
//		Query query = em.createQuery("select hg from HomeworkGrade hg "
//				+ "where hg.student.studentid =?1");
//		query.setParameter(1, studentid);
//		List list = query.getResultList();
//		em.clear();
//		return list;
	}
	
//	public List findByStudent(String studentid) {
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
//		ArrayList<HomeworkExt> list=new ArrayList<HomeworkExt>();
//		try 
//		{
//			stmt=con.prepareStatement("select hw.homeworkid,"
//					+ "hw.courseid,c.courseName,c.courseTerm,"
//					+ "hw.homeworktitle,hw.homeworkContent,hw.dueTime "
//					+ "from homework hw,course c,courseselection cs "
//					+ "WHERE hw.courseid=c.courseid "
//					+ "AND hw.courseid=cs.courseid "
//					+ "AND cs.studentid=?");
//			stmt.setString(1, studentid);
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				HomeworkExt homework = new HomeworkExt();
//				homework.setHomeworkid(result.getInt(1));
//				homework.setStudentid(studentid);
//				Course course = new Course();
//				course.setCourseid(result.getInt(2));
//				course.setCourseName(result.getString(3));
//				course.setCourseTerm(result.getString(4));
//				homework.setCourse(course);
//				homework.setHomeworkTitle(result.getString(5));
//				homework.setHomeworkContent(result.getString(6));
//				homework.setDueTime(result.getString(7));
//				list.add(homework);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally
//		{
//			daoHelper.closeConnection(con);
//			daoHelper.closePreparedStatement(stmt);
//			daoHelper.closeResult(result);
//		}
//		return list;
//	}
	
//	public List findUploadConditionByStudent(String studentid){
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
//		ArrayList<HomeworkExt> list=new ArrayList<HomeworkExt>();
//		try 
//		{
//			stmt=con.prepareStatement("SELECT t.homeworkid,"
//					+ "t.courseid,t.courseName,t.courseTerm,"
//					+ "t.homeworktitle,t.homeworkContent,t.dueTime,hu.uploadTime "
//					+ "FROM "
//						+ "(SELECT cs.studentid,h.homeworkid,"
//						+ "c.courseid,c.courseName,c.courseTerm,"
//						+ "h.homeworktitle,h.homeworkContent,h.dueTime "
//						+ "FROM homework h,course c,courseselection cs "
//						+ "WHERE cs.studentid=? "
//						+ "AND h.courseid=c.courseid "
//						+ "AND cs.courseid=c.courseid) t "
//					+ "LEFT JOIN homeworkupload hu "
//					+ "ON t.studentid=hu.studentid AND t.homeworkid=hu.homeworkid");
//			stmt.setString(1, studentid);
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				HomeworkExt homework = new HomeworkExt();
//				homework.setHomeworkid(result.getInt(1));
//				homework.setStudentid(studentid);
//				Course course = new Course();
//				course.setCourseid(result.getInt(2));
//				course.setCourseName(result.getString(3));
//				course.setCourseTerm(result.getString(4));
//				homework.setCourse(course);
//				homework.setHomeworkTitle(result.getString(5));
//				homework.setHomeworkContent(result.getString(6));
//				homework.setDueTime(result.getString(7));
//				homework.setUploadTime(result.getString(8));
//				list.add(homework);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally
//		{
//			daoHelper.closeConnection(con);
//			daoHelper.closePreparedStatement(stmt);
//			daoHelper.closeResult(result);
//		}
//		return list;
//	}

//	public List findUnUploadByStudent(String studentid){
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
//		ArrayList<HomeworkExt> list=new ArrayList<HomeworkExt>();
//		try 
//		{
//			stmt=con.prepareStatement("SELECT t.homeworkid,"
//					+ "t.courseid,t.courseName,t.courseTerm,"
//					+ "t.homeworktitle,t.homeworkContent,t.dueTime,hu.uploadTime "
//					+ "FROM "
//						+ "(SELECT cs.studentid,h.homeworkid,"
//						+ "c.courseid,c.courseName,c.courseTerm,"
//						+ "h.homeworktitle,h.homeworkContent,h.dueTime "
//						+ "FROM homework h,course c,courseselection cs "
//						+ "WHERE cs.studentid=? "
//						+ "AND h.courseid=c.courseid "
//						+ "AND cs.courseid=c.courseid) t "
//					+ "LEFT JOIN homeworkupload hu "
//					+ "ON t.studentid=hu.studentid AND t.homeworkid=hu.homeworkid "
//					+ "WHERE t.dueTime>CURRENT_TIMESTAMP() "
//					+ "AND ISNULL(hu.uploadTime)");
//			stmt.setString(1, studentid);
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				HomeworkExt homework = new HomeworkExt();
//				homework.setHomeworkid(result.getInt(1));
//				homework.setStudentid(studentid);
//				Course course = new Course();
//				course.setCourseid(result.getInt(2));
//				course.setCourseName(result.getString(3));
//				course.setCourseTerm(result.getString(4));
//				homework.setCourse(course);
//				homework.setHomeworkTitle(result.getString(5));
//				homework.setHomeworkContent(result.getString(6));
//				homework.setDueTime(result.getString(7));
//				homework.setUploadTime(result.getString(8));
//				list.add(homework);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally
//		{
//			daoHelper.closeConnection(con);
//			daoHelper.closePreparedStatement(stmt);
//			daoHelper.closeResult(result);
//		}
//		return list;
//	}
	
//	public List findGradeByStudent(String studentid){
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
//		ArrayList<HomeworkExt> list=new ArrayList<HomeworkExt>();
//		try 
//		{
//			stmt=con.prepareStatement("SELECT h.homeworkid,"
//					+ "c.courseid,c.courseName,c.courseTerm,"
//					+ "h.homeworktitle,h.homeworkContent,hg.grade "
//					+ "FROM homeworkgrade hg,homework h,course c "
//					+ "WHERE hg.studentid=? "
//					+ "AND hg.homeworkid=h.homeworkid "
//					+ "AND h.courseid=c.courseid");
//			stmt.setString(1, studentid);
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				HomeworkExt homework = new HomeworkExt();
//				homework.setHomeworkid(result.getInt(1));
//				homework.setStudentid(studentid);
//				Course course = new Course();
//				course.setCourseid(result.getInt(2));
//				course.setCourseName(result.getString(3));
//				course.setCourseTerm(result.getString(4));
//				homework.setCourse(course);
//				homework.setHomeworkTitle(result.getString(5));
//				homework.setHomeworkContent(result.getString(6));
//				homework.setGrade(result.getInt(7));
//				list.add(homework);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally
//		{
//			daoHelper.closeConnection(con);
//			daoHelper.closePreparedStatement(stmt);
//			daoHelper.closeResult(result);
//		}
//		return list;
//	}

//	public List findFailedGradeByStudent(String studentid){
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
//		ArrayList<HomeworkExt> list=new ArrayList<HomeworkExt>();
//		try 
//		{
//			stmt=con.prepareStatement("SELECT h.homeworkid,"
//					+ "c.courseid,c.courseName,c.courseTerm,"
//					+ "h.homeworktitle,h.homeworkContent,hg.grade "
//					+ "FROM homeworkgrade hg,homework h,course c "
//					+ "WHERE hg.studentid=? "
//					+ "AND hg.homeworkid=h.homeworkid "
//					+ "AND h.courseid=c.courseid "
//					+ "AND hg.grade<60");
//			stmt.setString(1, studentid);
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				HomeworkExt homework = new HomeworkExt();
//				homework.setHomeworkid(result.getInt(1));
//				homework.setStudentid(studentid);
//				Course course = new Course();
//				course.setCourseid(result.getInt(2));
//				course.setCourseName(result.getString(3));
//				course.setCourseTerm(result.getString(4));
//				homework.setCourse(course);
//				homework.setHomeworkTitle(result.getString(5));
//				homework.setHomeworkContent(result.getString(6));
//				homework.setGrade(result.getInt(7));
//				list.add(homework);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally
//		{
//			daoHelper.closeConnection(con);
//			daoHelper.closePreparedStatement(stmt);
//			daoHelper.closeResult(result);
//		}
//		return list;
//	}
	
//	public List findByCourse(int courseid){
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
//		ArrayList<HomeworkExt> list=new ArrayList<HomeworkExt>();
//		try 
//		{
//			stmt=con.prepareStatement("select hw.homeworkid,"
//					+ "hw.courseid,c.courseName,c.courseTerm,"
//					+ "hw.homeworktitle,hw.homeworkContent,hw.dueTime "
//					+ "from homework hw,course c "
//					+ "WHERE hw.courseid=c.courseid "
//					+ "AND hw.courseid=?");
//			stmt.setInt(1, courseid);
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				HomeworkExt homework = new HomeworkExt();
//				homework.setHomeworkid(result.getInt(1));
//				Course course = new Course();
//				course.setCourseid(result.getInt(2));
//				course.setCourseName(result.getString(3));
//				course.setCourseTerm(result.getString(4));
//				homework.setCourse(course);
//				homework.setHomeworkTitle(result.getString(5));
//				homework.setHomeworkContent(result.getString(6));
//				homework.setDueTime(result.getString(7));
//				list.add(homework);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally
//		{
//			daoHelper.closeConnection(con);
//			daoHelper.closePreparedStatement(stmt);
//			daoHelper.closeResult(result);
//		}
//		return list;
//	}

//	public List find() {
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
//		ArrayList<HomeworkExt> list=new ArrayList<HomeworkExt>();
//		try 
//		{
//			stmt=con.prepareStatement("select hw.homeworkid,"
//					+ "hw.courseid,c.courseName,c.courseTerm,"
//					+ "hw.homeworktitle,hw.homeworkContent,hw.dueTime "
//					+ "from homework hw,course c "
//					+ "WHERE hw.courseid=c.courseid");
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				HomeworkExt homework = new HomeworkExt();
//				homework.setHomeworkid(result.getInt(1));
//				Course course = new Course();
//				course.setCourseid(result.getInt(2));
//				course.setCourseName(result.getString(3));
//				course.setCourseTerm(result.getString(4));
//				homework.setCourse(course);
//				homework.setHomeworkTitle(result.getString(5));
//				homework.setHomeworkContent(result.getString(6));
//				homework.setDueTime(result.getString(7));
//				list.add(homework);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally
//		{
//			daoHelper.closeConnection(con);
//			daoHelper.closePreparedStatement(stmt);
//			daoHelper.closeResult(result);
//		}
//		return list;
//	}

}
