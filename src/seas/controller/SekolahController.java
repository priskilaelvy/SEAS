package seas.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.security.NoSuchAlgorithmException;
import javax.servlet.*;
import javax.servlet.http.*;
import seas.dao.SekolahDAO;
import seas.model.SekolahBean;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/SekolahController")
public class SekolahController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static String LOGIN = "/login.jsp";
	private static String ADD = "/SEAS/System/Admin/addschool.jsp";
	private static String UPDATE = "/SEAS/System/Admin/updateschool.jsp";
	//private static String INDEX = "/customer/index.jsp";
    private String VIEW = "/SEAS/System/Admin/school.jsp";
    private SekolahDAO dao;
    String forward="";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SekolahController() {
        super();
        dao = new SekolahDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   
        String action = request.getParameter("action");

	if (action.equalsIgnoreCase("updateSekolah")){
				forward = UPDATE;
            	String kodsekolah = request.getParameter("kodsekolah");
            	SekolahBean sekolah = dao.getSekolahByKod(kodsekolah);
            	request.setAttribute("sekolah", sekolah);
        }
	else if (action.equalsIgnoreCase("viewSekolah")){
				forward = VIEW;   
				String kodsekolah= request.getParameter("kodsekolah");
				SekolahBean sekolah = dao.getSekolahByKod(kodsekolah);
				request.setAttribute("sekolah", sekolah);           
	}
	else {
	           forward = ADD;
	}

	RequestDispatcher view = request.getRequestDispatcher(forward);
    view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		SekolahBean sekolah = new SekolahBean();
		sekolah.setKodsekolah(request.getParameter("kodsekolah"));
		sekolah.setNama_sklh(request.getParameter("nama_sklh"));
		sekolah.setJenis_sklh(request.getParameter("jenis_sklh"));
		sekolah.setTelefon(request.getParameter("telefon"));
		sekolah.setLamanweb(request.getParameter("lamanweb"));
		sekolah.setAlamat(request.getParameter("alamat"));
		sekolah.setGurubesar_pengetua(request.getParameter("gurubesar_pengetua"));
		sekolah.setGuru_data(request.getParameter("guru_data"));
		
		String kodsekolah = request.getParameter("kodsekolah");
		
		sekolah.setKodsekolah(kodsekolah);
		
		sekolah = SekolahDAO.getSekolah(sekolah);

		if(!sekolah.isValid()){
        	try {
				dao.add(sekolah);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	response.sendRedirect("/SEAS/System/Admin/school.jsp");
        }
		
        else{
        	try {
    			dao.updateSekolah(sekolah);
    		} catch (NoSuchAlgorithmException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
            RequestDispatcher view = request.getRequestDispatcher(VIEW);
            request.setAttribute("sekolah", dao.getSekolahByKod(kodsekolah));
            view.forward(request, response);
        }
        
	}


}
