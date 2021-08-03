package seas.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import seas.dao.AdminDAO;
import seas.model.AdminBean;

/**
 * Servlet implementation class AdminLoginController
 */
@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminDAO dao;
    HttpServletRequest request;
    HttpServletResponse response;
    String forward="";
    String action="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginController() {
        super();
        dao = new AdminDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
        
		
    	try {
			
			AdminBean admin = new AdminBean();
			admin.setIdadmin(request.getParameter("idadmin"));
			admin.setPwadmin(request.getParameter("pwadmin"));

			admin = AdminDAO.login(admin);

			if(admin.isValid())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", admin.getIdadmin());
				response.sendRedirect("/SEAS/System/Admin/dashboard.jsp"); // logged-in page
			}
			else
			{
				response.sendRedirect("/SEAS/Login/adminlogin.jsp");
			}
			
		}

		catch (Throwable ex) {
			System.out.println(ex);
		}
	}
}
