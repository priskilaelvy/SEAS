package seas.dao;

import java.sql.*;
import java.security.NoSuchAlgorithmException;
import seas.model.AdminBean;
import seas.connection.ConnectionManager;

public class AdminDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
    static String idadmin, nama_admin, tel_admin, pwadmin;
    
    //login
    public static AdminBean login(AdminBean bean) throws NoSuchAlgorithmException {
    	
        idadmin = bean.getIdadmin();
        pwadmin = bean.getPwadmin();
/*
       //convert the password to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwadmin.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        */
        String searchQuery = "select * from ADMIN where idadmin='" + idadmin + "' AND pwadmin='" + pwadmin + "'";

        System.out.println("Your ID is " + idadmin);
        System.out.println("Your password is " + pwadmin);
        System.out.println("Query: " + searchQuery);
        
        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String idadmin = rs.getString("idadmin");
           
           		System.out.println("Welcome " + idadmin);
                bean.setIdadmin(idadmin);
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
    
/*
    //add new user (register)
    public void add(AdminBean bean) throws NoSuchAlgorithmException{
    	
        idadmin = bean.getidadmin();
        nama_admin = bean.getnama_admin();
        pwadmin = bean.getpwadmin();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwadmin.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String pass = sb.toString();
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into STAFF (idadmin,nama_admin,pwadmin)values(?,?,?)");
    		ps.setInt(1,idadmin);
    		ps.setString(2,nama_admin);
    		ps.setString(3,pass);
    		ps.executeUpdate();
    	
    		System.out.println("Your ID is " + idadmin);
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
    */
    /*
    public void deleteUser(int idadmin) {
        try {
        	currentCon = ConnectionManager.getConnection();
        	ps=currentCon.prepareStatement("delete from STAFF where idadmin=?");
            ps.setInt(1, idadmin);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    
    //update account
    public void updateAdmin(AdminBean bean) throws NoSuchAlgorithmException {

        idadmin = bean.getIdadmin();
        nama_admin = bean.getNama_admin();
        tel_admin = bean.getTel_admin();
        pwadmin = bean.getPwadmin();
    	
    	/*//convert the password to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwadmin.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
       */
        
        String searchQuery = "UPDATE admin SET tel_admin='" + tel_admin + "', pwadmin='" + pwadmin + "' WHERE idadmin= '" + idadmin + "'";
    	
    	try {

            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            stmt.executeUpdate(searchQuery);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  //get user by id
    public AdminBean getAdminByID(String idadmin) {
        AdminBean admin = new AdminBean();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from ADMIN where idadmin=?");
            
            ps.setString(1, idadmin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                admin.setIdadmin(rs.getString("idadmin"));
                admin.setNama_admin(rs.getString("nama_admin"));
                admin.setTel_admin(rs.getString("tel_admin"));
                admin.setPwadmin(rs.getString("pwadmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    /*
    //get all user
    public List<AdminBean> getAllStaff() {
        List<AdminBean> staffs = new ArrayList<AdminBean>();
        try {
        	currentCon = ConnectionManager.getConnection();
        	stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery("select * from STAFF");
            
            while (rs.next()) {
                AdminBean staff = new AdminBean();
                staff.setidadmin(rs.getInt("idadmin"));
                staff.setpwadmin(rs.getString("pwadmin"));
                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffs;
    }
    */
  //get user by email
public static AdminBean getAdmin(AdminBean bean)  {
    	
        idadmin = bean.getIdadmin();

        String searchQuery = "select * from ADMIN where idadmin='" + idadmin + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String idadmin = rs.getString("idadmin");
           
                bean.setIdadmin(idadmin);
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
