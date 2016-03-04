package edu.nju.courseHomeworkCheck.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nju.courseHomeworkCheck.action.business.CourseListBean;
import edu.nju.courseHomeworkCheck.service.CourseManageService;

/**
 * Servlet implementation class MyCourse
 */
@WebServlet("/MyCourseServlet")
public class MyCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ApplicationContext appliationContext;
	private static CourseManageService courseManage;
	
//	@EJB CourseManageService courseManage;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCourseServlet() {
        super();
    	appliationContext=new ClassPathXmlApplicationContext("applicationContext.xml"); 
    	courseManage = (CourseManageService) appliationContext.getBean("CourseManageService");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
		}
		else{
			ServletContext context = getServletContext();
			String studentid = String.valueOf(session.getAttribute("userid"));
			CourseListBean courseList = new CourseListBean();
			courseList.setCourseList(courseManage.findCourseByStudent(studentid));
			try {
				request.setAttribute("nickname", session.getAttribute("nickname"));
				session.setAttribute("listCourse", courseList);
				context.getRequestDispatcher("/jsp/myCourses.jsp").forward(
						request, response);
			} catch (ServletException e) {
				// System error - report error 500 and message
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"This is a ServletException.");
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
