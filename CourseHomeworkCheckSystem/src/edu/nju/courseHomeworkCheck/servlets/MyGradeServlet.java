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

import edu.nju.courseHomeworkCheck.action.business.HomeworkGradeListBean;
import edu.nju.courseHomeworkCheck.service.HomeworkManageService;

/**
 * Servlet implementation class MyGradeServlet
 */
@WebServlet("/MyGradeServlet")
public class MyGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ApplicationContext appliationContext;
    private static HomeworkManageService homeworkManage;   
	
//	@EJB HomeworkManageService homeworkManage;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyGradeServlet() {
        super();
    	appliationContext=new ClassPathXmlApplicationContext("applicationContext.xml"); 
    	homeworkManage = (HomeworkManageService) appliationContext.getBean("HomeworkManageService");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
		}
		else{
			ServletContext context = getServletContext();
			String studentid = String.valueOf(session.getAttribute("userid"));
			HomeworkGradeListBean listGrade = new HomeworkGradeListBean();
			listGrade.setHomeworkGradeList(homeworkManage.findGradeByStudent(studentid));
			try {
				request.setAttribute("nickname", session.getAttribute("nickname"));
				session.setAttribute("listGrade", listGrade);
				context.getRequestDispatcher("/jsp/grade.jsp").forward(
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
