package seas.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import seas.dao.StaffDAO;
import seas.model.StaffBean;

/**
 * Servlet implementation class StaffLoginController
 */
@WebServlet("/StaffLoginController")
public class StaffLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StaffDAO dao;
    HttpServletRequest request;
    HttpServletResponse response;
    String forward="";
    String action="";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StaffLoginController() {
		super();
		dao = new StaffDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
        
		
    	try {
			
			StaffBean staff = new StaffBean();
			staff.setIdstaff(request.getParameter("idstaff"));
			staff.setPwstaff(request.getParameter("pwstaff"));

			staff = StaffDAO.login(staff);

			if(staff.isValid())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", staff.getIdstaff());
				response.sendRedirect("/SEAS/System/Staff/dashboard.jsp"); // logged-in page
			}
			else
			{
				response.sendRedirect("/SEAS/Login/stafflogin.jsp");
			}
			
		}

		catch (Throwable ex) {
			System.out.println(ex);
		}
	}
}
