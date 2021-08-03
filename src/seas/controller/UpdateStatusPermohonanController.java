package seas.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seas.dao.PermohonanDAO;
import seas.model.PermohonanBean;

/**
 * Servlet implementation class UpdateStatusPermohonanController
 */
@WebServlet("/UpdateStatusPermohonanController")
public class UpdateStatusPermohonanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PermohonanDAO dao;
	String forward = "";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStatusPermohonanController() {
        super();
        dao = new PermohonanDAO();
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
		
		PermohonanBean permohonan = new PermohonanBean();
		
		permohonan.setStatus(request.getParameter("status"));
		
		System.out.print(permohonan.getIdpermohonan());
		
		try {
			dao.updateStatus(permohonan);
			System.out.println("update status permohonan");
			response.sendRedirect("/SEAS/System/Pegawai/listofapplication.jsp");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
