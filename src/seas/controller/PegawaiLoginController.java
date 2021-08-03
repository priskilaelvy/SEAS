package seas.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import seas.dao.PegawaiDAO;
import seas.model.PegawaiBean;

/**
 * Servlet implementation class PegawaiLoginController
 */
@WebServlet("/PegawaiLoginController")
public class PegawaiLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PegawaiDAO dao;
    HttpServletRequest request;
    HttpServletResponse response;
    String forward="";
    String action="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PegawaiLoginController() {
        super();
        dao = new PegawaiDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
        
		
    	try {
			
			PegawaiBean pegawai = new PegawaiBean();
			pegawai.setIdpegawai(request.getParameter("idpegawai"));
			pegawai.setPwpegawai(request.getParameter("pwpegawai"));

			pegawai = PegawaiDAO.login(pegawai);

			if(pegawai.isValid())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", pegawai.getIdpegawai());
				response.sendRedirect("/SEAS/System/Pegawai/dashboard.jsp"); // logged-in page
			}
			else
			{
				response.sendRedirect("/SEAS/invalidLogin.jsp");
			}
			
		}

		catch (Throwable ex) {
			System.out.println(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
