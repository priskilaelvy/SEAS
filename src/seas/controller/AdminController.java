package seas.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import seas.dao.AdminDAO;
import seas.model.AdminBean;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LOGIN = "/System/Admin/adminlogin.jsp";
	private static String UPDATE = "/SEAS/System/Admin/updateProfile.jsp";
	private static String INDEX = "/SEAS/System/Admin/dashboard.jsp";
    private static String VIEW = "/SEAS/System/Admin/profile.jsp";
    private AdminDAO dao;
    String forward="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        dao = new AdminDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("updateAccount")){
					forward = UPDATE;
	            	String idadmin = request.getParameter("idadmin");
	            	AdminBean admin = dao.getAdminByID(idadmin);
	            	request.setAttribute("admin", admin);
	        }
		else {
					forward = VIEW;   
					String idadmin = request.getParameter("idadmin");
					AdminBean admin = dao.getAdminByID(idadmin);
					request.setAttribute("admin", admin);           
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
	    view.forward(request, response);	
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		AdminBean admin = new AdminBean();
		admin.setIdadmin(request.getParameter("idadmin"));
		admin.setNama_admin(request.getParameter("nama_admin"));
		admin.setTel_admin(request.getParameter("tel_admin"));
		admin.setPwadmin(request.getParameter("pwadmin"));
		
		String idadmin = request.getParameter("idadmin");
		
		admin.setIdadmin(idadmin);
		
		admin = AdminDAO.getAdmin(admin);
		
        	try {
    			dao.updateAccount(admin);
    		} catch (NoSuchAlgorithmException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
            RequestDispatcher view = request.getRequestDispatcher(VIEW);
            request.setAttribute("admin", dao.getAdminByID(idadmin));
            view.forward(request, response);
        }
        
	}
