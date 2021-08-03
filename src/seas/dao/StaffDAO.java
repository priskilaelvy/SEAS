package seas.dao;

import java.util.*;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import seas.model.StaffBean;
import seas.connection.ConnectionManager;

public class StaffDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
    static String idstaff, namastaff, jawatan, telstaff, pwstaff;
    
    //login
    public static StaffBean login(StaffBean bean) throws NoSuchAlgorithmException {
    	
        idstaff = bean.getIdstaff();
        pwstaff = bean.getPwstaff();

        //convert the pwstaff to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwstaff.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String searchQuery = "select * from STAFF where idstaff='" + idstaff + "' AND pwstaff='" + sb.toString() + "'";

        System.out.println("Your id is " + idstaff);
        System.out.println("Your password is " + pwstaff);
        System.out.println("Query: " + searchQuery);

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String idstaff = rs.getString("idstaff");
           
           		System.out.println("Welcome " + idstaff);
                bean.setIdstaff(idstaff);
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
    public void add(StaffBean bean) throws NoSuchAlgorithmException{
    	
        idstaff = bean.getIdstaff();
        namastaff = bean.getNamastaff();
        jawatan = bean.getJawatan();
        telstaff = bean.getTelstaff();
        pwstaff = bean.getPwstaff();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwstaff.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String pass = sb.toString();
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into STAFF (idstaff,namastaff,jawatan,telstaff,pwstaff)values(?,?,?,?,?)");
    		ps.setString(1,idstaff);
    		ps.setString(2,namastaff);
    		ps.setString(3,jawatan);
    		ps.setString(4,telstaff);
    		ps.setString(5,pass);
    		ps.executeUpdate();
    	
    		System.out.println("Your id is " + idstaff);
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
    
    public void deleteUser(String idstaff) {
        try {
        	currentCon = ConnectionManager.getConnection();
        	ps=currentCon.prepareStatement("delete from STAFF where idstaff=?");
            ps.setString(1, idstaff);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update account
    public void updateAccount(StaffBean bean) throws NoSuchAlgorithmException {

    	idstaff = bean.getIdstaff();
        namastaff = bean.getNamastaff();
        jawatan = bean.getJawatan();
        telstaff = bean.getTelstaff();
        pwstaff = bean.getPwstaff();

    	//convert the pwstaff to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwstaff.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
       
        String searchQuery = "UPDATE staff SET  jawatan='" + jawatan + "', telstaff='" + telstaff + "', pwstaff='" + sb.toString() + "' WHERE idstaff= '" + idstaff + "'";
    	
    	try {

            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            stmt.executeUpdate(searchQuery);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //get all user
    public List<StaffBean> getAllStaff() {
        List<StaffBean> staff = new ArrayList<StaffBean>();
        try {
        	currentCon = ConnectionManager.getConnection();
        	stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery("select * from STAFF");
            
            while (rs.next()) {
                StaffBean s = new StaffBean();
                s.setIdstaff(rs.getString("idstaff"));
                s.setPwstaff(rs.getString("pwstaff"));
                staff.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }
    
    //get user by idstaff
    public StaffBean getStaffByID(String idstaff) {
        StaffBean staff = new StaffBean();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from STAFF where idstaff=?");
            
            ps.setString(1, idstaff);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                staff.setIdstaff(rs.getString("idstaff"));
                staff.setNamastaff(rs.getString("namastaff"));
                staff.setJawatan(rs.getString("jawatan"));
                staff.setTelstaff(rs.getString("telstaff"));
                staff.setPwstaff(rs.getString("pwstaff"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }
    
  //get user by idstaff
public static StaffBean getStaff(StaffBean bean)  {
    	
        idstaff = bean.getIdstaff();

        String searchQuery = "select * from STAFF where idstaff='" + idstaff + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String idstaff = rs.getString("idstaff");
           
                bean.setIdstaff(idstaff);
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
