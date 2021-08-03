package seas.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import seas.dao.StaffDAO;
import seas.model.StaffBean;

/**
 * Servlet implementation class StaffController
 */
@WebServlet("/StaffController")
public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LOGIN = "/SEAS/Login/stafflogin.jsp";
	private static String REGISTER = "/SEAS/Register/staffregister.jsp";
	private static String UPDATE = "/SEAS/System/Staff/updateAccount.jsp";
	private static String INDEX = "/SEAS/System/Staff/dashboard.jsp";
    private static String VIEW = "/SEAS/System/Staff/staffprofile.jsp";
    private StaffDAO dao;
    String forward="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffController() {
        super();
        dao = new StaffDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equalsIgnoreCase("updateAccount")){
					forward = UPDATE;
	            	String idstaff = request.getParameter("idstaff");
	            	StaffBean staff = dao.getStaffByID(idstaff);
	            	request.setAttribute("staff", staff);
	        }
		else if (action.equalsIgnoreCase("viewAccount")){
					forward = VIEW;   
					System.out.println(forward);
					String idstaff = request.getParameter("idstaff");
					StaffBean staff = dao.getStaffByID(idstaff);
					request.setAttribute("staff", staff);           
		}
		else {
		           forward = REGISTER;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
	    view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StaffBean staff = new StaffBean();
		staff.setIdstaff(request.getParameter("idstaff"));
		staff.setNamastaff(request.getParameter("namastaff"));
		staff.setJawatan(request.getParameter("jawatan"));
		staff.setTelstaff(request.getParameter("telstaff"));
		staff.setPwstaff(request.getParameter("pwstaff"));
		
		String idstaff = request.getParameter("idstaff");
		
		staff.setIdstaff(idstaff);
		
		staff = StaffDAO.getStaff(staff);

		if(!staff.isValid()){
        	try {
				dao.add(staff);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	response.sendRedirect("/SEAS/Login/stafflogin.jsp");
        }
		
        else{
        	try {
    			dao.updateAccount(staff);
    		} catch (NoSuchAlgorithmException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
            RequestDispatcher view = request.getRequestDispatcher(VIEW);
            request.setAttribute("staff", dao.getStaffByID(idstaff));
            view.forward(request, response);
        }
	}
}
