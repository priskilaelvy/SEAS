package seas.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import seas.connection.ConnectionManager;
import seas.model.PermohonanBean;

public class PermohonanDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static int idpermohonan;
	static String jenis_tukar, negeri, daerah, kategori, sklh_asal, namamurid, ic, alamat, bangsa, tahun_ting, aliran, sklh_mohon, sebab_pindah, nama_penjaga, ic_penjaga, no_tel, idstaff,status;

	public static PermohonanBean getPermohonanBean(PermohonanBean permohonan) {

		idpermohonan = permohonan.getIdpermohonan();

		String searchQuery = "select * from permohonan where idpermohonan='" + idpermohonan + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if user exists set the isValid variable to true
			if (more) {
				int idpermohonan = rs.getInt("idpermohonan");

				permohonan.setIdpermohonan(idpermohonan);
				permohonan.setValid(true);
			}

			else if (!more) {
				System.out.println("Sorry");
				permohonan.setValid(false);
			}

		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return permohonan;

	}


	public void add(PermohonanBean permohonan) {

		jenis_tukar  = permohonan.getJenis_tukar();
		negeri = permohonan.getNegeri();
		daerah = permohonan.getDaerah();
		kategori = permohonan.getKategori();
		sklh_asal = permohonan.getSklh_asal();
		namamurid = permohonan.getNamamurid();
		ic = permohonan.getIc();
		alamat = permohonan.getAlamat();
		bangsa = permohonan.getBangsa();
		tahun_ting = permohonan.getTahun_ting();
		aliran = permohonan.getAliran();
		sklh_mohon = permohonan.getAliran();
		sebab_pindah = permohonan.getSebab_pindah();
		nama_penjaga = permohonan.getNama_penjaga();
		ic_penjaga = permohonan.getIc_penjaga();
		no_tel = permohonan.getNo_tel();
		idstaff = permohonan.getIdstaff();
		status = permohonan.getStatus();

		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into PERMOHONAN (jenis_tukar,negeri,daerah,kategori,sklh_asal,namamurid,ic,alamat,bangsa,tahun_ting,aliran,sklh_mohon,sebab_pindah,nama_penjaga,ic_penjaga,no_tel,idstaff,status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,jenis_tukar);
			ps.setString(2,negeri);
			ps.setString(3,daerah);
			ps.setString(4,kategori);
			ps.setString(5,sklh_asal);
			ps.setString(6,namamurid);
			ps.setString(7,ic);
			ps.setString(8,alamat);
			ps.setString(9,bangsa);
    		ps.setString(10,tahun_ting);
    		ps.setString(11,aliran);
    		ps.setString(12,sklh_mohon);
    		ps.setString(13,sebab_pindah);
    		ps.setString(14,nama_penjaga);
    		ps.setString(15,ic_penjaga);
    		ps.setString(16,no_tel);
    		ps.setString(17,idstaff);
    		ps.setString(18,status);
			ps.executeUpdate();

			System.out.println("Your permohonan success for " + namamurid);
		}

		catch (Exception ex) {
			System.out.println("failed: An Exception has occurred! " + ex);
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
	}

	public void updatePermohonan(PermohonanBean permohonan) throws NoSuchAlgorithmException {
		idpermohonan = permohonan.getIdpermohonan();
		jenis_tukar  = permohonan.getJenis_tukar();
		negeri = permohonan.getNegeri();
		daerah = permohonan.getDaerah();
		kategori = permohonan.getKategori();
		sklh_asal = permohonan.getSklh_asal();
		namamurid = permohonan.getNamamurid();
		ic = permohonan.getIc();
		alamat = permohonan.getAlamat();
		bangsa = permohonan.getBangsa();
		tahun_ting = permohonan.getTahun_ting();
		aliran = permohonan.getAliran();
		sklh_mohon = permohonan.getSklh_mohon();
		sebab_pindah = permohonan.getSebab_pindah();
		nama_penjaga = permohonan.getNama_penjaga();
		ic_penjaga = permohonan.getIc_penjaga();
		no_tel = permohonan.getNo_tel();
		idstaff = permohonan.getIdstaff();
		status = permohonan.getStatus();
		
		String searchQuery = "UPDATE permohonan SET sklh_mohon ='" + sklh_mohon + "' WHERE idpermohonan= '" + idpermohonan + "'";
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
			System.out.println("Masuk try" + idpermohonan);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Masuk catch");
		}
	}
	
	public void updateStatus(PermohonanBean permohonan) throws NoSuchAlgorithmException {
		
		idpermohonan = permohonan.getIdpermohonan();
		status = permohonan.getStatus();
		
		String searchQuery = "UPDATE permohonan SET status ='" + status + "' WHERE idpermohonan= '" + idpermohonan + "'";
		
		try {

			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);

			System.out.print(" Permohonan " + status);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Update Unsuccess");
		}
	}
	
	public void delete(PermohonanBean permohonan) {
		int idpermohonan = permohonan.getIdpermohonan();

		try {

			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("delete from permohonan where idpermohonan=?");
			ps.setInt(1, idpermohonan);
			ps.executeUpdate();

			System.out.println("DELETED!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Log In failed: An Exception has occurred! " + "FAILED");

		}
	}  
	public static List<PermohonanBean> getAllPermohonan(){  
		List<PermohonanBean> list=new ArrayList<PermohonanBean>();  

		try{  
			currentCon = ConnectionManager.getConnection();  
			ps=currentCon.prepareStatement("select * from permohonan");  
			ResultSet rs=ps.executeQuery();  
			while(rs.next()){  
				PermohonanBean permohonan = new PermohonanBean();  
				permohonan.setIdpermohonan(rs.getInt("idpermohonan"));
	            permohonan.setJenis_tukar(rs.getString("jenis_tukar"));
	            permohonan.setNegeri(rs.getString("negeri"));
	            permohonan.setDaerah(rs.getString("daerah"));
	            permohonan.setKategori(rs.getString("kategori"));
	            permohonan.setSklh_asal(rs.getString("sklh_asal"));
	            permohonan.setNamamurid(rs.getString("namamurid"));
	            permohonan.setIc(rs.getString("ic"));
	            permohonan.setAlamat(rs.getString("alamat"));
	            permohonan.setBangsa(rs.getString("bangsa"));
	            permohonan.setTahun_ting(rs.getString("tahun_ting"));
	            permohonan.setAliran(rs.getString("aliran"));
	            permohonan.setSklh_mohon(rs.getString("sklh_mohon"));
	            permohonan.setSebab_pindah(rs.getString("sebab_pindah"));
	            permohonan.setNama_penjaga(rs.getString("nama_penjaga"));
	            permohonan.setIc_penjaga(rs.getString("ic_penjaga"));
	            permohonan.setNo_tel(rs.getString("no_tel"));
	            permohonan.setIdstaff(rs.getString("idstaff"));
	            permohonan.setStatus(rs.getString("status"));
				list.add(permohonan);  
			}  
		}catch(Exception e){System.out.println(e);}  
		return list;  
	}
	
	public PermohonanBean getPermohonanById(int idpermohonan){  
		PermohonanBean permohonan = new PermohonanBean();
	
		try{  
			currentCon = ConnectionManager.getConnection();  
			ps=currentCon.prepareStatement("select * from permohonan where idpermohonan=?");  
			ps.setInt(1,idpermohonan);  
			ResultSet rs=ps.executeQuery();  
			
			if (rs.next()){  
				//PermohonanBean permohonan1 = new PermohonanBean();  
				permohonan.setIdpermohonan(rs.getInt("idpermohonan"));
	            permohonan.setJenis_tukar(rs.getString("jenis_tukar"));
	            permohonan.setNegeri(rs.getString("negeri"));
	            permohonan.setDaerah(rs.getString("daerah"));
	            permohonan.setKategori(rs.getString("kategori"));
	            permohonan.setSklh_asal(rs.getString("sklh_asal"));
	            permohonan.setNamamurid(rs.getString("namamurid"));
	            permohonan.setIc(rs.getString("ic"));
	            permohonan.setAlamat(rs.getString("alamat"));
	            permohonan.setBangsa(rs.getString("bangsa"));
	            permohonan.setTahun_ting(rs.getString("tahun_ting"));
	            permohonan.setAliran(rs.getString("aliran"));
	            permohonan.setSklh_mohon(rs.getString("sklh_mohon"));
	            permohonan.setSebab_pindah(rs.getString("sebab_pindah"));
	            permohonan.setNama_penjaga(rs.getString("nama_penjaga"));
	            permohonan.setIc_penjaga(rs.getString("ic_penjaga"));
	            permohonan.setNo_tel(rs.getString("no_tel"));
	            permohonan.setIdstaff(rs.getString("idstaff"));
	            permohonan.setStatus(rs.getString("status"));
			}  
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Sorry salah");
		}
		return permohonan;
	}
	
	public static PermohonanBean getPermohonan(PermohonanBean permohonan)  {

		idstaff = permohonan.getIdstaff();

		String searchQuery = "select * from permohonan where idstaff='" + idstaff + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if user exists set the isValid variable to true
			if (more) {
				String idstaff = rs.getString("idstaff");

				permohonan.setIdstaff(idstaff);
				permohonan.setValid(true);
				System.out.println("taknak");
			}

			else if (!more) {
				System.out.println("Sorry");
				permohonan.setValid(false);
			}

		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + "SALAH");
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return permohonan;
	}
	
	public static List<PermohonanBean> getMenungguPermohonan(){  
		List<PermohonanBean> list2=new ArrayList<PermohonanBean>();  

		try{  
			currentCon = ConnectionManager.getConnection();  
			ps=currentCon.prepareStatement("select * from permohonan where status like 'Menunggu'");
			ResultSet rs=ps.executeQuery();  
			while(rs.next()){  
				PermohonanBean permohonan = new PermohonanBean();  
				permohonan.setIdpermohonan(rs.getInt("idpermohonan"));
	            permohonan.setJenis_tukar(rs.getString("jenis_tukar"));
	            permohonan.setNegeri(rs.getString("negeri"));
	            permohonan.setDaerah(rs.getString("daerah"));
	            permohonan.setKategori(rs.getString("kategori"));
	            permohonan.setSklh_asal(rs.getString("sklh_asal"));
	            permohonan.setNamamurid(rs.getString("namamurid"));
	            permohonan.setIc(rs.getString("ic"));
	            permohonan.setAlamat(rs.getString("alamat"));
	            permohonan.setBangsa(rs.getString("bangsa"));
	            permohonan.setTahun_ting(rs.getString("tahun_ting"));
	            permohonan.setAliran(rs.getString("aliran"));
	            permohonan.setSklh_mohon(rs.getString("sklh_mohon"));
	            permohonan.setSebab_pindah(rs.getString("sebab_pindah"));
	            permohonan.setNama_penjaga(rs.getString("nama_penjaga"));
	            permohonan.setIc_penjaga(rs.getString("ic_penjaga"));
	            permohonan.setNo_tel(rs.getString("no_tel"));
	            permohonan.setIdstaff(rs.getString("idstaff"));
	            permohonan.setStatus(rs.getString("status"));
				list2.add(permohonan);  
			}  
		}catch(Exception e){System.out.println(e);}  
		return list2;  
	}
}
