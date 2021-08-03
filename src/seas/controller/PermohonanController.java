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
 * Servlet implementation class PermohonanController
 */
@WebServlet("/PermohonanController")
public class PermohonanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADD = "/SEAS/System/Staff/newapplication.jsp";
       
	private PermohonanDAO dao;
	String forward="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PermohonanController() {
        super();
        dao = new PermohonanDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
//		 int id = Integer.parseInt(request.getParameter("id"));
		 
			if  (action.equalsIgnoreCase("delete")) {
				
				PermohonanBean permohonan = new PermohonanBean();
				int idpermohonan= Integer.parseInt(request.getParameter("idpermohonan"));
				permohonan.setIdpermohonan(idpermohonan);
				dao.delete(permohonan);
				response.sendRedirect("/SEAS/System/Staff/listofapplication.jsp");
			}
			
			else
			{
				 forward = ADD;
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String typeApp = request.getParameter("typeApp");
		
		PermohonanBean permohonan = new PermohonanBean();
		
		permohonan.setJenis_tukar(request.getParameter("jenis_tukar"));
        permohonan.setNegeri(request.getParameter("negeri"));
        permohonan.setDaerah(request.getParameter("daerah"));
        permohonan.setKategori(request.getParameter("kategori"));
        permohonan.setSklh_asal(request.getParameter("sklh_asal"));
        permohonan.setNamamurid(request.getParameter("namamurid"));
        permohonan.setIc(request.getParameter("ic"));
        permohonan.setAlamat(request.getParameter("alamat"));
        permohonan.setBangsa(request.getParameter("bangsa"));
        permohonan.setTahun_ting(request.getParameter("tahun_ting"));
        permohonan.setAliran(request.getParameter("aliran"));
        permohonan.setSklh_mohon(request.getParameter("sklh_mohon"));
        permohonan.setSebab_pindah(request.getParameter("sebab_pindah"));
        permohonan.setNama_penjaga(request.getParameter("nama_penjaga"));
        permohonan.setIc_penjaga(request.getParameter("ic_penjaga"));
        permohonan.setNo_tel(request.getParameter("no_tel"));
        
        String status = request.getParameter("status");
        permohonan.setStatus(status);
        String idstaff = request.getParameter("idstaff");
        permohonan.setIdstaff(idstaff);
        
        System.out.println(idstaff);
        
        if(request.getParameter("idpermohonan") != null) {
        	int idpermohonan = Integer.parseInt(request.getParameter("idpermohonan"));
        	permohonan.setIdpermohonan(idpermohonan);
        
        	//permohonan = PermohonanDAO.getPermohonan(permohonan);
        }
		if(typeApp.equalsIgnoreCase("insert")){
        	dao.add(permohonan);
        	System.out.println("insert permohonan");
        	response.sendRedirect("/SEAS/System/Staff/listofapplication.jsp");
        }

        else{
        	try {
				dao.updateStatus(permohonan);
				System.out.println("update status permohonan");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("/SEAS/System/Pegawai/listofapplication.jsp");
        }	
	}
}
