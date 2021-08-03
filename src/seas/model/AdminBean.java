package seas.model;

public class AdminBean {
	private String idadmin, nama_admin, tel_admin, pwadmin;
	boolean valid;
	
	public String getIdadmin() {
		return idadmin;
	}
	
	public void setIdadmin(String idadmin) {
		this.idadmin = idadmin;
	}
	
	public String getNama_admin() {
		return nama_admin;
	}
	
	public void setNama_admin(String nama_admin) {
		this.nama_admin = nama_admin;
	}
	
	public String getTel_admin() {
		return tel_admin;
	}
	
	public void setTel_admin(String tel_admin) {
		this.tel_admin = tel_admin;
	}
	
	public String getPwadmin() {
		return pwadmin;
	}
	
	public void setPwadmin(String pwadmin) {
		this.pwadmin = pwadmin;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
