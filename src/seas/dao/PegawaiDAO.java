package seas.dao;

import java.util.*;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import seas.model.PegawaiBean;
import seas.connection.ConnectionManager;

public class PegawaiDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
    static String idpegawai, namapegawai, jawatan_pegawai, tel_pegawai, pwpegawai;
    
    //login
    public static PegawaiBean login(PegawaiBean bean) throws NoSuchAlgorithmException {
    	
        idpegawai = bean.getIdpegawai();
        pwpegawai = bean.getPwpegawai();

        //convert the pwPegawai to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwpegawai.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String searchQuery = "select * from PEGAWAI where idpegawai='" + idpegawai + "' AND pwpegawai='" + sb.toString() + "'";

        System.out.println("Your id is " + idpegawai);
        System.out.println("Your password is " + pwpegawai);
        System.out.println("Query: " + searchQuery);

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String idpegawai = rs.getString("idpegawai");
           
           		System.out.println("Welcome " + idpegawai);
                bean.setIdpegawai(idpegawai);
                bean.setValid(true);
           	}
           
            // if user does not exist set the isValid variable to false
            else if (!more) {
            	System.out.println("Sorry, you are not a registered user! Please sign up first");
            	bean.setValid(false);
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

        return bean;
    }
    
    //add new user (register)
    public void add(PegawaiBean bean) throws NoSuchAlgorithmException{
    	
        idpegawai = bean.getIdpegawai();
        namapegawai = bean.getNamapegawai();
        jawatan_pegawai = bean.getJawatan_pegawai();
        tel_pegawai = bean.getTel_pegawai();
        pwpegawai = bean.getPwpegawai();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwpegawai.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String pass = sb.toString();
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into PEGAWAI (idpegawai,namapegawai,jawatan_pegawai,tel_pegawai,pwpegawai)values(?,?,?,?,?)");
    		ps.setString(1,idpegawai);
    		ps.setString(2,namapegawai);
    		ps.setString(3,jawatan_pegawai);
    		ps.setString(4,tel_pegawai);
    		ps.setString(5,pass);
    		ps.executeUpdate();
    	
    		System.out.println("Your id is " + idpegawai);
    		System.out.println("Your password is " + pass);
            
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
    
    public void deleteUser(String idpegawai) {
        try {
        	currentCon = ConnectionManager.getConnection();
        	ps=currentCon.prepareStatement("delete from PEGAWAI where idpegawai=?");
            ps.setString(1, idpegawai);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update account
    public void updateAccount(PegawaiBean bean) throws NoSuchAlgorithmException {

    	idpegawai = bean.getIdpegawai();
        namapegawai = bean.getNamapegawai();
        jawatan_pegawai = bean.getJawatan_pegawai();
        tel_pegawai = bean.getTel_pegawai();
        pwpegawai = bean.getPwpegawai();

    	//convert the pwPegawai to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwpegawai.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
       
        String searchQuery = "UPDATE PEGAWAI SET  jawatan_pegawai='" + jawatan_pegawai + "', tel_pegawai='" + tel_pegawai + "', pwpegawai='" + sb.toString() + "' WHERE idpegawai= '" + idpegawai + "'";
    	
    	try {

            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            stmt.executeUpdate(searchQuery);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //get all user
    public List<PegawaiBean> getAllPegawai() {
        List<PegawaiBean> pegawai = new ArrayList<PegawaiBean>();
        try {
        	currentCon = ConnectionManager.getConnection();
        	stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery("select * from PEGAWAI");
            
            while (rs.next()) {
                PegawaiBean p = new PegawaiBean();
                p.setIdpegawai(rs.getString("idpegawai"));
                p.setPwpegawai(rs.getString("pwpegawai"));
                pegawai.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pegawai;
    }
    
    //get user by idPegawai
    public PegawaiBean getPegawaiByID(String idpegawai) {
        PegawaiBean pegawai = new PegawaiBean();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from Pegawai where idpegawai=?");
            
            ps.setString(1, idpegawai);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                pegawai.setIdpegawai(rs.getString("idpegawai"));
                pegawai.setNamapegawai(rs.getString("namapegawai"));
                pegawai.setJawatan_pegawai(rs.getString("jawatan_pegawai"));
                pegawai.setTel_pegawai(rs.getString("tel_pegawai"));
                pegawai.setPwpegawai(rs.getString("pwpegawai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pegawai;
    }
    
  //get user by idPegawai
public static PegawaiBean getPegawai(PegawaiBean bean)  {
    	
        idpegawai = bean.getIdpegawai();

        String searchQuery = "select * from PEGAWAI where idpegawai='" + idpegawai + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String idpegawai = rs.getString("idpegawai");
           
                bean.setIdpegawai(idpegawai);
                bean.setValid(true);
           	}
           
            else if (!more) {
            	System.out.println("Sorry");
            	bean.setValid(false);
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

        return bean;
    }
}
