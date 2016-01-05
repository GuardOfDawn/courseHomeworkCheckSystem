package edu.nju.courseHomework.factory;

import edu.nju.courseHomeworkCheck.dao.HomeworkDao;
import edu.nju.courseHomeworkCheck.dao.impl.HomeworkDaoImpl;

public class DaoFactory {
	
	public static HomeworkDao getHomeworkDao(){
		return HomeworkDaoImpl.getInstance();
	}
	
}
