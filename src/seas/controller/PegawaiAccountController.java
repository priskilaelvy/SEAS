 package seas.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.security.NoSuchAlgorithmException;
import javax.servlet.*;
import javax.servlet.http.*;
import seas.dao.PegawaiDAO;
import seas.model.PegawaiBean;

/**
 * Servlet implementation class PegawaiAccountController
 */
@WebServlet("/PegawaiAccountController")
public class PegawaiAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LOGIN = "/SEAS/Login/pegawailogin.jsp";
	private static String REGISTER = "/SEAS/Register/pegawairegister.jsp";
	private static String UPDATE = "/customer/updateAccount.jsp";
	private static String INDEX = "/SEAS/System/Pegawai/dashboard.jsp";
    private static String VIEW = "/SEAS/System/Pegawai/pegawaiprofile.jsp";
    private PegawaiDAO dao;
    String forward="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PegawaiAccountController() {
        super();
        dao = new PegawaiDAO();
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
    		String idpegawai = request.getParameter("idpegawai");
    		PegawaiBean pegawai = dao.getPegawaiByID(idpegawai);
    		request.setAttribute("pegawai", pegawai);
    	}
    	else if (action.equalsIgnoreCase("viewAccount")){
    		forward = VIEW;   
    		String idpegawai = request.getParameter("idpegawai");
    		PegawaiBean pegawai = dao.getPegawaiByID(idpegawai);
    		request.setAttribute("pegawai", pegawai);           
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
		
		PegawaiBean pegawai = new PegawaiBean();
		
		pegawai.setIdpegawai(request.getParameter("idpegawai"));
		pegawai.setNamapegawai(request.getParameter("namapegawai"));
		pegawai.setJawatan_pegawai(request.getParameter("jawatan_pegawai"));
		pegawai.setTel_pegawai(request.getParameter("tel_pegawai"));
		pegawai.setPwpegawai(request.getParameter("pwpegawai"));
		
		String idpegawai = request.getParameter("idpegawai");
		
		pegawai.setIdpegawai(idpegawai);
		
		pegawai = PegawaiDAO.getPegawai(pegawai);

		if(!pegawai.isValid()){
        	try {
				dao.add(pegawai);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	response.sendRedirect("/SEAS/Login/pegawailogin.jsp");
        }
		
        else{
        	try {
    			dao.updateAccount(pegawai);
    		} catch (NoSuchAlgorithmException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
            RequestDispatcher view = request.getRequestDispatcher(VIEW);
            request.setAttribute("pegawai", dao.getPegawaiByID(idpegawai));
            view.forward(request, response);
        }
	}

}
