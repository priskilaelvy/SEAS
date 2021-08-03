package seas.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seas.dao.AdminDAO;
import seas.model.AdminBean;

/**
 * Servlet implementation class UpdateAdminAccController
 */
@WebServlet("/UpdateAdminAccController")
public class UpdateAdminAccController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDAO dao;
	String forward;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAdminAccController() {
        super();
        dao = new AdminDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminBean admin = new AdminBean();
		
		admin.setTel_admin(request.getParameter("tel_admin"));
		admin.setPwadmin(request.getParameter("pwadmin"));
		
		System.out.print(admin.getIdadmin());
		
		try {
			dao.updateAdmin(admin);
			System.out.println("update data admin");
			response.sendRedirect("/SEAS/System/Admin/profile.jsp");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
