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
import seas.model.SekolahBean;

public class SekolahDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static String kodsekolah, nama_sklh, jenis_sklh, telefon, lamanweb, alamat, gurubesar_pengetua, guru_data;

	public void add(SekolahBean s) throws NoSuchAlgorithmException{
		
		kodsekolah = s.getKodsekolah();
		nama_sklh = s.getNama_sklh();
		jenis_sklh = s.getJenis_sklh();
		telefon = s.getTelefon();
		lamanweb = s.getLamanweb();
		alamat = s.getAlamat();
		gurubesar_pengetua = s.getGurubesar_pengetua();
		guru_data = s.getGuru_data();

		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into SEKOLAH (kodsekolah, nama_sklh, jenis_sklh, telefon, lamanweb, alamat, gurubesar_pengetua, guru_data)values(?,?,?,?,?,?,?,?)");
			ps.setString(1,kodsekolah);
			ps.setString(2,nama_sklh);
			ps.setString(3,jenis_sklh);
			ps.setString(4,telefon);
			ps.setString(5,lamanweb);
			ps.setString(6,alamat);
			ps.setString(7,gurubesar_pengetua);
			ps.setString(8,guru_data);
			ps.executeUpdate();

			System.out.println("Your kod sekolah is " + kodsekolah);

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

	public static SekolahBean getSekolah(SekolahBean s)  {

		kodsekolah = s.getKodsekolah();

		String searchQuery = "select * from SEKOLAH where kodsekolah='" + kodsekolah + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			//   boolean more = rs.next();

			// if user exists set the isValid variable to true
			/* if (more) {
            	String cocurriculum_id = rs.getString("cocurriculum_id");

                c.setCocurriculum_id(cocurriculum_id);
                c.setValid(true);
           	}

            else if (!more) {
            	System.out.println("Sorry");
            	c.setValid(false);
            }*/

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

		return s;
	}

	public void updateSekolah(SekolahBean s) throws NoSuchAlgorithmException {

		kodsekolah = s.getKodsekolah();
		nama_sklh = s.getNama_sklh();
		jenis_sklh = s.getJenis_sklh();
		telefon = s.getTelefon();
		lamanweb = s.getLamanweb();
		alamat = s.getAlamat();
		gurubesar_pengetua = s.getGurubesar_pengetua();
		guru_data = s.getGuru_data();

		String searchQuery = "UPDATE sekolah SET telefon = '" + telefon + "', lamanweb = '" + lamanweb + "', gurubesar_pengetua = '" + gurubesar_pengetua + "', guru_data = '" + guru_data + "' WHERE kodsekolah= '" + kodsekolah + "'";

		try {

			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Update Unsuccessful");
		}
	}

	public static List<SekolahBean> getAllSekolah() 
	{
		List<SekolahBean> sekolah = new ArrayList<SekolahBean>();
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			String q = "select * from SEKOLAH order by KODSEKOLAH asc";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				SekolahBean s = new SekolahBean();  
				s.setKodsekolah(rs.getString("kodsekolah"));
				s.setNama_sklh(rs.getString("nama_sklh"));
				s.setJenis_sklh(rs.getString("jenis_sklh"));
				s.setTelefon(rs.getString("telefon"));
				s.setLamanweb(rs.getString("lamanweb"));
				s.setAlamat(rs.getString("alamat"));
				s.setGurubesar_pengetua(rs.getString("gurubesar_pengetua"));
				s.setGuru_data(rs.getString("guru_data"));
				sekolah.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sekolah;
	}

	public SekolahBean getSekolahByKod(String kodsekolah) {
		SekolahBean s = new SekolahBean();
		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("select * from SEKOLAH where kodsekolah=?");
			ps.setString(1, kodsekolah);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {	        	

				s.setKodsekolah(rs.getString("kodsekolah"));
				s.setNama_sklh(rs.getString("nama_sklh"));
				s.setJenis_sklh(rs.getString("jenis_sklh"));
				s.setTelefon(rs.getString("telefon"));
				s.setLamanweb(rs.getString("lamanweb"));
				s.setAlamat(rs.getString("alamat"));
				s.setGurubesar_pengetua(rs.getString("gurubesar_pengetua"));
				s.setGuru_data(rs.getString("guru_data"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Sorry");
		}
		return s;
	}  
	
	public void delete(SekolahBean bean) {
		String kodsekolah = bean.getKodsekolah();

		try {

			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("delete from SEKOLAH where kodsekolah=?");
			ps.setString(1, kodsekolah);
			ps.executeUpdate();

			System.out.println("DELETED!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Log In failed: An Exception has occurred! " + "TAK JADI");

		}
	}

}
