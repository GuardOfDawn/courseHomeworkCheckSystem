package edu.nju.courseHomeworkCheck.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import edu.nju.courseHomeworkCheck.models.Homework;

/**
 * Servlet implementation class BadHomeworkWarning
 */
@WebServlet("/BadHomeworkWarning")
public class BadHomeworkWarning extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource = null;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BadHomeworkWarning() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(){
		InitialContext jndiContext = null;
		
		Properties properties = new Properties();
		properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		try {
			jndiContext = new InitialContext(properties);
			datasource = (DataSource) jndiContext
					.lookup("java:comp/env/jdbc/studentinfo");
		} catch (NamingException e) {
			e.printStackTrace();
		}

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendRedirect(request.getContextPath() + "/Login");
		}
		else{
			request.setAttribute("userid", session.getAttribute("userid"));
			request.setAttribute("nickname", session.getAttribute("nickname"));
			request.setAttribute("gender", session.getAttribute("gender"));
			
			showStudentOpPage(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showStudentOpPage(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		checkHomework(request, response);
		ServletContext Context= getServletContext();
		
		@SuppressWarnings("unchecked")
		ArrayList<Homework> notUploadHomework = (ArrayList<Homework>) request.getAttribute("notUploadHomework");
		@SuppressWarnings("unchecked")
		ArrayList<Homework> failedHomework = (ArrayList<Homework>) request.getAttribute("failedHomework");
		
		int webCounter = Integer.parseInt((String) Context.getAttribute("webCounter"));
		int totalCounter = Integer.parseInt((String) Context.getAttribute("totalCounter"));
		int onlineCounter = Integer.parseInt((String) Context.getAttribute("onlineCounter"));
		int visitorCounter = totalCounter - onlineCounter;
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body bgcolor='#FFFACD'>");
		out.println("<p>总在线人数："+totalCounter+" 登录人数："+onlineCounter+" 游客人数:"+visitorCounter+
				" 网站总登录次数："+webCounter+"</p>");
        out.println("<table width='650' border='0' >");
		out.println("<tr>");
		String imagePath = null;
		if(Integer.parseInt(String.valueOf(request.getAttribute("gender")))==1){
			imagePath = request.getContextPath()+"/image/boyPic.jpeg";
		}
		else{
			imagePath = request.getContextPath()+"/image/girlPic.jpeg";
		}
		out.println("<td width='80' height='80' background='"+imagePath+"'></td>"
				+ "<td width='650' height='80' background='"
				+ request.getContextPath() + "/image/top.jpg'>&nbsp;</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<h3>Welcome "+request.getAttribute("nickname")+"!</h3>");
		out.println("<table bgcolor='#B0E0E6' style='font-weight:bold'>");
		out.println("<tbody><tr>");
		out.println("<td><a href='"+request.getContextPath() + "/BadHomeworkWarning"+"' style='text-decoration: none;'>首页</a></td>");
		out.println("<td style='BACKGROUND-POSITION: 50% 50%;background-image: url(image/verticalLine.png);BACKGROUND-REPEAT: no-repeat;' width='27'>&nbsp;&nbsp;</td>");
		out.println("<td><a href='"+request.getContextPath() + "/MyCourse"+"' style='text-decoration: none;'>我的课程</a></td>");
		out.println("<td style='BACKGROUND-POSITION: 50% 50%;background-image: url(image/verticalLine.png);BACKGROUND-REPEAT: no-repeat;' width='27'>&nbsp;&nbsp;</td>");
		out.println("<td><a href='"+request.getContextPath() + "/MyHomework"+"' style='text-decoration: none;'>我的作业</a></td>");
//		out.println("<td style='BACKGROUND-POSITION: 50% 50%;background-image: url(image/verticalLine.png);BACKGROUND-REPEAT: no-repeat;' width='27'>&nbsp;&nbsp;</td>");
//		out.println("<td><a href='http://localhost:8080"+req.getContextPath() + "/Login"+"' style='text-decoration: none;'>登出</a></td>");
		out.println("</tr></tbody>");
		out.println("</table>");
		out.println("<p>未提交作业：</p>");
		if(notUploadHomework!=null&&notUploadHomework.size()!=0){
			int num = 1;
			for (int i = 0; i < notUploadHomework.size(); i++) {
				Homework hw = notUploadHomework.get(i);
				out.println("<table border='2px' bordercolor='#00FF7F' cellspacing='0px' style='border-collapse:collapse;max-width:800px'>");
				out.println("<tbody>");
				out.println("<tr>"
						+ "<td width='%20'>Assignment No:</td>"
						+ "<td width='%80'>"+num+"</td>"
						+ "</tr>");
				out.println("<tr>"
						+ "<td>Course Name:</td>"
						+ "<td>"+hw.getCourseName()+"</td>"
						+ "</tr>");
				out.println("<tr>"
						+ "<td>Due date:</td>"
						+ "<td>"+hw.getDueTime()+"</td>"
						+ "</tr>");
				out.println("<tr>"
						+ "<td>Title:</td>"
						+ "<td>"+hw.getHomeworkTitle()+"</td>"
						+ "</tr>");
				out.println("<tr>"
						+ "<td>Description:</td>"
						+ "<td>"+hw.getHomeworkContent()+"</td>"
						+ "</tr>");
				out.println("</tbody>");
				out.println("</table>");
				if(i!=notUploadHomework.size()-1)
					out.println("<p></p>");
				num++;
			}
		}
		else{
			out.println("<p>恭喜，你没有未提交的作业！</p>");
		}

		out.println("<p>不合格作业：</p>");
		if(failedHomework!=null&&failedHomework.size()!=0){
			int num = 1;
			for (int i = 0; i < failedHomework.size(); i++) {
				Homework hw = failedHomework.get(i);
				out.println("<table border='2px' bordercolor='#00FF7F' cellspacing='0px' style='border-collapse:collapse;max-width:800px'>");
				out.println("<tbody>");
				out.println("<tr>"
						+ "<td width='%20'>Assignment No:</td>"
						+ "<td width='%80'>"+num+"</td>"
						+ "</tr>");
				out.println("<tr>"
						+ "<td>Course Name:</td>"
						+ "<td>"+hw.getCourseName()+"</td>"
						+ "</tr>");
				out.println("<tr>"
						+ "<td>Due date:</td>"
						+ "<td>"+hw.getDueTime()+"</td>"
						+ "</tr>");
				out.println("<tr>"
						+ "<td>Description:</td>"
						+ "<td>"+hw.getHomeworkContent()+"</td>"
						+ "</tr>");
				out.println("<tr>"
						+ "<td>Completion:</td>"
						+ "<td>"+hw.getCompletition()+"</td>"
						+ "</tr>");
				out.println("<tr>"
						+ "<td>Grade:</td>"
						+ "<td>"+hw.getGrade()+"</td>"
						+ "</tr>");
				out.println("</tbody>");
				out.println("</table>");
				if(i!=failedHomework.size()-1)
					out.println("<p></p>");
				num++;
			}
		}
		else{
			out.println("<p>恭喜，你没有不合格的作业！</p>");
		}
		out.println("<p></p>");
		out.println("<form method='GET' action='"
				+ response.encodeURL(request.getContextPath() + "/Login") + "'>");
		out.println("<input type='submit' name='Logout' value='登出'/>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	private boolean checkHomework(HttpServletRequest req,
			HttpServletResponse resp) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;	
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ArrayList<Homework> notUploadHomework = new ArrayList<Homework>();
			stmt = connection.prepareStatement("SELECT c.courseName,h.homeworktitle,h.homeworkContent,h.dueTime "
					+ "FROM homework h,course c,courseselection cs "
					+ "WHERE cs.studentid=? AND h.courseid=cs.courseid "
					+ "AND h.homeworkid NOT IN (SELECT hg.homeworkid FROM homeworkgrade hg WHERE hg.studentid=?) "
					+ "AND h.dueTime>CURRENT_TIMESTAMP AND cs.courseid=c.courseid");
			stmt.setString(1, (String) req.getAttribute("userid"));
			stmt.setString(2, (String) req.getAttribute("userid"));
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				result = true;
				Homework work = new Homework();
				work.setCourseName(resultSet.getString("courseName"));
				work.setHomeworkTitle(resultSet.getString("homeworktitle"));
				work.setHomeworkContent(resultSet.getString("homeworkContent"));
				work.setDueTime(resultSet.getString("dueTime"));
				notUploadHomework.add(work);
			}
			req.setAttribute("notUploadHomework", notUploadHomework);

			ArrayList<Homework> failedHomework = new ArrayList<Homework>();
			stmt = connection.prepareStatement("SELECT c.courseName,h.homeworkContent"
					+ ",h.dueTime,hg.completition,hg.grade "
					+ "FROM homeworkgrade hg,homework h,course c "
					+ "WHERE hg.studentid=? "
					+ "AND hg.grade<60 "
					+ "AND hg.homeworkid=h.homeworkid "
					+ "AND h.courseid=c.courseid");
			stmt.setString(1, (String) req.getAttribute("userid"));
			resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				result = true;
				Homework work = new Homework();
				work.setCourseName(resultSet.getString("courseName"));
				work.setHomeworkContent(resultSet.getString("homeworkContent"));
				work.setDueTime(resultSet.getString("dueTime"));
				work.setCompletition(resultSet.getString("completition"));
				work.setGrade(resultSet.getInt("grade"));
				failedHomework.add(work);
			}
			req.setAttribute("failedHomework", failedHomework);
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
